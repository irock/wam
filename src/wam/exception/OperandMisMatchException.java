/**
 * OperandMisMatchException.java
 *
 * An exception that is thrown when one type of operand was expected, but
 * another one was received.
 */

package wam.exception;

public class OperandMisMatchException extends WamException {
    public OperandMisMatchException(String expected, String received) {
        super(String.format("Operand of type %s was expected, received %s.",
                    expected, received));
    }
}
