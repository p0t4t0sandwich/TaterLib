package dev.neuralnexus.taterlib.api.impl.metadata.fabric;

import dev.neuralnexus.taterlib.logger.Logger;
import dev.neuralnexus.taterlib.logger.impl.GenericSlf4jLogger;

import org.slf4j.LoggerFactory;

public class FabricLogger_18_21 {
    public static Logger logger(String pluginId) {
        return new GenericSlf4jLogger(LoggerFactory.getLogger(pluginId));
    }
}
