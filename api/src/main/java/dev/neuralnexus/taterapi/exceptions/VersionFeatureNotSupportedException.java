/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.exceptions;

import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.Platform;

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
