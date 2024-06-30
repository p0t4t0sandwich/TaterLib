package org.slf4j;

/**
 * Fake LoggerFactory class.
 */
public class LoggerFactory {
    public static Logger getLogger(String name) {
        return null;
    }

    public static Logger getLogger(Class<?> clazz) {
        return null;
    }
}
