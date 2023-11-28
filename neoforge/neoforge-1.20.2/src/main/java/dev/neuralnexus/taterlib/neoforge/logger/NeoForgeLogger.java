package dev.neuralnexus.taterlib.neoforge.logger;

import dev.neuralnexus.taterlib.logger.AbstractLogger;

import org.slf4j.Logger;

/** The NeoForge logger. */
public class NeoForgeLogger implements AbstractLogger {
    private final Logger logger;

    /**
     * Constructor for the Forge logger.
     *
     * @param logger The Forge logger
     */
    public NeoForgeLogger(Logger logger) {
        this.logger = logger;
    }

    /** {@inheritDoc} */
    @Override
    public void info(String message) {
        logger.info(message);
    }
}
