/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.logger;

import dev.neuralnexus.taterapi.meta.MetaAPI;

/** The abstract logger. */
public interface Logger {
    // TODO: conditionally apply TextUtil.ansiParser based on the platform

    static Logger create(String modId) {
        return MetaAPI.instance().logger(modId);
    }

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
     * @param throwable The throwable to log
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
     * @param throwable The throwable to log
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
