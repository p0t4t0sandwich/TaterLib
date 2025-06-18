/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.platforms;

import org.spongepowered.plugin.builtin.jvm.Plugin;

@Plugin("tater_metadata")
public class Sponge8Entrypoint {
    public Sponge8Entrypoint() {
        TaterMetadata.initSponge();
    }
}
