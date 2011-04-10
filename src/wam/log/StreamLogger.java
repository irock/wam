/**
 * StreamLogger.java
 *
 * A class for logging messages to an output stream.
 */

package wam.log;

import java.io.*;

public class StreamLogger extends Logger {
    /**
     * THe PrintWriter to use for file IO.
     */
    PrintWriter output;

    /**
     * Create a new StreamLogger.
     *
     * @param stream The output stream to write the log to.
     */
    public StreamLogger(OutputStream stream) {
        super();
        output = new PrintWriter(new BufferedOutputStream(stream));
    }

    /**
     * Create a new StreamLogger.
     *
     * @param stream The output stream to write the log to.
     * @param level The logging level to use.
     */
    public StreamLogger(OutputStream stream, Level level) {
        super(level);
        output = new PrintWriter(new BufferedOutputStream(stream));
    }

    @Override
    public void log(Level severity, String message) {
        if (severity.ordinal() > getLevel().ordinal())
            return;

        output.println(severity.toString() + ": " + message);
        output.flush();
    }
}
