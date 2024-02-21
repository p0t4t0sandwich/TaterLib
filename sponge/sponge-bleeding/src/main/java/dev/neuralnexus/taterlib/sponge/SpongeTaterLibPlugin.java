package dev.neuralnexus.taterlib.sponge;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.PluginInfo;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterlib.sponge.hooks.permissions.SpongePermissionsHook;
import dev.neuralnexus.taterlib.sponge.listeners.command.SpongeCommandListener;
import dev.neuralnexus.taterlib.utils.VanillaServerReflect;

import org.spongepowered.api.Sponge;
import org.spongepowered.plugin.PluginContainer;

import java.util.stream.Collectors;

public class SpongeTaterLibPlugin implements TaterLibPlugin {
    private PluginContainer container;

    @Override
    public void platformInit(Object plugin, Object server, Object logger) {
        container = (PluginContainer) plugin;
        TaterAPIProvider.addHook(new SpongePermissionsHook());
        pluginStart(
                plugin, server, logger, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPI api = TaterAPIProvider.get(ServerType.SPONGE);
        api.setPluginList(
                () ->
                        Sponge.pluginManager().plugins().stream()
                                .map(
                                        pluginContainer ->
                                                new PluginInfo(
                                                        pluginContainer.metadata().id(),
                                                        pluginContainer
                                                                .metadata()
                                                                .version()
                                                                .toString()))
                                .collect(Collectors.toSet()));
        api.setServer(VanillaServerReflect::instance);
        TaterAPIProvider.setPrimaryServerType(ServerType.SPONGE);
    }

    @Override
    public void platformEnable() {
        if (TaterAPIProvider.isPrimaryServerType(ServerType.SPONGE)) {
            Sponge.eventManager().registerListeners(container, new SpongeCommandListener());
        }
    }

    @Override
    public void platformDisable() {
        pluginStop();
    }
}
