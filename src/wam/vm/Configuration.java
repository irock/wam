/**
 * Configuration.java
 *
 * Represents a configuration for the While Virtual Machine. A configuration
 * consists of a stack of instructions - code - to execute, a state space and
 * an operand stack.
 */

package wam.vm;

import java.util.*;

import wam.code.*;

public class Configuration implements Cloneable {
    /**
     * The set of instructions that remains to be executed.
     */
    private Stack<Instruction> instructions;

    /**
     * The set of operands that are stored on the operand stack.
     */
    private Stack<Operand> operands;

    /**
     * The current state.
     */
    private State state;

    /**
     * Create a new Configuration.
     *
     * @param instructions The stack of instructions that remains to be executed.
     * @param operands The stack of operands that are currently stored on the stack.
     * @param state The current state space.
     */
    @SuppressWarnings("unchecked")
    public Configuration(Stack<Instruction> instructions, Stack<Operand> operands, State state) {
        this.instructions = (Stack<Instruction>)instructions.clone();
        this.operands = (Stack<Operand>)operands.clone();
        this.state = new State(state);
    }

    /**
     * Create a new start Configuration.
     *
     * @param instructions The instructions that should be executed.
     */
    @SuppressWarnings("unchecked")
    public Configuration(Stack<Instruction> instructions) {
        this.instructions = (Stack<Instruction>)instructions.clone();
        this.operands = new Stack<Operand>();
        this.state = new State();
    }

    /**
     * Create a new copy of the given Configuration.
     *
     * @param configuration Configuration to copy.
     */
    public Configuration(Configuration configuration) {
        this(configuration.instructions, configuration.operands, configuration.state);
    }

    /**
     * @return true iff the configuration is a final configuration.
     */
    public boolean isFinal() {
        return instructions.isEmpty();
    }

    /**
     * @return the state of the configuration.
     */
    public State getState() {
        return state;
    }

    /**
     * @return the instruction stack.
     */
    public Stack<Instruction> getInstructions() {
        return instructions;
    }

    /**
     * @return the operand stack.
     */
    public Stack<Operand> getOperands() {
        return operands;
    }

    @Override
    public String toString() {
        String operandString = operands.size() > 0 ? operands.peek().toString() : "ε";
        if (operands.size() > 1)
            operandString += ":" + operands.get(operands.size()-2).toString();
        if (operands.size() > 2)
            operandString += ":e";

        return String.format("< %s, %s, %s >", Instruction.codeToShortString(instructions, 1), operandString, state.toString());
    }
}
