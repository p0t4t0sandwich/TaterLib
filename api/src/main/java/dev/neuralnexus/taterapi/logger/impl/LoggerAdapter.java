/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.logger.impl;

import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.util.TextUtil;

/** A generic implementation of the {@link Logger} interface. */
public class LoggerAdapter implements Logger {
    private final String prefix;
    private final Logger logger;

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

    @Override
    public Object getLogger() {
        return this.logger.getLogger();
    }

    @Override
    public void info(String message) {
        this.logger.info(TextUtil.ansiParser(this.prefix + message));
    }

    @Override
    public void warn(String message) {
        this.logger.warn(TextUtil.ansiParser(this.prefix + message));
    }

    @Override
    public void warn(String message, Throwable throwable) {
        this.logger.warn(TextUtil.ansiParser(this.prefix + message), throwable);
    }

    @Override
    public void error(String message) {
        this.logger.error(TextUtil.ansiParser(this.prefix + message));
    }

    @Override
    public void error(String message, Throwable throwable) {
        this.logger.error(TextUtil.ansiParser(this.prefix + message), throwable);
    }

    @Override
    public void debug(String message) {
        this.logger.debug(TextUtil.ansiParser(this.prefix + message));
    }

    @Override
    public void trace(String message) {
        this.logger.trace(TextUtil.ansiParser(this.prefix + message));
    }

    @Override
    public void fatal(String message) {
        this.logger.fatal(TextUtil.ansiParser(this.prefix + message));
    }

    enum LoggerType {
        JAVA,
        APACHE,
        SLF4J,
        GENERIC
    }
}
