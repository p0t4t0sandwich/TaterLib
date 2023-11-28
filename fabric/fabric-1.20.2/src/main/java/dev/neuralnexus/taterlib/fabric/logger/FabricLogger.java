package dev.neuralnexus.taterlib.fabric.logger;

import dev.neuralnexus.taterlib.logger.AbstractLogger;

import org.slf4j.Logger;

/** The Fabric logger. */
public class FabricLogger implements AbstractLogger {
    private final String prefix;
    private final Logger logger;

    /**
     * Constructor for the Fabric logger.
     *
     * @param prefix The prefix
     * @param logger The logger
     */
    public FabricLogger(String prefix, Logger logger) {
        this.prefix = prefix;
        this.logger = logger;
    }

    /** {@inheritDoc} */
    @Override
    public void info(String message) {
        logger.info(prefix + message);
    }
}
