/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.api.impl.metadata.forge;

import dev.neuralnexus.taterlib.logger.Logger;
import dev.neuralnexus.taterlib.logger.impl.GenericSlf4jLogger;

import org.slf4j.LoggerFactory;

public class ForgeLogger_182_21 {
    public static Logger logger(String pluginId) {
        return new GenericSlf4jLogger(LoggerFactory.getLogger(pluginId));
    }
}
