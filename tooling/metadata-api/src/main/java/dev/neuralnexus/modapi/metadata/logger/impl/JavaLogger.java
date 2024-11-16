/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.logger.impl;

import dev.neuralnexus.modapi.metadata.logger.Logger;

@SuppressWarnings("CallToPrintStackTrace")
public class JavaLogger implements Logger {
    private final java.util.logging.Logger logger;

    @Override
    public Object getLogger() {
        return this.logger;
    }

    public JavaLogger(Object logger) {
        this.logger = (java.util.logging.Logger) logger;
    }

    @Override
    public void info(String message) {
        this.logger.info(message);
    }

    @Override
    public void warn(String message) {
        this.logger.warning(message);
    }

    @Override
    public void warn(String message, Throwable throwable) {
        this.logger.warning(message);
        throwable.printStackTrace();
    }

    @Override
    public void error(String message) {
        this.logger.severe(message);
    }

    @Override
    public void error(String message, Throwable throwable) {
        this.logger.severe(message);
        throwable.printStackTrace();
    }

    @Override
    public void debug(String message) {
        this.logger.fine(message);
    }

    @Override
    public void trace(String message) {
        this.logger.finest(message);
    }

    @Override
    public void fatal(String message) {
        this.logger.severe(message);
    }
}
