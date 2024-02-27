package org.apache.logging.log4j;

/** Fake Log4j Logger class to simplify the creation of entrypoints. */
public interface Logger {
    void info(String message);

    void warn(String message);

    void error(String message);

    void debug(String message);

    void trace(String message);
}
