package dev.neuralnexus.taterlib.v1_20.fabric;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.event.api.*;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterlib.v1_20.fabric.hooks.permissions.FabricPermissionsHook;
import dev.neuralnexus.taterlib.v1_20.vanilla.server.VanillaServer;

import net.fabricmc.loader.api.FabricLoader;

import org.apache.logging.log4j.LogManager;

public class FabricTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void platformInit(Object plugin, Object logger) {
        TaterAPIProvider.addHook(new FabricPermissionsHook());
        pluginStart(
                plugin,
                new LoggerAdapter(
                        "[" + TaterLib.Constants.PROJECT_NAME + "] ",
                        TaterLib.Constants.PROJECT_ID,
                        LogManager.getLogger(TaterLib.Constants.PROJECT_ID)));
        TaterAPI api = TaterAPIProvider.get(ServerType.FABRIC);
        api.setIsModLoaded((modId) -> FabricLoader.getInstance().isModLoaded(modId));
        api.setServer(VanillaServer::getInstance);
    }
}
