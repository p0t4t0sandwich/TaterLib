package net.neoforged.neoforge.common;

import net.neoforged.bus.api.IEventBus;

/** Class to spoof the main NeoForge class and Event Bus. */
public class NeoForge {
    public static final IEventBus EVENT_BUS = target -> {};
}
