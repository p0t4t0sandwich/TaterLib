/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_11_2.forge;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.v1_11_2.forge.event.command.ForgeCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_11_2.forge.listeners.block.ForgeBlockListener;
import dev.neuralnexus.taterlib.v1_11_2.forge.listeners.entity.ForgeEntityListener;
import dev.neuralnexus.taterlib.v1_11_2.forge.listeners.player.ForgePlayerListener;
import dev.neuralnexus.taterlib.v1_11_2.forge.resources.ForgeResourceKey;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

/** Forge entry point. */
@SuppressWarnings("unused")
public class ForgeTaterLibPlugin implements TaterLibPlugin {
    @Mod.EventHandler
    public static void registerCommand(FMLServerStartingEvent event) {
        CommandEvents.REGISTER_COMMAND.invoke(new ForgeCommandRegisterEvent(event));
    }

    @Override
    public void onInit() {
        TaterAPIProvider.registerBuilder(ResourceKey.Builder.class, ForgeResourceKey.Builder::new);
        TaterAPIProvider.registerFactory(ResourceKey.Factory.class, ForgeResourceKey.Factory::new);

        if (MetaAPI.instance().isPrimaryPlatform(Platforms.FORGE)) {
            // Register listeners
            MinecraftForge.EVENT_BUS.register(this);
            MinecraftForge.EVENT_BUS.register(new ForgeBlockListener());
            MinecraftForge.EVENT_BUS.register(new ForgeEntityListener());
            MinecraftForge.EVENT_BUS.register(new ForgePlayerListener());
            //        MinecraftForge.EVENT_BUS.register(new ForgeServerListener());
        }
    }

    /**
     * Called when the server is stopping.
     *
     * @param event The event.
     */
    @Mod.EventHandler
    public void onServerStopped(FMLServerStoppedEvent event) {
        TaterLib.stop();
    }

    // ----------------------------- Relocated Server listeners -----------------------------

    /**
     * Called when the server starts.
     *
     * @param event The server starting event
     */
    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        ServerEvents.STARTING.invoke(new ServerStartingEvent() {});
    }

    /**
     * Called when the server starts.
     *
     * @param event The server started event
     */
    @Mod.EventHandler
    public void onServerStarted2(FMLServerStartedEvent event) {
        ServerEvents.STARTED.invoke(new ServerStartedEvent() {});
    }

    /**
     * Called when the server stops.
     *
     * @param event The server stopping event
     */
    @Mod.EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        ServerEvents.STOPPING.invoke(new ServerStoppingEvent() {});
    }

    /**
     * Called when the server stops.
     *
     * @param event The server stopped event
     */
    @Mod.EventHandler
    public void onServerStopped2(FMLServerStoppedEvent event) {
        ServerEvents.STOPPED.invoke(new ServerStoppedEvent() {});
    }
}
