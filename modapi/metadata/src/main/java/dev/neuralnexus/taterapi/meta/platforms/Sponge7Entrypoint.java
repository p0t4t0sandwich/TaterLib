package dev.neuralnexus.taterapi.meta.platforms;

import org.spongepowered.api.plugin.Plugin;

@Plugin(
        id = "tater_metadata",
        name = "TaterMetadata")
//        version = LoaderImpl.PROJECT_VERSION,
//        description = LoaderImpl.PROJECT_DESCRIPTION)
public class Sponge7Entrypoint {
    public Sponge7Entrypoint() {
        TaterMetadata.initSponge();
    }
}
