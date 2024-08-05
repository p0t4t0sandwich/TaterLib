/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_20_2.neoforge;

import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.server.SimpleServer;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.v1_20.vanilla.VanillaBootstrap;
import dev.neuralnexus.taterlib.v1_20_2.neoforge.hooks.permissions.NeoForgePermissionsHook;

import net.neoforged.neoforge.server.ServerLifecycleHooks;

public class NeoForgeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        VanillaBootstrap.init();
        TaterAPIProvider.addHook(new NeoForgePermissionsHook());
        start();
        TaterAPIProvider.api(Platform.NEOFORGE)
                .ifPresent(
                        api ->
                                api.setServer(
                                        () ->
                                                (SimpleServer)
                                                        ServerLifecycleHooks.getCurrentServer()));
    }

    @Override
    public void onDisable() {
        stop();
    }
}
