/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.platforms;

import com.velocitypowered.api.plugin.Plugin;

@Plugin(id = "tater_metadata", name = "TaterMetadata")
// version = LoaderImpl.PROJECT_VERSION,
// authors = LoaderImpl.PROJECT_AUTHORS,
// description = LoaderImpl.PROJECT_DESCRIPTION,
// url = LoaderImpl.PROJECT_URL)
public class VelocityEntrypoint {
    public VelocityEntrypoint() {
        TaterMetadata.initVelocity();
    }
}
