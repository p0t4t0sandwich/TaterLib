/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

import java.util.UUID;

/** Represents an object identifiable via a UUID */
public interface Identifiable {
    /**
     * Get the UUID of the object
     *
     * @return The UUID of the object
     */
    UUID uuid();
}
