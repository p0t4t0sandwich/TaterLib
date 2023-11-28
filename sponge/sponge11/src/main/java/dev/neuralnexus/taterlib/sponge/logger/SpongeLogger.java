package dev.neuralnexus.taterlib.sponge.logger;

import dev.neuralnexus.taterlib.logger.AbstractLogger;

import org.apache.logging.log4j.Logger;

/** The Sponge logger. */
public class SpongeLogger implements AbstractLogger {
    private final Logger logger;

    /**
     * Constructor for the Sponge logger.
     *
     * @param logger The Sponge logger
     */
    public SpongeLogger(Logger logger) {
        this.logger = logger;
    }

    /** {@inheritDoc} */
    @Override
    public void info(String message) {
        this.logger.info(message);
    }
}
