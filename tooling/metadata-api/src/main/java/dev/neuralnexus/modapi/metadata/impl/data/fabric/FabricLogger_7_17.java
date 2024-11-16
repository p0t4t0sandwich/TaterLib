/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.data.fabric;

import dev.neuralnexus.modapi.metadata.logger.Logger;
import dev.neuralnexus.modapi.metadata.logger.impl.ApacheLogger;

import org.apache.logging.log4j.LogManager;

public class FabricLogger_7_17 {
    public static Logger logger(String pluginId) {
        return new ApacheLogger(LogManager.getLogger(pluginId));
    }
}
