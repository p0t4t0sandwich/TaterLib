/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.logger.impl;

import dev.neuralnexus.taterapi.logger.Logger;

/** A generic implementation of the {@link Logger} interface. */
public class SystemLogger implements Logger {
    private final String pluginId;

    public SystemLogger(String pluginId) {
        this.pluginId = pluginId;
    }

    public String prependPrefix(String message) {
        return "[" + this.pluginId + "] " + message;
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
