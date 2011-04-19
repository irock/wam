/**
 * TryStatement.java
 *
 * An AST node representing a try-catch statement in the While language.
 */

package wam.ast;

import wam.exception.*;
import wam.visitor.WhileVisitor;

public class TryStatement extends Statement {
    /**
     * The Statement to execute inside the try block.
     */
    Statement tryStatement;

    /**
     * The Statement to execute if the try block results in an exception.
     */
    Statement catchStatement;

    /**
     * Create a new TryStatement.
     *
     * @param tryStatement The statement to start execute as part of the try
     * statement.
     * @param catchStatement The statement to execute if the tryStatement
     * results in an exception.
     * @param lineNumber @see Node
     */
    public TryStatement(Statement tryStatement, Statement catchStatement, int lineNumber) {
        super(lineNumber);
        this.tryStatement = tryStatement;
        this.catchStatement = catchStatement;
    }

    /**
     * @return the statement that should be executed first.
     */
    public Statement getTryStatement() {
        return tryStatement;
    }

    /**
     * @return the statement that should be executed if the try statement
     * fails.
     */
    public Statement getCatchStatement() {
        return catchStatement;
    }

    @Override
    public <T> T accept(WhileVisitor<T> visitor) throws WamException {
        return visitor.visit(this);
    }
}
