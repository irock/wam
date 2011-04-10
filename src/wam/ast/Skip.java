/**
 * Skip.java
 *
 * Represents the skip statement in the While language. This operation is
 * similar to the more common noop operation.
 */

package wam.ast;

import wam.exception.*;
import wam.visitor.*;

public class Skip extends Statement {
    /**
     * Create a new Skip statement.
     *
     * @param lineNumber @see Node
     */
    public Skip(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public <T> T accept(WhileVisitor<T> visitor) throws WamException {
        return visitor.visit(this);
    }
}
