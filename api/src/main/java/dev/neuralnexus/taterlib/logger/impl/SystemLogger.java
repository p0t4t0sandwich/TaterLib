/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.logger.impl;

import dev.neuralnexus.taterlib.logger.Logger;

/** A generic implementation of the {@link Logger} interface. */
public class SystemLogger implements Logger {
    private final String pluginId;

    public SystemLogger(String pluginId) {
        this.pluginId = pluginId;
    }

    public String prependPrefix(String message) {
        return "[" + this.pluginId + "] " + message;
    }

    /** {@inheritDoc} */
    @Override
    public void info(String message) {
        System.out.println(prependPrefix(message));
    }

    /** {@inheritDoc} */
    @Override
    public void warn(String message) {
        System.out.println(prependPrefix(message));
    }

    /** {@inheritDoc} */
    @Override
    public void warn(String message, Throwable throwable) {
        System.out.println(prependPrefix(message));
        throwable.printStackTrace();
    }

    /** {@inheritDoc} */
    @Override
    public void error(String message) {
        System.err.println(prependPrefix(message));
    }

    /** {@inheritDoc} */
    @Override
    public void error(String message, Throwable throwable) {
        System.err.println(prependPrefix(message));
        throwable.printStackTrace();
    }

    /** {@inheritDoc} */
    @Override
    public void debug(String message) {
        System.out.println(prependPrefix(message));
    }

    /** {@inheritDoc} */
    @Override
    public void trace(String message) {
        System.out.println(prependPrefix(message));
    }

    /** {@inheritDoc} */
    @Override
    public void fatal(String message) {
        System.err.println(prependPrefix(message));
    }
}
