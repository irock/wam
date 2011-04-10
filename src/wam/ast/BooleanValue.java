/**
 * BooleanValue.java
 *
 * Represents a boolean value in the While language.
 */

package wam.ast;

public class BooleanValue extends Value {
    /**
     * The value of the boolean this value represents.
     */
    private boolean value;

    /**
     * Create a new BooleanValue.
     *
     * @param value The value of this boolean.
     * @param lineNumber @see Node
     */
    public BooleanValue(boolean value, int lineNumber) {
        super(lineNumber);
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
