/**
 * Program.java
 *
 * Represents a program written in the While language.
 */

package wam.ast;

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
}
