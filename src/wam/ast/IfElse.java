/**
 * IfElse.java
 *
 * Represents an if-else statement in the While language.
 */

package wam.ast;

import wam.exception.*;
import wam.visitor.*;

public class IfElse extends Statement {
    /**
     * The condition decides whether to execute the ifStatement or the
     * elseStatement.
     */
    private final Expression condition;

    /**
     * The statement to execute if the condition evaluates to true.
     */
    private final Statement ifStatement;

    /**
     * The statement to execute if the condition evaluates to false.
     */
    private final Statement elseStatement;

    /**
     * Create a new IfElse statement.
     *
     * @param condition The expression to evaluate for deciding which statement
     * to execute next.
     * @param ifStatement The statement to execute if the condition is true.
     * @param elseStatement The statement to execute if the condition is false.
     * @param lineNumber @see Node
     */
    public IfElse(Expression condition, Statement ifStatement, Statement elseStatement, int lineNumber) {
        super(lineNumber);
        this.condition = condition;
        this.ifStatement = ifStatement;
        this.elseStatement = elseStatement;
    }

    /**
     * @return the condition expression.
     */
    public Expression getCondition() {
        return condition;
    }

    /**
     * @return the statement to execute if the condition is true.
     */
    public Statement getIfStatement() {
        return ifStatement;
    }

    /**
     * @return the statement to execute if the condition is false.
     */
    public Statement getElseStatement() {
        return elseStatement;
    }

    @Override
    public <T> T accept(WhileVisitor<T> visitor) throws WamException {
        return visitor.visit(this);
    }
}
