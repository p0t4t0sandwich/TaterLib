package dev.neuralnexus.taterlib.bukkit.logger;

import dev.neuralnexus.taterlib.common.logger.AbstractLogger;

import java.util.logging.Logger;

/**
 * The Bukkit logger.
 */
public class BukkitLogger implements AbstractLogger {
    private final Logger logger;

    /**
     * Constructor for the Bukkit logger.
     * @param logger The Bukkit logger
     */
    public BukkitLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(String message) {
        logger.info(message);
    }
}
