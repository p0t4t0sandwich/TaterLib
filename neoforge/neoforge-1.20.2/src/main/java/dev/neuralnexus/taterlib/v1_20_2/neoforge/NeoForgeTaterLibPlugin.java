package dev.neuralnexus.taterlib.v1_20_2.neoforge;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterlib.v1_20.vanilla.server.VanillaServer;
import dev.neuralnexus.taterlib.v1_20_2.neoforge.hooks.permissions.NeoForgePermissionsHook;

import net.neoforged.fml.ModList;

public class NeoForgeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void platformInit(Object plugin, Object logger) {
        TaterAPIProvider.addHook(new NeoForgePermissionsHook());
        pluginStart(plugin, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPI api = TaterAPIProvider.get(ServerType.NEOFORGE);
        api.setIsModLoaded(ModList.get()::isLoaded);
        api.setServer(VanillaServer::getInstance);
    }

    @Override
    public void platformDisable() {
        pluginStop();
    }
}
