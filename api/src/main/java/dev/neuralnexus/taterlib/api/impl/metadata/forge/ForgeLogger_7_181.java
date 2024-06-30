package dev.neuralnexus.taterlib.api.impl.metadata.forge;

import dev.neuralnexus.taterlib.logger.Logger;
import dev.neuralnexus.taterlib.logger.impl.GenericApacheLogger;

import org.apache.logging.log4j.LogManager;

public class ForgeLogger_7_181 {
    public static Logger logger(String pluginId) {
        return new GenericApacheLogger(LogManager.getLogger(pluginId));
    }
}
