package dev.neuralnexus.taterlib.v1_19.fabric;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ModInfo;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterlib.v1_19.fabric.hooks.permissions.FabricPermissionsHook;
import dev.neuralnexus.taterlib.v1_19.vanilla.server.VanillaServer;

import net.fabricmc.loader.api.FabricLoader;

import org.apache.logging.log4j.LogManager;

import java.util.stream.Collectors;

public class FabricTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void platformInit(Object plugin, Object server, Object logger) {
        TaterAPIProvider.addHook(new FabricPermissionsHook());
        pluginStart(
                plugin,
                server,
                logger,
                new LoggerAdapter(
                        "[" + TaterLib.Constants.PROJECT_NAME + "] ",
                        TaterLib.Constants.PROJECT_ID,
                        LogManager.getLogger(TaterLib.Constants.PROJECT_ID)));
        TaterAPI api = TaterAPIProvider.get(ServerType.FABRIC);
        api.setModList(
                () ->
                        FabricLoader.getInstance().getAllMods().stream()
                                .map(
                                        modContainer ->
                                                new ModInfo(
                                                        modContainer.getMetadata().getId(),
                                                        modContainer.getMetadata().getName(),
                                                        modContainer
                                                                .getMetadata()
                                                                .getVersion()
                                                                .getFriendlyString()))
                                .collect(Collectors.toSet()));
        api.setServer(VanillaServer::instance);
    }
}
