/**
 * While.java
 *
 * Represents a while do statement in the While language.
 */

package wam.ast;

public class While extends Statement {
    /**
     * The condition that decides whether the loop should continue.
     */
    private final Expression condition;

    /**
     * The statement to execute while the condition is true.
     */
    private final Statement statement;

    /**
     * Create a new While loop.
     *
     * @param condition The condition under which the loop should execute.
     * @param statement The statement to execute while the condition is true.
     * @param lineNumber @see Node
     */
    public While(Expression condition, Statement statement, int lineNumber) {
        super(lineNumber);
        this.condition = condition;
        this.statement = statement;
    }

    /**
     * @return the condition of the while statement.
     */
    public Expression getCondition() {
        return condition;
    }

    /**
     * @return the statement to execute while the condition is true.
     */
    public Statement getStatement() {
        return statement;
    }
}
