package dev.neuralnexus.taterlib.v1_20_6.forge;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ModInfo;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterlib.v1_20.vanilla.server.VanillaServer;
import dev.neuralnexus.taterlib.v1_20_6.forge.hooks.permissions.ForgePermissionsHook;

import net.minecraftforge.fml.ModList;

import java.util.stream.Collectors;

public class ForgeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void platformInit(Object plugin, Object server, Object logger) {
        TaterAPIProvider.addHook(new ForgePermissionsHook());
        pluginStart(
                plugin, server, logger, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPI api = TaterAPIProvider.get(ServerType.NEOFORGE);
        api.setModList(
                () ->
                        ModList.get().getMods().stream()
                                .map(
                                        modContainer ->
                                                new ModInfo(
                                                        modContainer.getModId(),
                                                        modContainer.getDisplayName(),
                                                        modContainer.getVersion().toString()))
                                .collect(Collectors.toList()));
        api.setServer(VanillaServer::instance);
    }

    @Override
    public void platformDisable() {
        pluginStop();
    }
}
