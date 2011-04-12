/**
 * Push.java
 *
 * An instruction for representing pushing a value onto the stack in the
 * Abstract Machine Language.
 */

package wam.code;

import wam.ast.Node;

public class Push extends Instruction {
    /**
     * The integer value to push onto the stack upon execution.
     */
    private final int value;

    /**
     * Create a new Push instruction.
     *
     * @param node @see Instruction
     * @param value The value to push
     */
    public Push(Node node, int value) {
        super(node, Instruction.Type.PUSH);
        this.value = value;
    }
    
    @Override
    public String toString() {
        return String.format("PUSH-(%d)", value);
    }
}
