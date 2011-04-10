/**
 * Operation.java
 *
 * Represents an operation in the While language.
 */

package wam.ast;

import wam.util.*;

public abstract class Operation extends Expression {
    /**
     * The operator of the operation.
     */
    private final Operator op;

    /**
     * Create a new Operation.
     *
     * @param op The operator of this operation.
     * @param lineNumber @see Node
     */
    public Operation(Operator op, int lineNumber) {
        super(lineNumber);
        this.op = op;
    }

    /**
     * @return the operator of this operation.
     */
    public Operator getOperator() {
        return op;
    }
}
