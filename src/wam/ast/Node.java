/**
 * Node.java
 *
 * Base class for the AST nodes.
 */

package wam.ast;

import wam.exception.*;
import wam.visitor.*;

public abstract class Node {
    /**
     * The line number where the Node starts.
     */
    public final int lineNumber;

    /**
     * Hide the default constructor.
     */
    private Node() {
        this.lineNumber = -1;
    }

    /**
     * Create a new Node starting at the given line number.
     *
     * @param lineNumber The line number where the node starts.
     */
    public Node(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * @return the line number of this node.
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Visit the visitor given.
     *
     * @param visitor The visitor to visit.
     */
    public <T> T accept(WhileVisitor<T> visitor) throws WamException {
        return visitor.visit(this);
    }
}
