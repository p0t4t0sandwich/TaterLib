package org.slf4j;

/** Fake SLF4J Logger interface to allow for dependency injection. */
public interface Logger {
    void info(String message);

    void warn(String message);

    void error(String message);

    void debug(String message);

    void trace(String message);
}
