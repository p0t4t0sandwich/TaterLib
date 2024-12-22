/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package org.spongepowered.api;

/** Fake Sponge interface */
public interface MinecraftVersion extends Comparable<MinecraftVersion> {
    String name();

    String getName();

    int protocolVersion();

    boolean isLegacy();
}
