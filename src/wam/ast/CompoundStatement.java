/**
 * CompoundStatement.java
 *
 * Represents compound statements in the While language, i.e.\ statements to be
 * executed sequentially.
 */

package wam.ast;

import java.util.List;
import wam.exception.*;
import wam.visitor.*;

public class CompoundStatement extends Statement {
    /**
     * A list of statements to executed sequentially.
     */
    private final List<Statement> statements;

    /**
     * Create a new CompoundStatement.
     *
     * @param statements The statements to be executed sequentially.
     * @param lineNumber @see Node
     */
    public CompoundStatement(List<Statement> statements, int lineNumber) {
        super(lineNumber);
        this.statements = statements;
    }

    /**
     * @return the list of statements to execute.
     */
    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public <T> T accept(WhileVisitor<T> visitor) throws WamException {
        return visitor.visit(this);
    }
}
