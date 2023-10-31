package dev.neuralnexus.taterlib.forge.logger;

import dev.neuralnexus.taterlib.common.logger.AbstractLogger;
import org.apache.logging.log4j.Logger;

/**
 * The Forge logger.
 */
public class ForgeLogger implements AbstractLogger {
    private final Logger logger;

    /**
     * Constructor for the Forge logger.
     * @param logger The Forge logger
     */
    public ForgeLogger(Logger logger) {
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
