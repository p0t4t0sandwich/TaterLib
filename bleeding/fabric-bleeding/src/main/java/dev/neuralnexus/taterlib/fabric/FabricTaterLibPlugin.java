package dev.neuralnexus.taterlib.fabric;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.event.api.*;
import dev.neuralnexus.taterlib.event.plugin.CommonPluginEnableEvent;
import dev.neuralnexus.taterlib.fabric.hooks.permissions.FabricPermissionsHook;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterlib.vanilla.event.command.VanillaBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.vanilla.event.command.VanillaCommandRegisterEvent;
import dev.neuralnexus.taterlib.vanilla.server.VanillaServer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;

import org.apache.logging.log4j.LogManager;

public class FabricTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void platformInit(Object plugin, Object logger) {
        TaterAPIProvider.register();
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

        // Register Fabric API command events
        CommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess, environment) -> {
                    CommandEvents.REGISTER_COMMAND.invoke(
                            new VanillaCommandRegisterEvent(dispatcher, environment));
                    CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                            new VanillaBrigadierCommandRegisterEvent(dispatcher, environment));
                });
    }

    @Override
    public void platformEnable() {
        PluginEvents.ENABLED.invoke(new CommonPluginEnableEvent());
    }
}
