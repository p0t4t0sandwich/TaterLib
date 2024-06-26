/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.logger.impl;

import dev.neuralnexus.taterlib.logger.Logger;

/** A generic SLF4J implementation of the {@link Logger} interface. */
public class Slf4jLogger implements Logger {
    private final org.slf4j.Logger logger;

    public Slf4jLogger(Object logger) {
        this.logger = (org.slf4j.Logger) logger;
    }

    /** {@inheritDoc} */
    @Override
    public void info(String message) {
        this.logger.info(message);
    }

    /** {@inheritDoc} */
    @Override
    public void warn(String message) {
        this.logger.warn(message);
    }

    /** {@inheritDoc} */
    @Override
    public void warn(String message, Throwable throwable) {
        this.logger.warn(message);
        throwable.printStackTrace();
    }

    /** {@inheritDoc} */
    @Override
    public void error(String message) {
        this.logger.error(message);
    }

    /** {@inheritDoc} */
    @Override
    public void error(String message, Throwable throwable) {
        this.logger.error(message);
        throwable.printStackTrace();
    }

    /** {@inheritDoc} */
    @Override
    public void debug(String message) {
        this.logger.debug(message);
    }

    /** {@inheritDoc} */
    @Override
    public void trace(String message) {
        this.logger.trace(message);
    }

    /** {@inheritDoc} */
    @Override
    public void fatal(String message) {
        this.logger.error(message);
    }
}
