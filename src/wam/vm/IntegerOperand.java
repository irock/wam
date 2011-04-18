/**
 * IntegerOperand.java
 *
 * Represents an integral operand in the While Virtual Machine.
 */

package wam.vm;

public class IntegerOperand extends Operand {
    /**
     * The value of this operand.
     */
    private final int value;

    /**
     * Create a new IntegerOperand.
     *
     * @param value The value that this operand should store.
     */
    public IntegerOperand(int value) {
        super(Operand.Type.Integer);
        this.value = value;
    }

    /**
     * @return the value of the operand.
     */
    public int getValue() {
        return value;
    }
}
