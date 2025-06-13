package dev.neuralnexus.taterapi.meta.platforms;

import com.velocitypowered.api.plugin.Plugin;

import dev.neuralnexus.taterapi.meta.Platforms;

@Plugin(
        id = "tater_metadata",
        name = "TaterMetadata")
// version = LoaderImpl.PROJECT_VERSION,
// authors = LoaderImpl.PROJECT_AUTHORS,
// description = LoaderImpl.PROJECT_DESCRIPTION,
// url = LoaderImpl.PROJECT_URL)
public class VelocityEntrypoint {
    public VelocityEntrypoint() {
        TaterMetadata.init(Platforms.VELOCITY);
    }
}
