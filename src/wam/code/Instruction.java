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
import wam.vm.*;

public class Instruction {
    /**
     * The different types of instructions.
     */
    public enum Type {
        PUSH,
        FETCH,
        STORE,
        LOOP,
        BRANCH,

        SKIP,
        ADD,
        SUB,
        MULT,
        EQ,
        LE,
        AND,
        NEG,
        FALSE,
        TRUE;
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

    /**
     * Execute this instruction.
     *
     * @param config The configuration in this instruciton is to be executed.
     * @return the resulting configuration.
     */
    public void execute(Configuration config) throws WamException {
        switch (type) {
            case SKIP:
                break;
            case ADD:
            case SUB:
            case MULT:
            case EQ:
            case LE:
                executeBinaryIntegerOperation(config);
                break;
            case AND:
                executeBinaryBooleanOperation(config);
                break;
            case NEG:
                executeUnaryBooleanOperation(config);
                break;
            case FALSE:
                config.getOperands().push(new BooleanOperand(false));
                break;
            case TRUE:
                config.getOperands().push(new BooleanOperand(true));
                break;
            default:
                throw new NotImplementedException(String.format("Support for operator %s is " +
                            "missing in Instrucion.execute().", type.toString()));
        }
    }

    private void executeBinaryIntegerOperation(Configuration config) throws WamException {
        Operand rightOp = config.getOperands().pop();
        Operand leftOp = config.getOperands().pop();

        if ( !leftOp.getType().equals(Operand.Type.Integer))
            throw new OperandMisMatchException(Operand.Type.Integer.toString(), leftOp.getType().toString());
        if (!rightOp.getType().equals(Operand.Type.Integer))
            throw new OperandMisMatchException(Operand.Type.Integer.toString(), rightOp.getType().toString());

        int left = ((IntegerOperand)leftOp).getValue();
        int right = ((IntegerOperand)rightOp).getValue();
        Operand result;

        switch (type) {
            case ADD:
                result = new IntegerOperand(left + right);
                break;
            case SUB:
                result = new IntegerOperand(left - right);
                break;
            case MULT:
                result = new IntegerOperand(left * right);
                break;
            case LE:
                result = new BooleanOperand(left <= right);
                break;
            case EQ:
                result = new BooleanOperand(left == right);
                break;
            default:
                throw new NotImplementedException(String.format("Support for operator %s is " +
                            "missing in Instrucion.BinaryIntegerOperation().", type.toString()));
        }

        config.getOperands().push(result);
    }

    private void executeBinaryBooleanOperation(Configuration config) throws WamException {
        Operand leftOp = config.getOperands().pop();
        Operand rightOp = config.getOperands().pop();

        if ( !leftOp.getType().equals(Operand.Type.Boolean))
            throw new OperandMisMatchException(Operand.Type.Boolean.toString(), leftOp.getType().toString());
        if (!rightOp.getType().equals(Operand.Type.Boolean))
            throw new OperandMisMatchException(Operand.Type.Boolean.toString(), rightOp.getType().toString());

        boolean left = ((BooleanOperand)leftOp).getValue();
        boolean right = ((BooleanOperand)rightOp).getValue();
        Operand result;

        switch (type) {
            case AND:
                result = new BooleanOperand(left && right);
                break;
            default:
                throw new NotImplementedException(String.format("Support for operator %s is " +
                            "missing in Instrucion.BinaryBooleanOperation().", type.toString()));
        }

        config.getOperands().push(result);
    }

    private void executeUnaryBooleanOperation(Configuration config) throws WamException {
        Operand leftOp = config.getOperands().pop();

        if ( !leftOp.getType().equals(Operand.Type.Boolean))
            throw new OperandMisMatchException(Operand.Type.Boolean.toString(), leftOp.getType().toString());

        boolean left = ((BooleanOperand)leftOp).getValue();
        Operand result;

        switch (type) {
            case NEG:
                result = new BooleanOperand(!left);
                break;
            default:
                throw new NotImplementedException(String.format("Support for operator %s is " +
                            "missing in Instrucion.UnaryBooleanOperation().", type.toString()));
        }

        config.getOperands().push(result);
    }
}
