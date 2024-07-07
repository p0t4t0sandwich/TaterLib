/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.logger.impl;

import dev.neuralnexus.taterapi.logger.Logger;

/** A generic Apache implementation of the {@link Logger} interface. */
public class ApacheLogger implements Logger {
    private final org.apache.logging.log4j.Logger logger;

    public ApacheLogger(Object logger) {
        this.logger = (org.apache.logging.log4j.Logger) logger;
    }

    @Override
    public Object getLogger() {
        return this.logger;
    }

    @Override
    public void info(String message) {
        this.logger.info(message);
    }

    @Override
    public void warn(String message) {
        this.logger.warn(message);
    }

    @Override
    public void warn(String message, Throwable throwable) {
        this.logger.warn(message);
        throwable.printStackTrace();
    }

    @Override
    public void error(String message) {
        this.logger.error(message);
    }

    @Override
    public void error(String message, Throwable throwable) {
        this.logger.error(message);
        throwable.printStackTrace();
    }

    @Override
    public void debug(String message) {
        this.logger.debug(message);
    }

    @Override
    public void trace(String message) {
        this.logger.trace(message);
    }

    @Override
    public void fatal(String message) {
        this.logger.error(message);
    }
}
