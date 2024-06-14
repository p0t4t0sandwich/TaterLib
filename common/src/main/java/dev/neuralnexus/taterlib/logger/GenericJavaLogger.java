package dev.neuralnexus.taterlib.logger;

import java.util.logging.Logger;

public class GenericJavaLogger implements AbstractLogger {
    private final Logger logger;

    public GenericJavaLogger(Object logger) {
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
        this.logger.warning(message);
    }

    /** {@inheritDoc} */
    @Override
    public void warn(String message, Throwable throwable) {
        this.logger.warning(message);
        throwable.printStackTrace();
    }

    /** {@inheritDoc} */
    @Override
    public void error(String message) {
        this.logger.severe(message);
    }

    /** {@inheritDoc} */
    @Override
    public void error(String message, Throwable throwable) {
        this.logger.severe(message);
        throwable.printStackTrace();
    }

    /** {@inheritDoc} */
    @Override
    public void debug(String message) {
        this.logger.fine(message);
    }

    /** {@inheritDoc} */
    @Override
    public void trace(String message) {
        this.logger.finest(message);
    }

    /** {@inheritDoc} */
    @Override
    public void fatal(String message) {
        this.logger.severe(message);
    }
}
