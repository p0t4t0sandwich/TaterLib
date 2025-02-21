/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform.meta.sponge;

import static dev.neuralnexus.taterapi.meta.impl.util.ReflectionUtil.checkForMethod;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.Platform;

/** Stores data about the Fabric platform */
public final class SpongeData {
    public static Platform.Meta create() {
        if (checkForMethod("org.spongepowered.api.Sponge", "platform")) {
            return new SpongeModernMeta();
        } else if (checkForMethod("org.spongepowered.api.Sponge", "getPlatform")) {
            return new SpongeLegacyMeta();
        } else {
            return null;
        }
    }
}
