package dev.neuralnexus.taterlib.sponge.abstractions.logger;

import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;
import org.slf4j.Logger;

/**
 * The Sponge logger.
 */
public class SpongeLogger implements AbstractLogger {
    private final Logger logger;

    /**
     * Constructor for the Sponge logger.
     * @param logger The Sponge logger
     */
    public SpongeLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void info(String message) {
        this.logger.info(message);
    }
}
