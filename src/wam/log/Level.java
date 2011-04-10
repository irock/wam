/**
 * Level.java
 *
 * Log level definitions. Only messages with level lower or equal to the
 * log level will be displayed.
 */

package wam.log;

public enum Level {
    SILENT,
    FATAL,
    ERROR,
    WARNING,
    INFO,
    DEBUG;
}
