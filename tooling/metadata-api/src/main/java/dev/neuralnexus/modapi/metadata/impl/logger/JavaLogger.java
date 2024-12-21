/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.logger;

import dev.neuralnexus.modapi.metadata.Logger;

@SuppressWarnings("CallToPrintStackTrace")
public final class JavaLogger implements Logger {
    private final java.util.logging.Logger logger;
    private final String prefix;

    @Override
    public java.util.logging.Logger getLogger() {
        return this.logger;
    }

    public JavaLogger(String modId, Object logger) {
        this.logger = (java.util.logging.Logger) logger;
        this.prefix = "[" + modId + "] ";
    }

    @Override
    public void info(String message) {
        this.logger.info(this.prefix + message);
    }

    @Override
    public void warn(String message) {
        this.logger.warning(this.prefix + message);
    }

    @Override
    public void warn(String message, Throwable throwable) {
        this.logger.warning(this.prefix + message);
        throwable.printStackTrace();
    }

    @Override
    public void error(String message) {
        this.logger.severe(this.prefix + message);
    }

    @Override
    public void error(String message, Throwable throwable) {
        this.logger.severe(this.prefix + message);
        throwable.printStackTrace();
    }

    @Override
    public void debug(String message) {
        this.logger.fine(this.prefix + message);
    }

    @Override
    public void trace(String message) {
        this.logger.finest(this.prefix + message);
    }

    @Override
    public void fatal(String message) {
        this.logger.severe(this.prefix + message);
    }
}
