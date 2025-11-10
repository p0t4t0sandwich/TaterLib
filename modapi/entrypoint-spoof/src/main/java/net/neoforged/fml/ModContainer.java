/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package net.neoforged.fml;

import net.neoforged.bus.api.IEventBus;

import net.neoforged.neoforgespi.language.IModInfo;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class ModContainer {
    private IModInfo modInfo;
    private String modId;

    public abstract @Nullable IEventBus getEventBus();

    public IModInfo getModInfo() {
        return this.modInfo;
    }

    public final String getModId() {
        return this.modId;
    }
}
