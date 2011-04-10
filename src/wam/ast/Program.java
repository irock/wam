/**
 * Program.java
 *
 * Represents a program written in the While language.
 */

package wam.ast;

import wam.exception.*;
import wam.visitor.*;

public class Program extends Node {
    /**
     * The source of the program, i.e.\ the textual representation of the whole
     * program.
     */
    private final String source;

    /**
     * The entry point of the program.
     */
    private final Statement entry;

    /**
     * Create a new Program.
     *
     * @param source The source of the program.
     * @param entry The statement that the program should execute.
     */
    public Program(String source, Statement entry) {
        super(0);
        this.source = source;
        this.entry = entry;
    }

    /**
     * @return the entry point of the program.
     */
    public Statement getEntry() {
        return entry;
    }

    @Override
    public <T> T accept(WhileVisitor<T> visitor) throws WamException {
        return visitor.visit(this);
    }
}
