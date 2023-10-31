package dev.neuralnexus.taterlib.common.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A generic implementation of the AbstractLogger interface.
 */
public class GenericLogger implements AbstractLogger {
    private final String prefix;
    private final Logger logger;

    public GenericLogger(String prefix, String MOD_ID) {
        this.prefix = prefix;
        this.logger = LoggerFactory.getLogger(MOD_ID);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void info(String message) {
        this.logger.info(this.prefix + message);
    }
}
