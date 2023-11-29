package dev.neuralnexus.taterlib.logger;

/** The abstract logger. */
public interface AbstractLogger {
    /**
     * Logs an info message.
     *
     * @param message The message to log
     */
    void info(String message);

    /**
     * Logs a warning message.
     *
     * @param message The message to log
     */
    void warn(String message);

    /**
     * Logs an error message.
     *
     * @param message The message to log
     */
    void error(String message);

    /**
     * Logs a debug message.
     *
     * @param message The message to log
     */
    void debug(String message);

    /**
     * Logs a trace message.
     *
     * @param message The message to log
     */
    void trace(String message);

    /**
     * Logs a fatal message.
     *
     * @param message The message to log
     */
    void fatal(String message);
}
