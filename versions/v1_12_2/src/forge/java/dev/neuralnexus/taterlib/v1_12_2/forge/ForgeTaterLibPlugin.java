/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12_2.forge;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.MinecraftVersions;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.v1_12_2.forge.listeners.player.ForgePlayerAdvancementListener;
import dev.neuralnexus.taterlib.v1_8_9.vanilla.VanillaBootstrap;
import dev.neuralnexus.taterlib.v1_9_4.forge.event.command.ForgeCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_9_4.forge.listeners.block.ForgeBlockListener;
import dev.neuralnexus.taterlib.v1_9_4.forge.listeners.entity.ForgeEntityListener;
import dev.neuralnexus.taterlib.v1_9_4.forge.listeners.player.ForgePlayerListener;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

/** Forge entry point. */
@AConstraint(
        platform = Platform.FORGE,
        version = @Versions(min = MinecraftVersion.V9, max = MinecraftVersion.V12_2))
public class ForgeTaterLibPlugin implements TaterLibPlugin {
    @ToBeLibrary("brigadier-general")
    @Mod.EventHandler
    public static void registerCommand(FMLServerStartingEvent event) {
        CommandEvents.REGISTER_COMMAND.invoke(new ForgeCommandRegisterEvent(event));
    }

    @Override
    public void onInit() {
        if (!TaterAPI.hasLoaded() && MetaAPI.instance().platform().isForge()) {
            TaterAPI.setLoaded(true);
            if (MetaAPI.instance().version().lessThan(MinecraftVersions.V8)) {
                dev.neuralnexus.taterlib.v1_7_10.vanilla.VanillaBootstrap.init();
            } else {
                VanillaBootstrap.init();
            }

            // Register listeners
            MinecraftForge.EVENT_BUS.register(this);
            MinecraftForge.EVENT_BUS.register(new ForgeBlockListener());
            MinecraftForge.EVENT_BUS.register(new ForgeEntityListener());
            MinecraftForge.EVENT_BUS.register(new ForgePlayerListener());
            if (MetaAPI.instance().version().lessThan(MinecraftVersions.V12)) {
                MinecraftForge.EVENT_BUS.register(
                        new dev.neuralnexus.taterlib.v1_9_4.forge.listeners.player
                                .ForgePlayerAdvancementListener());
            } else {
                MinecraftForge.EVENT_BUS.register(new ForgePlayerAdvancementListener());
            }
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
        TaterLibPlugin.super.onDisable();
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
