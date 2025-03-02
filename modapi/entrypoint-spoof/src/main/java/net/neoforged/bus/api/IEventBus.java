/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package net.neoforged.bus.api;

/** Fake event buss to allow registering NeoForge events.. */
public interface IEventBus {
    void register(Object target);
}
