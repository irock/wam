/**
 * Value.java
 *
 * Represents a boolean or integral value in the While language.
 */

package wam.ast;

public abstract class Value extends Expression{
    /**
     * Create a new Value.
     *
     * @param lineNumber @see Node
     */
    public Value(int lineNumber) {
        super(lineNumber);
    }
}
