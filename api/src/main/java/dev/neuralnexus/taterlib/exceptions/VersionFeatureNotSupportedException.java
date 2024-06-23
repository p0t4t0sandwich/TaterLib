package dev.neuralnexus.taterlib.exceptions;

import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.api.Platform;

/** Exception thrown when a feature is not supported on the current Minecraft version. */
public class VersionFeatureNotSupportedException extends RuntimeException {
    private static final String message =
            "This feature is not supported on Minecraft "
                    + Platform.get().toString()
                    + " "
                    + MinecraftVersion.get().toString()
                    + "!"
                    + "\nIf you believe this may be an error, please report it to the plugin/mod developer using TaterLib!"
                    + "\nIf you are the plugin developer and believe this to be a missing implementation, please report this to the TaterLib GitHub repository!";

    public VersionFeatureNotSupportedException() {
        super(message);
    }
}
