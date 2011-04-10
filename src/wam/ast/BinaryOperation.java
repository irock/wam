/**
 * BinaryOperation.java
 *
 * Represents a binary operation in the While language. This could e.g. be an
 * addition or a multiplication.
 */

package wam.ast;

import wam.util.*;

public class BinaryOperation extends Operation {
    /**
     * The left hand expression of this binary operation.
     */
    private final Expression left;

    /**
     * The right hand expreesion of this binary operation.
     */
    private final Expression right;

    /**
     * Create a new BinaryOperation.
     *
     * @param op The type of operation (add, sub, etc).
     * @param left The left hand side of the binary operation.
     * @param right The right hand side of the binary operation.
     * @param lineNumber @see Node
     */
    public BinaryOperation(Operator op, Expression left, Expression right, int lineNumber) {
        super(op, lineNumber);
        this.left = left;
        this.right = right;
    }

    /**
     * @return The left hand expression of this binary operation.
     */
    public Expression getLeftHand() {
        return left;
    }

    /**
     * @return The right hand expression of this binary operation.
     */
    public Expression getRightHand() {
        return right;
    }
}
