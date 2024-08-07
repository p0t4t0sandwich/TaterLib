/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.metadata.impl;

import static dev.neuralnexus.taterapi.util.ReflectionUtil.checkForMethod;

import dev.neuralnexus.taterapi.metadata.PlatformData;

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
