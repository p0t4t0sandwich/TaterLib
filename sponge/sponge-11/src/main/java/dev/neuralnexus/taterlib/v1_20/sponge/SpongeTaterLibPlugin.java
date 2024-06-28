package dev.neuralnexus.taterlib.v1_20.sponge;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.logger.impl.LoggerAdapter;
import dev.neuralnexus.taterlib.utils.VanillaServerReflect;
import dev.neuralnexus.taterlib.v1_20.sponge.hooks.permissions.SpongePermissionsHook;
import dev.neuralnexus.taterlib.v1_20.sponge.listeners.command.SpongeCommandListener;

import org.spongepowered.api.Sponge;
import org.spongepowered.plugin.PluginContainer;

public class SpongeTaterLibPlugin implements TaterLibPlugin {
    private PluginContainer container;

    @Override
    public void onInit(Object plugin, Object server, Object logger) {
        container = (PluginContainer) plugin;
        TaterAPIProvider.addHook(new SpongePermissionsHook());
        start(plugin, server, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPI api = TaterAPIProvider.api(Platform.SPONGE);
        api.setServer(VanillaServerReflect::instance);
        TaterAPIProvider.setPrimaryServerType(Platform.SPONGE);
    }

    @Override
    public void onEnable() {
        if (TaterAPIProvider.isPrimaryServerType(Platform.SPONGE)) {
            Sponge.eventManager().registerListeners(container, new SpongeCommandListener());
        }
    }

    @Override
    public void onDisable() {
        stop();
    }
}
