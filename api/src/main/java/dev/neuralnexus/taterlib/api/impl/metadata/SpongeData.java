/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.api.impl.metadata;

import static dev.neuralnexus.taterlib.utils.ReflectionUtil.checkForMethod;

import dev.neuralnexus.taterlib.api.PlatformData;

/** Stores data about the Fabric platform */
public class SpongeData {
    public static PlatformData create() {
        if (checkForMethod("org.spongepowered.api.Sponge", "platform")) {
            return new SpongeModernData();
        } else if (checkForMethod("org.spongepowered.api.Sponge", "getPlatform")) {
            return new SpongeLegacyData();
        } else {
            return null;
        }
    }
}
