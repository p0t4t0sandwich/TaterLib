/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.version;

import dev.neuralnexus.modapi.metadata.MinecraftVersion;

/** Implementation of {@link MinecraftVersion} */
public record MinecraftVersionImpl(String version) implements MinecraftVersion {
    public static MinecraftVersionImpl of(String version) {
        return new MinecraftVersionImpl(version);
    }

    @Override
    public String toString() {
        return this.version;
    }
}
