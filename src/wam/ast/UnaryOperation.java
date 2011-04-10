/**
 * UnaryOperation.java
 *
 * Represents an unary operation in the While language.
 */

package wam.ast;

import wam.util.*;
import wam.exception.*;
import wam.visitor.*;

public class UnaryOperation extends Operation {
    /**
     * The expression the unary operator of this operation operates on.
     */
    private final Expression exp;

    /**
     * Create a new UnaryOperation.
     *
     * @param op The operator of this operation.
     * @param exp The expression this operation works on.
     * @param lineNumber @see Node
     */
    public UnaryOperation(Operator op, Expression exp, int lineNumber) {
        super(op, lineNumber);
        this.exp = exp;
    }

    /**
     * @return the expression this operation operates on.
     */
    public Expression getExpression() {
        return exp;
    }

    @Override
    public <T> T accept(WhileVisitor<T> visitor) throws WamException {
        return visitor.visit(this);
    }
}
