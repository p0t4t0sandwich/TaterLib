/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.logger.impl;

import dev.neuralnexus.taterlib.Utils;

/** A generic implementation of the {@link dev.neuralnexus.taterlib.logger.Logger} interface. */
public class LoggerAdapter implements dev.neuralnexus.taterlib.logger.Logger {
    private final String prefix;
    private final dev.neuralnexus.taterlib.logger.Logger logger;

    public LoggerAdapter(String pluginId) {
        this(pluginId, new Object());
    }

    public LoggerAdapter(String pluginId, Object logger) {
        this("[" + pluginId + "] ", pluginId, logger);
    }

    public LoggerAdapter(String prefix, String PROJECT_ID, Object logger) {
        this.prefix = prefix;

        LoggerType type = LoggerType.GENERIC;

        if (logger instanceof java.util.logging.Logger) {
            type = LoggerType.JAVA;
        }

        try {
            if (Class.forName("org.apache.logging.log4j.Logger").isInstance(logger)) {
                type = LoggerType.APACHE;
            }
        } catch (ClassNotFoundException ignored) {
        }

        try {
            if (Class.forName("org.slf4j.Logger").isInstance(logger)) {
                type = LoggerType.SLF4J;
            }
        } catch (ClassNotFoundException ignored) {
        }

        switch (type) {
            case JAVA:
                this.logger = new JavaLogger(logger);
                break;
            case APACHE:
                this.logger = new ApacheLogger(logger);
                break;
            case SLF4J:
                this.logger = new Slf4jLogger(logger);
                break;
            default:
                this.logger = new SystemLogger(PROJECT_ID);
                break;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void info(String message) {
        this.logger.info(Utils.ansiParser(this.prefix + message));
    }

    /** {@inheritDoc} */
    @Override
    public void warn(String message) {
        this.logger.warn(Utils.ansiParser(this.prefix + message));
    }

    /** {@inheritDoc} */
    @Override
    public void warn(String message, Throwable throwable) {
        this.logger.warn(Utils.ansiParser(this.prefix + message), throwable);
    }

    /** {@inheritDoc} */
    @Override
    public void error(String message) {
        this.logger.error(Utils.ansiParser(this.prefix + message));
    }

    /** {@inheritDoc} */
    @Override
    public void error(String message, Throwable throwable) {
        this.logger.error(Utils.ansiParser(this.prefix + message), throwable);
    }

    /** {@inheritDoc} */
    @Override
    public void debug(String message) {
        this.logger.debug(Utils.ansiParser(this.prefix + message));
    }

    /** {@inheritDoc} */
    @Override
    public void trace(String message) {
        this.logger.trace(Utils.ansiParser(this.prefix + message));
    }

    /** {@inheritDoc} */
    @Override
    public void fatal(String message) {
        this.logger.fatal(Utils.ansiParser(this.prefix + message));
    }

    enum LoggerType {
        JAVA,
        APACHE,
        SLF4J,
        GENERIC
    }
}
