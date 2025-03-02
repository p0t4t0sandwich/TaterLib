/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package com.velocitypowered.api.event;

/** Fake Velocity EventManager interface to allow for event registration. */
public interface EventManager {
    void register(Object plugin, Object listener);
}
