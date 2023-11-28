package dev.neuralnexus.taterlib.bungee.logger;

import dev.neuralnexus.taterlib.logger.AbstractLogger;

import java.util.logging.Logger;

/** The Bungee logger. */
public class BungeeLogger implements AbstractLogger {
    private final Logger logger;

    /**
     * Constructor for the Bungee logger.
     *
     * @param logger The logger
     */
    public BungeeLogger(Logger logger) {
        this.logger = logger;
    }

    /** {@inheritDoc} */
    @Override
    public void info(String message) {
        logger.info(message);
    }
}
