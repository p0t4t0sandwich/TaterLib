/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.data.forge;

import dev.neuralnexus.modapi.metadata.logger.Logger;
import dev.neuralnexus.modapi.metadata.logger.impl.Slf4jLogger;

import org.slf4j.LoggerFactory;

public class ForgeLogger_182_21 {
    public static Logger logger(String pluginId) {
        return new Slf4jLogger(LoggerFactory.getLogger(pluginId));
    }
}
