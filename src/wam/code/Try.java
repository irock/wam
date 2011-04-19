/**
 * Try.java
 *
 * The start of a try-catch construct in the Abstract Machine language for the
 * While language.
 */

package wam.code;

import java.util.*;

import wam.ast.*;
import wam.vm.*;

public class Try extends Instruction {
    /**
     * The code to execute as part of the try block.
     */
    private final Stack<Instruction> tryBlock;

    /**
     * The catch instruction, to be executed in case that the try block fails.
     */
    private final Catch catchBlock;

    /**
     * Create a new Try instruction.
     *
     * @param node @see Instruction
     * @param tryBlock The code to execute as part of the try block.
     * @param catchBlock The catch instruction.
     */
    public Try(Node node, Stack<Instruction> tryBlock, Catch catchBlock) {
        super(node, Instruction.Type.TRY);
        this.tryBlock = tryBlock;
        this.catchBlock = catchBlock;
    }

    /**
     * @return the try block.
     */
    public Stack<Instruction> getTryBlock() {
        return tryBlock;
    }

    /**
     * @return the catch block.
     */
    public Catch getCatchBlock() {
        return catchBlock;
    }

    @Override
    public String toString() {
        return String.format("TRY(%s, %s)", Instruction.codeToString(tryBlock),
                catchBlock.toString());
    }

    @Override
    public void execute(Configuration config) {
        if (config.getState().isNormal()) {
            config.getInstructions().push(catchBlock);
            config.getInstructions().addAll(tryBlock);
        }
    }
}
