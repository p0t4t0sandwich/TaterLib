package dev.neuralnexus.taterlib.v1_20_2.neoforge;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.logger.impl.LoggerAdapter;
import dev.neuralnexus.taterlib.v1_20.vanilla.server.VanillaServer;
import dev.neuralnexus.taterlib.v1_20_2.neoforge.hooks.permissions.NeoForgePermissionsHook;

public class NeoForgeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit(Object plugin, Object server, Object logger) {
        TaterAPIProvider.setPrimaryServerType(Platform.NEOFORGE);
        TaterAPIProvider.addHook(new NeoForgePermissionsHook());
        start(plugin, server, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPI api = TaterAPIProvider.api(Platform.NEOFORGE);
        api.setServer(VanillaServer::instance);
    }

    @Override
    public void onDisable() {
        stop();
    }
}
