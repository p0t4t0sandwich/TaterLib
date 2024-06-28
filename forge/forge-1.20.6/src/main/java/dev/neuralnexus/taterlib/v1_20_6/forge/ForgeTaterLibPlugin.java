/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_20_6.forge;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.logger.impl.LoggerAdapter;
import dev.neuralnexus.taterlib.v1_20.vanilla.server.VanillaServer;
import dev.neuralnexus.taterlib.v1_20_6.forge.hooks.permissions.ForgePermissionsHook;

import net.minecraftforge.server.ServerLifecycleHooks;

public class ForgeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit(Object plugin, Object server, Object logger) {
        TaterAPIProvider.addHook(new ForgePermissionsHook());
        start(plugin, server, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPIProvider.api(Platform.FORGE)
                .ifPresent(
                        api ->
                                api.setServer(
                                        () ->
                                                new VanillaServer(
                                                        ServerLifecycleHooks.getCurrentServer())));
    }

    @Override
    public void onDisable() {
        stop();
    }
}
