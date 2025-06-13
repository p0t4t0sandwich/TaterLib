package dev.neuralnexus.taterapi.meta.platforms;

import dev.neuralnexus.taterapi.meta.Platforms;

import org.spongepowered.plugin.builtin.jvm.Plugin;

@Plugin("tater_metadata")
public class Sponge8Entrypoint {
    public Sponge8Entrypoint() {
        TaterMetadata.init(Platforms.SPONGE);
    }
}
