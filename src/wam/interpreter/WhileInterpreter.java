/**
 * WhileInterpreter.java
 */

package wam.interpreter;

import java.io.*;
import wam.log.*;
import wam.ast.*;
import wam.parser.WhileParser;
import wam.parser.ParseException;

public class WhileInterpreter {
    /**
     * Main method for an interpreter for the While language.
     */
    public static void main(String[] args) {
        String line = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        WhileParser parser = null;
        Log.setLogger(new StreamLogger(System.err));

        System.out.print("> ");

        try {
            line = reader.readLine();
        } catch (IOException e) {
            Log.logFatal(String.format("IOError: %s", e.getMessage()));
            System.exit(1);
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
                System.exit(1);
            }
        }

        System.out.println();
        System.exit(0);
    }
}
