/**
 * Catch.java
 *
 * The end of a try-catch construct in the Abstract Machine language for the
 * While language.
 */

package wam.code;

import java.util.*;

import wam.ast.*;
import wam.vm.*;

public class Catch extends Instruction {
    /**
     * The code to execute as part of the catch-block.
     */
    private final Stack<Instruction> block;

    /**
     * Create a new Catch instruction.
     *
     * @param node @see Instruction
     * @param block The code to execute in the catch block.
     */
    public Catch(Node node, Stack<Instruction> block) {
        super(node, Instruction.Type.CATCH);
        this.block = block;
    }

    /**
     * @return the block of instructions in the catch block.
     */
    public Stack<Instruction> getBlock() {
        return block;
    }

    @Override
    public String toString() {
        return String.format("CATCH(%s)", Instruction.codeToString(block));
    }

    @Override
    public void execute(Configuration config) {
        if (!config.getState().isNormal()) {
            config.getState().setIsNormal(true);
            config.getInstructions().addAll(block);
        }
    }
}
