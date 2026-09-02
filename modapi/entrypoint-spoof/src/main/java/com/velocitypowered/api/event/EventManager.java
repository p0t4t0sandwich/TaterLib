/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package com.velocitypowered.api.event;

/** Fake Velocity EventManager interface to allow for event registration. */
public interface EventManager {
    void register(Object plugin, Object listener);
}
