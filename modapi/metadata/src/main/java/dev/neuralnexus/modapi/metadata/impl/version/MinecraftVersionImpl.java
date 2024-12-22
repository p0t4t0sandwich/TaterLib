/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.version;

import dev.neuralnexus.modapi.metadata.MinecraftVersion;

public final class MinecraftVersionImpl implements MinecraftVersion {
    private final String version;

    private MinecraftVersionImpl(String version) {
        this.version = version;
    }

    public static MinecraftVersionImpl of(String version) {
        return new MinecraftVersionImpl(version);
    }

    @Override
    public String asString() {
        return this.version;
    }

    @Override
    public String toString() {
        return this.version;
    }
}
