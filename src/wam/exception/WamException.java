/**
 * WamException.java
 *
 * A common base class for all exceptions in the wam project.
 */

package wam.exception;

public abstract class WamException extends Exception {
    /**
     * Create a new WamException with the given message.
     */
    public WamException(String message) {
        super(message);
    }
}
