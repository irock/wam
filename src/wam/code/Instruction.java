/**
 * Instruction.java
 *
 * Represents an instruction in the Abstract Machine Language.
 */

package wam.code;

import java.util.*;

import wam.util.*;
import wam.exception.*;
import wam.ast.Node;

public class Instruction {
    /**
     * The different types of instructions.
     */
    public enum Type {
        PUSH,
        FETCH,
        STORE,
        SKIP,
        ADD,
        SUB,
        MULT,
        EQ,
        LE,
        AND,
        NEG,
        FALSE,
        TRUE,
        LOOP,
        BRANCH;
    }

    /**
     * The node from which this instruction was translated.
     */
    final Node node;

    /**
     * The type of the instruction.
     */
    final Type type;

    /**
     * Hide the default constructor.
     */
    private Instruction() {
        this(null, null);
    }

    /**
     * Create a new Instruction.
     *
     * @param node The source of this instruction.
     * @param type The type of instruction.
     */
    public Instruction(Node node, Type type) {
        this.node = node;
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString();
    }

    /**
     * Convert an Operator type to an Instruction type.
     *
     * @param op The operator to convert.
     * @return The instruction type that match op.
     */
    public static Type operatorToType(Operator op) throws NotImplementedException {
        switch (op) {
            case ADD:
                return Type.ADD;
            case SUB:
                return Type.SUB;
            case MUL:
                return Type.MULT;
            case EQ:
                return Type.EQ;
            case LE:
                return Type.LE;
            case AND:
                return Type.AND;
            case NOT:
                return Type.NEG;
            default:
                throw new NotImplementedException(String.format("Operator %s " +
                            "can't be converted to an instruction type.", op.toString()));
        }
    }

    /**
     * Convert a code stack to a string.
     *
     * @param code The code stack to convert to a string.
     * @return The code given in a more readable form.
     */
    public static String codeToString(Stack<Instruction> code) {
        boolean first = true;
        StringBuffer buffer = new StringBuffer();

        for (int i = code.size()-1; i >= 0; i--) {
            Instruction ins = code.get(i);
            if (first)
                first = false;
            else
                buffer.append(" : ");

            buffer.append(ins.toString());
        }
        
        return buffer.toString();
    }
}
