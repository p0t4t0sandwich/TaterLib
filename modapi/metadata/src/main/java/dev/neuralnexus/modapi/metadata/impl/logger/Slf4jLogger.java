/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.logger;

import dev.neuralnexus.modapi.metadata.Logger;

import org.slf4j.LoggerFactory;

/** A generic SLF4J implementation of the {@link Logger} interface. */
@SuppressWarnings("CallToPrintStackTrace")
public final class Slf4jLogger implements Logger {
    private final org.slf4j.Logger logger;

    public Slf4jLogger(String modId) {
        this.logger = LoggerFactory.getLogger(modId);
    }

    @Override
    public org.slf4j.Logger getLogger() {
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
