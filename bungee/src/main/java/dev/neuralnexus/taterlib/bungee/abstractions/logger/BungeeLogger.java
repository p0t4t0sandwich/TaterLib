package dev.neuralnexus.taterlib.bungee.abstractions.logger;

import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;

import java.util.logging.Logger;

/**
 * The Bungee logger.
 */
public class BungeeLogger implements AbstractLogger {
    private final Logger logger;

    /**
     * Constructor for the Bungee logger.
     * @param logger The logger
     */
    public BungeeLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void info(String message) {
        logger.info(message);
    }
}
