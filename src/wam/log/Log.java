/**
 * Log.java
 *
 * Static log handler.
 */

package wam.log;

public class Log {
    /**
     * The Logger in use.
     */
    private static Logger logger = null;

    /**
     * Set the Logger to use.
     *
     * @param logger The logger to use.
     */
    public static void setLogger(Logger logger) {
        Log.logger = logger;
    }

    /**
     * Log the given message.
     *
     * @param level The severity of the message.
     * @param message The message to log.
     */
    public static void log(Level level, String message) {
        logger.log(level, message);
    }

    /**
     * Log the given message with fatal level.
     *
     * @param message The message to log.
     */
    public static void logFatal(String message) {
        log(Level.FATAL, message);
    }

    /**
     * Log the given message with error level.
     *
     * @param message The message to log.
     */
    public static void logError(String message) {
        log(Level.ERROR, message);
    }

    /**
     * Log the given message with warning level.
     *
     * @param message The message to log.
     */
    public static void logWarning(String message) {
        log(Level.WARNING, message);
    }

    /**
     * Log the given message with info level.
     *
     * @param message The message to log.
     */
    public static void logInfo(String message) {
        log(Level.INFO, message);
    }

    /**
     * Log the given message with debug level.
     *
     * @param message The message to log.
     */
    public static void logDebug(String message) {
        log(Level.DEBUG, message);
    }
}
