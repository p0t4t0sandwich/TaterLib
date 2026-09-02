/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.neoforged.neoforge.common;

import net.neoforged.bus.api.IEventBus;

/** Class to spoof the main NeoForge class and Event Bus. */
public class NeoForge {
    public static final IEventBus EVENT_BUS = target -> {};
}
