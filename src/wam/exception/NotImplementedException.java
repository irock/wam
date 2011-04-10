/**
 * NotImplementedException.java
 *
 * An exception indicating that something hasn't been implemented yet.
 */

package wam.exception;

public class NotImplementedException extends WamException {
    /**
     * Create a new NotImplementedException with the given message.
     */
    public NotImplementedException(String message) {
        super(message);
    }
}
