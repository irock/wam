/**
 * Statement.java
 *
 * Represents a statement in the While language.
 */

package wam.ast;

public abstract class Statement extends Node {
    /**
     * Create a new Statement.
     *
     * @param lineNumber @see Node
     */
    public Statement(int lineNumber) {
        super(lineNumber);
    }
}
