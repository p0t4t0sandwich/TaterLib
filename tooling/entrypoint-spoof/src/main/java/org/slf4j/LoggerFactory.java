/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package org.slf4j;

/** Fake LoggerFactory class. */
public class LoggerFactory {
    public static Logger getLogger(String name) {
        return null;
    }

    public static Logger getLogger(Class<?> clazz) {
        return null;
    }
}
