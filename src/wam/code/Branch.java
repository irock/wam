/**
 * Branch.java
 *
 * A class representing a BRANCH instruction in the Abstract Machine Language.
 */

package wam.code;

import java.util.*;

import wam.ast.Node;

public class Branch extends Instruction {
    /**
     * The set of instructions to execute if the top element of the stack is
     * true.
     */
    private final Stack<Instruction> ifCode;

    /**
     * The set of instructions to execute if the top element of the stack is
     * false.
     */
    private final Stack<Instruction> elseCode;

    /**
     * Create a new Branch instruction.
     *
     * @param node @see Instruction
     * @param ifCode The stack of instructions to add to the code stack if the
     * top element of the stack is true.
     * @param elseCode The stack of instructions to execute if the top element
     * of the stack is false.
     */
    public Branch(Node node, Stack<Instruction> ifCode, Stack<Instruction> elseCode) {
        super(node, Instruction.Type.BRANCH);
        this.ifCode = ifCode;
        this.elseCode = elseCode;
    }

    /**
     * @return the instruction to execute if the top element of the stack is true.
     */
    public Stack<Instruction> getIfCode() {
        return ifCode;
    }

    /**
     * @return the instruction to execute if the top element of the stack is false.
     */
    public Stack<Instruction> getElseCode() {
        return elseCode;
    }
    
    @Override
    public String toString() {
        return String.format("BRANCH(%s, %s)", Instruction.codeToString(ifCode),
                Instruction.codeToString(elseCode));
    }
}
