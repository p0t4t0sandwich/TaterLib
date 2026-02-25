/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.loader.plugin;

import dev.neuralnexus.taterapi.loader.Entrypoint;

import org.jspecify.annotations.NonNull;

public interface Plugin extends Entrypoint {
    @NonNull String id();

    default @NonNull String name() {
        return this.id();
    }
}
