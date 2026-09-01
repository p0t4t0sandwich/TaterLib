/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.fabricmc.api;

/** Fake Fabric interface to simplify the creation of entrypoints. */
@FunctionalInterface
public interface DedicatedServerModInitializer {
    void onInitializeServer();
}
