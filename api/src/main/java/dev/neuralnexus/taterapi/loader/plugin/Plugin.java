/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
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
