/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

import dev.neuralnexus.taterapi.meta.MetaAPI;

import org.jetbrains.annotations.ApiStatus;

/** Represents an object present on a server */
public interface ServerAware {
    /**
     * Get an instance of the server
     *
     * @return The server instance
     */
    // TODO: Give this a proper return type
    @ApiStatus.Experimental
    default Object server() {
        return MetaAPI.instance().server();
    }
}
