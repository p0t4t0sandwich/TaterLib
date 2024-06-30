package dev.neuralnexus.taterlib.api.impl.metadata.fabric;

import dev.neuralnexus.taterlib.logger.Logger;
import dev.neuralnexus.taterlib.logger.impl.GenericApacheLogger;

import org.apache.logging.log4j.LogManager;

public class FabricLogger_7_17 {
    public static Logger logger(String pluginId) {
        return new GenericApacheLogger(LogManager.getLogger(pluginId));
    }
}
