/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.version;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;

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
