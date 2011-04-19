/**
 * Loop.java
 *
 * A class used for representing the LOOP instruction in the Abstract Machine
 * Language.
 */

package wam.code;

import java.util.*;

import wam.ast.Node;
import wam.exception.*;
import wam.vm.*;

public class Loop extends Instruction {
    /**
     * The code to execute for the condition of the loop.
     */
    private final Stack<Instruction> conditionCode;

    /**
     * The code to execute in the loop.
     */
    private final Stack<Instruction> loopCode;

    /**
     * Create a new Loop instruction.
     *
     * @param node @see Instruction
     * @param conditionCode The code that constitutes the condition for the loop.
     * @param loopCode The code to execute inside the loop.
     */
    public Loop(Node node, Stack<Instruction> conditionCode, Stack<Instruction> loopCode) {
        super(node, Instruction.Type.LOOP);
        this.conditionCode = conditionCode;
        this.loopCode = loopCode;
    }

    /**
     * @return the condition code of the loop.
     */
    public Stack<Instruction> getConditionCode() {
        return conditionCode;
    }

    /**
     * @return the code to execute inside the loop.
     */
    public Stack<Instruction> getLoopCode() {
        return loopCode;
    }

    @Override
    public String toString() {
        return String.format("LOOP(%s, %s)", Instruction.codeToString(conditionCode),
                Instruction.codeToString(loopCode));
    }

    @Override
    public String toShortString() {
        return String.format("LOOP(%s, %s)",
                Instruction.codeToShortString(conditionCode, 1),
                Instruction.codeToShortString(loopCode, 1));
    }

    @Override
    public void execute(Configuration config) {
        Stack<Instruction> ifCode = new Stack<Instruction>();
        ifCode.push(this);
        ifCode.addAll(loopCode);

        Stack<Instruction> elseCode = new Stack<Instruction>();
        elseCode.push(new Instruction(node, Instruction.Type.SKIP));

        config.getInstructions().push(new Branch(node, ifCode, elseCode));
        config.getInstructions().addAll(conditionCode);
   }
}
