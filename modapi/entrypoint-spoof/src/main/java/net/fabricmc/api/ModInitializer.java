/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package net.fabricmc.api;

/** Fake Fabric interface to simplify the creation of entrypoints. */
@FunctionalInterface
public interface ModInitializer {
    void onInitialize();
}
