package dev.neuralnexus.taterlib.api.impl.metadata.forge;

import dev.neuralnexus.taterlib.logger.Logger;
import dev.neuralnexus.taterlib.logger.impl.GenericSlf4jLogger;

import org.slf4j.LoggerFactory;

public class ForgeLogger_182_21 {
    public static Logger logger(String pluginId) {
        return new GenericSlf4jLogger(LoggerFactory.getLogger(pluginId));
    }
}
