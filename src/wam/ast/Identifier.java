/**
 * Identifier.java
 *
 * Represents an identifier in the While language.
 */

package wam.ast;

public class Identifier extends Value {
    /**
     * The name of the identifier.
     */
    private final String name;

    /**
     * Create a new Identifier with the given name.
     *
     * @param name The name of the identifier.
     * @param lineNumber @see Node
     */
    public Identifier(String name, int lineNumber) {
        super(lineNumber);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
