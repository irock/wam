/**
 * ExceptionOperand.java
 *
 * An operand that indicates that an exception occured in the execution of an
 * expression in the While language.
 */

package wam.vm;

public class ExceptionOperand extends Operand {
    /**
     * Create a new ExceptionOperand.
     */
    public ExceptionOperand() {
        super(Operand.Type.Exception);
    }

    @Override
    public String toString() {
        return "⊥";
    }
}
