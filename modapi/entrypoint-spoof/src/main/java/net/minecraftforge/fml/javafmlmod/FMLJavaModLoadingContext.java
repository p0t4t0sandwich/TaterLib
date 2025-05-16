/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package net.minecraftforge.fml.javafmlmod;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;

public class FMLJavaModLoadingContext {
    public IEventBus getModEventBus() {
        return ModLoadingContext.get().getEventBus();
    }

    public static FMLJavaModLoadingContext get() {
        return ModLoadingContext.get().extension();
    }
}
