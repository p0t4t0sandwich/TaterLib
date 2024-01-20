package dev.neuralnexus.taterlib.logger;

import org.apache.logging.log4j.Logger;

/** A generic Apache implementation of the AbstractLogger interface. */
public class GenericApacheLogger implements AbstractLogger {
    private final Logger logger;

    public GenericApacheLogger(Object logger) {
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
