package dev.neuralnexus.taterapi.meta.platforms;

import org.spongepowered.plugin.builtin.jvm.Plugin;

@Plugin("tater_metadata")
public class Sponge8Entrypoint {
    public Sponge8Entrypoint() {
        TaterMetadata.initSponge();
    }
}
