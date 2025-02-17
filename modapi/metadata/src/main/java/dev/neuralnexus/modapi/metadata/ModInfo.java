/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata;

import dev.neuralnexus.modapi.metadata.impl.version.VersionComparable;

public interface ModInfo extends VersionComparable<ModInfo> {
    /**
     * Get the mod id
     *
     * @return The mod id
     */
    String id();

    /**
     * Get the mod name
     *
     * @return The mod name
     */
    String name();

    /**
     * Get the mod asString
     *
     * @return The mod asString
     */
    String version();

    /**
     * Get the mod's platform
     *
     * @return The mod's platform
     */
    Platform platform();
}
