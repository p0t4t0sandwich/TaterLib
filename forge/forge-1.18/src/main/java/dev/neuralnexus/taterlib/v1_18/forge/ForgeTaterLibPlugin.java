/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_18.forge;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.logger.impl.LoggerAdapter;
import dev.neuralnexus.taterlib.v1_18.forge.hooks.permissions.ForgePermissionsHook;
import dev.neuralnexus.taterlib.v1_18.forge.listeners.block.ForgeBlockListener;
import dev.neuralnexus.taterlib.v1_18.forge.listeners.command.ForgeCommandsListener;
import dev.neuralnexus.taterlib.v1_18.forge.listeners.entity.ForgeEntityListener;
import dev.neuralnexus.taterlib.v1_18.forge.listeners.player.ForgePlayerListener;
import dev.neuralnexus.taterlib.v1_18.forge.listeners.server.ForgeServerListener;
import dev.neuralnexus.taterlib.v1_18.vanilla.server.VanillaServer;
import dev.neuralnexus.taterlib.v1_18_2.forge.hooks.permissions.ForgePermissionsHook_1_18_2;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

import org.apache.logging.log4j.LogManager;

public class ForgeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit(Object plugin, Object server, Object logger) {
        MinecraftVersion mcv = TaterAPIProvider.minecraftVersion();
        if (mcv.isInRange(MinecraftVersion.V1_18, MinecraftVersion.V1_18_1)) {
            TaterAPIProvider.addHook(new ForgePermissionsHook());
        } else {
            TaterAPIProvider.addHook(new ForgePermissionsHook_1_18_2());
        }
        start(
                plugin,
                server,
                new LoggerAdapter(TaterLib.Constants.PROJECT_ID, LogManager.getLogger()));
        TaterAPIProvider.api(Platform.FORGE)
                .ifPresent(
                        api ->
                                api.setServer(
                                        () ->
                                                new VanillaServer(
                                                        ServerLifecycleHooks.getCurrentServer())));

        if (TaterAPIProvider.isPrimaryPlatform(Platform.FORGE)) {
            // Register listeners
            MinecraftForge.EVENT_BUS.register(this);
            MinecraftForge.EVENT_BUS.register(new ForgeBlockListener());
            MinecraftForge.EVENT_BUS.register(new ForgeCommandsListener());
            MinecraftForge.EVENT_BUS.register(new ForgeEntityListener());
            MinecraftForge.EVENT_BUS.register(new ForgePlayerListener());
            MinecraftForge.EVENT_BUS.register(new ForgeServerListener());
        }
    }

    /**
     * Called when the server is stopping.
     *
     * @param event The event.
     */
    @SubscribeEvent
    public void onServerStopped(ServerStoppedEvent event) {
        stop();
    }
}
