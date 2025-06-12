/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta;

import dev.neuralnexus.taterapi.meta.impl.version.VersionComparable;

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
