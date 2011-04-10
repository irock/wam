/**
 * IntegerValue.java
 *
 * Represents an integral value in the While language.
 */

package wam.ast;

import wam.exception.*;
import wam.visitor.*;

public class IntegerValue extends Value {
    /**
     * The value of the integer that this represents.
     */
    private final int value;

    /**
     * Create a new IntegerValue.
     *
     * @param value The value of the integer.
     * @param lineNumber @see Node
     */
    public IntegerValue(int value, int lineNumber) {
        super(lineNumber);
        this.value = value;
    }

    /**
     * @return the integer value.
     */
    public int getValue() {
        return value;
    }

    @Override
    public <T> T accept(WhileVisitor<T> visitor) throws WamException {
        return visitor.visit(this);
    }
}
