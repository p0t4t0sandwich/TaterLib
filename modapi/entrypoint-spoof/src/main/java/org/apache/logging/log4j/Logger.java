/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package org.apache.logging.log4j;

/** Fake Log4j Logger class to simplify the creation of entrypoints. */
public interface Logger {
    void info(String message);

    void warn(String message);

    void error(String message);

    void debug(String message);

    void trace(String message);
}
