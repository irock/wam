/**
 * Assignment.java
 *
 * Represents an assignment in the While language.
 */

package wam.ast;

import wam.exception.*;
import wam.visitor.*;

public class Assignment extends Statement {
    /**
     * The identifier this Assignment assigns its value to.
     */
    private final Identifier id;

    /**
     * The value of this assignment, i.e.\ the right hand side of this
     * assignment.
     */
    private final Expression value;

    /**
     * Create a new Assignment.
     *
     * @param id The identifier of this assignment (the left hand side).
     * @param value The value of this assignment (the right hand side).
     * @param lineNumber @see Node
     */
    public Assignment(Identifier id, Expression value, int lineNumber) {
        super(lineNumber);

        this.id = id;
        this.value = value;
    }

    /**
     * @return the left hand part of this assignment.
     */
    public Identifier getIdentifier() {
        return id;
    }

    /**
     * @return the right hand part of this assignment.
     */
    public Expression getValue() {
        return value;
    }

    @Override
    public <T> T accept(WhileVisitor<T> visitor) throws WamException {
        return visitor.visit(this);
    }
}
