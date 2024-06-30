/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.api.impl.metadata.forge;

import dev.neuralnexus.taterlib.logger.Logger;
import dev.neuralnexus.taterlib.logger.impl.GenericApacheLogger;

import org.apache.logging.log4j.LogManager;

public class ForgeLogger_7_181 {
    public static Logger logger(String pluginId) {
        return new GenericApacheLogger(LogManager.getLogger(pluginId));
    }
}
