/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLibLite/blob/main/LICENSE">MIT</a>
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
