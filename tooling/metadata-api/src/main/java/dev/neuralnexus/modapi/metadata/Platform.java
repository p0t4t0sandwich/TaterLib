/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata;

public interface Platform {
    /**
     * Get the name of the platform.
     *
     * @return The name of the platform
     */
    String name();

    /**
     * Detect if the platform is available.
     *
     * @return True if the platform is available, false otherwise
     */
    boolean detect();
}
