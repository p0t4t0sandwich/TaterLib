package dev.neuralnexus.taterlib.velocity.logger;

import dev.neuralnexus.taterlib.common.logger.AbstractLogger;

import org.slf4j.Logger;

/** The Velocity logger. */
public class VelocityLogger implements AbstractLogger {
    private final Logger logger;

    /**
     * Constructor for the Velocity logger.
     *
     * @param logger The Velocity logger
     */
    public VelocityLogger(Logger logger) {
        this.logger = logger;
    }

    /** {@inheritDoc} */
    @Override
    public void info(String message) {
        this.logger.info(message);
    }
}
