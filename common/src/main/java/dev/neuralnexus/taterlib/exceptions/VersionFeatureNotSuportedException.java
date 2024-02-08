package dev.neuralnexus.taterlib.exceptions;

import dev.neuralnexus.taterlib.api.info.MinecraftVersion;

/** Exception thrown when a feature is not supported on the current Minecraft version. */
public class VersionFeatureNotSuportedException extends RuntimeException {
    private static final String message =
            "This feature is not supported on Minecraft "
                    + MinecraftVersion.getMinecraftVersion().toString()
                    + "!";

    public VersionFeatureNotSuportedException() {
        super(message);
    }
}
