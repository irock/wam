/**
 * BooleanOperand.java
 *
 * Represents a boolean operand in the While Virtual Machine operand stack.
 */

package wam.vm;

public class BooleanOperand extends Operand {
    /**
     * The value that this operand should store.
     */
    private final boolean value;

    /**
     * Create a new BooleanOperand.
     *
     * @param value The value this operand should store.
     */
    public BooleanOperand(boolean value) {
        super(Operand.Type.Boolean);
        this.value = value;
    }

    /**
     * @return the value of this operand.
     */
    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value ? "tt" : "ff";
    }
}
