/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.platforms;

import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "tater_metadata", name = "TaterMetadata")
//        version = LoaderImpl.PROJECT_VERSION,
//        description = LoaderImpl.PROJECT_DESCRIPTION)
public class Sponge7Entrypoint {
    public Sponge7Entrypoint() {
        TaterMetadata.initSponge();
    }
}
