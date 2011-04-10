/**
 * BooleanValue.java
 *
 * Represents a boolean value in the While language.
 */

package wam.ast;

import wam.exception.*;
import wam.visitor.*;

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

    /**
     * @return the boolean value of this object.
     */
    public boolean getValue() {
        return value;
    }

    @Override
    public <T> T accept(WhileVisitor<T> visitor) throws WamException {
        return visitor.visit(this);
    }
}
