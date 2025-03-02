/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package net.neoforged.neoforge.common;

import net.neoforged.bus.api.IEventBus;

/** Class to spoof the main NeoForge class and Event Bus. */
public class NeoForge {
    public static final IEventBus EVENT_BUS = target -> {};
}
