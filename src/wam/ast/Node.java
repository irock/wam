/**
 * Node.java
 *
 */

package wam.ast;

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
}
