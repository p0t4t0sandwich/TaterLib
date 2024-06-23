package dev.neuralnexus.taterlib.logger.impl;

import dev.neuralnexus.taterlib.logger.Logger;

/** A generic Apache implementation of the AbstractLogger interface. */
public class GenericApacheLogger implements Logger {
    private final org.apache.logging.log4j.Logger logger;

    public GenericApacheLogger(Object logger) {
        this.logger = (org.apache.logging.log4j.Logger) logger;
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
