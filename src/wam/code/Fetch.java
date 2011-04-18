/**
 * Fetch.java
 *
 * An instruction representing a FETCH-X in the Abstract Machine Language.
 */

package wam.code;

import wam.ast.Node;
import wam.vm.*;

public class Fetch extends Instruction {
    /**
     * The id this instruction is supposed to fetch.
     */
    private final String id;

    /**
     * Create a new Fetch instruction.
     *
     * @param node @see Instruction
     * @param id The id to fetch
     */
    public Fetch(Node node, String id) {
        super(node, Type.FETCH);
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("FETCH-%s", id);
    }

    @Override
    public void execute(Configuration config) {
        int value = config.getState().get(id);
        config.getOperands().push(new IntegerOperand(value));
    }
}
