/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.logger;

import dev.neuralnexus.modapi.metadata.Logger;

/** A generic implementation of the {@link Logger} interface. */
@SuppressWarnings("CallToPrintStackTrace")
public final class SystemLogger implements Logger<Object> {
    private final String modId;

    public SystemLogger(String modId) {
        this.modId = modId;
    }

    public String prependPrefix(String message) {
        return "[" + this.modId + "] " + message;
    }

    @Override
    public Object getLogger() {
        return null;
    }

    @Override
    public void info(String message) {
        System.out.println(prependPrefix(message));
    }

    @Override
    public void warn(String message) {
        System.out.println(prependPrefix(message));
    }

    @Override
    public void warn(String message, Throwable throwable) {
        System.out.println(prependPrefix(message));
        throwable.printStackTrace();
    }

    @Override
    public void error(String message) {
        System.err.println(prependPrefix(message));
    }

    @Override
    public void error(String message, Throwable throwable) {
        System.err.println(prependPrefix(message));
        throwable.printStackTrace();
    }

    @Override
    public void debug(String message) {
        System.out.println(prependPrefix(message));
    }

    @Override
    public void trace(String message) {
        System.out.println(prependPrefix(message));
    }

    @Override
    public void fatal(String message) {
        System.err.println(prependPrefix(message));
    }
}
