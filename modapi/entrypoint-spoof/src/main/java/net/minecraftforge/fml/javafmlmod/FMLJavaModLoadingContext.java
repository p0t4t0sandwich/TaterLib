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
