package dev.neuralnexus.taterlib.logger;

import org.slf4j.Logger;

/** A generic SLF4J implementation of the AbstractLogger interface. */
public class GenericSlf4jLogger implements AbstractLogger {
    private final Logger logger;

    public GenericSlf4jLogger(Object logger) {
        this.logger = (Logger) logger;
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
    public void error(String message) {
        this.logger.error(message);
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
