package dev.neuralnexus.taterlib.logger.impl;

import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.logger.Logger;

/** A generic implementation of the AbstractLogger interface. */
public class LoggerAdapter implements Logger {
    private final String prefix;
    private final Logger logger;

    public LoggerAdapter(String PROJECT_ID) {
        this(PROJECT_ID, new Object());
    }

    public LoggerAdapter(String PROJECT_ID, Object logger) {
        this("", PROJECT_ID, logger);
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
                this.logger = new GenericJavaLogger(logger);
                break;
            case APACHE:
                this.logger = new GenericApacheLogger(logger);
                break;
            case SLF4J:
                this.logger = new GenericSlf4jLogger(logger);
                break;
            default:
                this.logger = new GenericLogger(PROJECT_ID);
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
