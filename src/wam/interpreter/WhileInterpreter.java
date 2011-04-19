/**
 * WhileInterpreter.java
 */

package wam.interpreter;

import java.io.*;
import java.util.*;

import wam.log.*;
import wam.ast.*;
import wam.code.*;
import wam.exception.*;
import wam.visitor.*;
import wam.vm.*;
import wam.parser.*;

public class WhileInterpreter {
    enum Mode {
        NORMAL,
        DEBUG,
        PRINT_AST,
        PRINT_AM,
    };

    /**
     * Main method for an interpreter for the While language.
     */
    public static void main(String[] args) {
        Log.setLogger(new StreamLogger(System.err));

        if (args.length == 0)
            System.exit(interactivePrompt());

        Mode mode = Mode.NORMAL;

        for (int i = 0; i < args.length-1; i++) {
            if (args[i].equals("-debug"))
                mode = Mode.DEBUG;
            else if (args[i].equals("-showast"))
                mode = Mode.PRINT_AST;
            else if (args[i].equals("-showam"))
                mode = Mode.PRINT_AM;
            else {
                Log.logError(String.format("Invalid option \"%s\" supplied.", args[i])); 
                System.exit(1);
            }
        }

        StringBuffer buffer = new StringBuffer();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[args.length-1]));
            while (reader.ready()) {
                buffer.append(reader.readLine());
                buffer.append("\n");
            }
        } catch (IOException e) {
            Log.logError(String.format("IO error: %s", e.getMessage()));
            System.exit(1);
        }

        Program program = null;

        try {
            String sourceCode = buffer.toString();
            WhileParser parser = new WhileParser(new StringReader(sourceCode));
            program = parser.program(sourceCode);
        } catch (TokenMgrError e) {
            Log.logFatal("Parsing failed.");
            Log.logFatal(e.getMessage());
            System.exit(1);
        } catch (ParseException e) {
            Log.logFatal("Parsing failed.");
            Log.logFatal(e.getMessage());
            System.exit(1);
        }

        if (mode == Mode.PRINT_AST) {
            try {
                System.out.println(program.accept(new PrettyWhilePrinter()));
            } catch (WamException e) {
                Log.logFatal(e.getMessage());
                System.exit(1);
            }
            System.exit(0);
        }

        Stack<Instruction> code = null;

        try {
            code = program.accept(new WhileTranslator());
        } catch (WamException e) {
            Log.logFatal(e.getMessage());
            System.exit(1);
        }

        if (mode == Mode.PRINT_AM) {
            System.out.println(Instruction.codeToString(code));
            System.exit(0);
        }

        VirtualMachine machine = new VirtualMachine();
        Configuration configuration = new Configuration(code);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        while (!configuration.isFinal()) {
            if (mode == Mode.DEBUG) {
                System.out.println(configuration);
                boolean stop = false;

                while (!stop) {
                    String line = null;

                    System.out.print("> ");

                    try {
                        line = input.readLine();
                    } catch (IOException e) {
                        Log.logFatal(String.format("IO error: %s", e.getMessage()));
                        System.exit(1);
                    }


                    if (line == null) {
                        System.out.println();
                        System.exit(0);
                    } else if (line.length() == 0 || line.equals("step"))
                        stop = true;
                    else if (line.equals("run")) {
                        mode = Mode.NORMAL;
                        stop = true;
                    } else if (line.length() > "set".length() && line.substring(0, "set".length()).equals("set")) {
                        // There's no scanf in Java, so we have to go the hard way.
                        String id = "";
                        boolean invalidId = false;
                        int i;

                        for (i = "set".length(); i < line.length()-1; i++) {
                            char c = line.charAt(i);

                            if (c == ' ') {
                                if (id.length() > 0)
                                    break;
                            } else if (c == '_' || Character.isLetter(c) ||
                                    (Character.isDigit(c) && id.length() > 0))
                            {
                                id += "" + c;
                            } else {
                                invalidId = true;
                                id += c;
                            }
                        }

                        if (id.length() == 0 || invalidId) {
                            System.out.println(String.format("invalid id \"%s\" found.", id));
                        } else {
                            for (; i < line.length() && line.charAt(i) == ' '; i++);

                            String value = "";

                            if (i < line.length() && line.charAt(i) == '-') {
                                value += '-';
                                i++;
                            }

                            for (; i < line.length() && line.charAt(i) >= '0' && line.charAt(i) <= '9'; i++)
                                value += line.charAt(i);

                            for (; i < line.length() && line.charAt(i) == ' '; i++);

                            if (value.length() == 0) {
                                System.out.println("Value expected.");
                            } else if (i != line.length()) {
                                System.out.println("Unexpected data following value.");
                            } else {
                                try {
                                    configuration.getState().set(id, Integer.parseInt(value));
                                    System.out.println(configuration);
                                } catch (NumberFormatException e) {
                                    System.out.println(String.format("Invalid integer value %s supplied.", value));
                                }
                            }
                        }
                    } else
                        System.out.println(String.format("Invalid debug instruction \"%s\"", line));
                }
            }

            try {
                configuration = machine.step(configuration);
            } catch (WamException e) {
                Log.logFatal(e.getMessage());
                System.exit(1);
            }

        }

        System.out.println(configuration);
        System.exit(0);
    }

    /**
     * Run an interactive prompt, evaluating each line of code in input.
     */
    private static int interactivePrompt() {
        String line = null;
        WhileParser parser = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("> ");

        try {
            line = reader.readLine();
        } catch (IOException e) {
            Log.logFatal(String.format("IOError: %s", e.getMessage()));
            return 1;
        }

        while (line != null) {
            if (parser == null)
                parser = new WhileParser(new StringReader(line));
            else
                parser.ReInit(new StringReader(line));

            try {
                Program program = parser.program(line);
            } catch (ParseException e) {
                Log.logError(String.format("Failed: %s", e.getMessage()));
            }

            System.out.print("> ");

            try {
                line = reader.readLine();
            } catch (IOException e) {
                Log.logFatal(String.format("IOError: %s", e.getMessage()));
                return 1;
            }
        }

        System.out.println();
        return 0;
    }
}
