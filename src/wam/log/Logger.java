/**
 * Logger.java
 *
 * A base class used to log messages from a program.
 */

package wam.log;

public abstract class Logger {
    /**
     * The default log level.
     */
    public static final Level DEFAULT_LEVEL = Level.INFO;

    /**
     * The current logging level.
     * Only output messages at least as severe as the given level.
     */
    private Level level;

    /**
     * Create a new Logger and initialize the logging level to the default.
     */
    public Logger() {
        this(DEFAULT_LEVEL);
    }

    /**
     * Create a new Logger and initialize the logging level to the given one.
     *
     * @param level The logging level to use.
     */
    public Logger(Level level) {
        this.level = level;
    }

    /**
     * @return the level of the logger.
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Set the logging level of the Logger.
     *
     * @param level The log level to use.
     */
    public void init(Level level) {
        this.level = level;
    }

    /**
     * Log the given message.
     *
     * @param level The severity of the message.
     * @param message The message to log.
     */
    public abstract void log(Level level, String message);
}
