/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.logger;

/** The abstract logger. */
public interface Logger {
    /**
     * Get underlying logger.
     *
     * @return The underlying logger
     */
    Object getLogger();

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
     * Logs a warning message.
     *
     * @param message The message to log
     */
    void warn(String message, Throwable throwable);

    /**
     * Logs an error message.
     *
     * @param message The message to log
     */
    void error(String message);

    /**
     * Logs an error message.
     *
     * @param message The message to log
     */
    void error(String message, Throwable throwable);

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
