/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package net.neoforged.fml;

import net.neoforged.bus.api.IEventBus;

import org.jetbrains.annotations.Nullable;

public abstract class ModContainer {
    public abstract @Nullable IEventBus getEventBus();
}
