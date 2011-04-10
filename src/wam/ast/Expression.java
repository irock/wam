/**
 * Expression.java
 *
 * Represents an expression in the While language. An expression could have the
 * form of an addition, multiplication, subtraction, value or a combination of
 * these.
 */

package wam.ast;

import wam.util.*;

public abstract class Expression extends Node {
    /**
     * Create a new Expression.
     *
     * @param lineNumber @see Node.
     */
    public Expression(int lineNumber) {
        super(lineNumber);
    }
}
