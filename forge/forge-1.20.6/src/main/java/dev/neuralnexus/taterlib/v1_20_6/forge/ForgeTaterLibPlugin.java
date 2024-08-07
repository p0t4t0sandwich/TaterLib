/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_20_6.forge;

import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.server.SimpleServer;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.v1_20_6.forge.hooks.permissions.ForgePermissionsHook;
import dev.neuralnexus.taterlib.v1_20_6.forge.listeners.block.ForgeBlockListener;
import dev.neuralnexus.taterlib.v1_20_6.forge.listeners.command.ForgeCommandsListener;
import dev.neuralnexus.taterlib.v1_20_6.forge.listeners.entity.ForgeEntityListener;
import dev.neuralnexus.taterlib.v1_20_6.forge.listeners.player.ForgePlayerListener;
import dev.neuralnexus.taterlib.v1_20_6.forge.listeners.server.ForgeServerListener;
import dev.neuralnexus.taterloader.TaterReflectUtil;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.server.ServerLifecycleHooks;

@SuppressWarnings("unused")
public class ForgeTaterLibPlugin implements TaterLibPlugin {
    @Override
    @SuppressWarnings("UnstableApiUsage")
    public void onInit() {
        TaterReflectUtil.getRelocatedClass("VanillaBootstrap")
                .ifPresent(
                        className -> {
                            try {
                                Class.forName(className).getMethod("init").invoke(null);
                            } catch (Exception e) {
                                TaterLib.logger()
                                        .error("Failed to boostrap Vanilla builders/factories", e);
                            }
                        });
        TaterAPIProvider.addHook(new ForgePermissionsHook());
        start();
        TaterAPIProvider.api(Platform.FORGE)
                .ifPresent(
                        api ->
                                api.setServer(
                                        () ->
                                                (SimpleServer)
                                                        ServerLifecycleHooks.getCurrentServer()));

        if (TaterAPIProvider.isPrimaryPlatform(Platform.FORGE)) {
            MinecraftForge.EVENT_BUS.register(this);
            MinecraftForge.EVENT_BUS.register(new ForgeBlockListener());
            MinecraftForge.EVENT_BUS.register(new ForgeCommandsListener());
            MinecraftForge.EVENT_BUS.register(new ForgeEntityListener());
            MinecraftForge.EVENT_BUS.register(new ForgePlayerListener());
            MinecraftForge.EVENT_BUS.register(new ForgeServerListener());
        }
    }

    @Override
    public void onDisable() {
        stop();
    }
}
