package dev.neuralnexus.taterlib.v1_13.sponge;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.PluginInfo;
import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.logger.impl.LoggerAdapter;
import dev.neuralnexus.taterlib.v1_13.sponge.hooks.permissions.SpongePermissionsHook;
import dev.neuralnexus.taterlib.v1_13.sponge.listeners.block.SpongeBlockListener;
import dev.neuralnexus.taterlib.v1_13.sponge.listeners.command.SpongeCommandListener;
import dev.neuralnexus.taterlib.v1_13.sponge.listeners.entity.SpongeEntityListener;
import dev.neuralnexus.taterlib.v1_13.sponge.listeners.player.SpongePlayerListener;
import dev.neuralnexus.taterlib.v1_13.sponge.listeners.server.SpongeServerListener;
import dev.neuralnexus.taterlib.v1_13.sponge.server.SpongeServer;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.plugin.PluginContainer;

import java.util.stream.Collectors;

public class SpongeTaterLibPlugin implements TaterLibPlugin {
    private PluginContainer container;

    @Override
    public void onInit(Object plugin, Object server, Object logger) {
        container = (PluginContainer) plugin;

        TaterAPIProvider.addHook(new SpongePermissionsHook());
        start(
                container,
                server,
                new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPI api = TaterAPIProvider.get(Platform.SPONGE);
        api.setServer(() -> new SpongeServer(Sponge.server()));
        TaterAPIProvider.setPrimaryServerType(Platform.SPONGE);
    }

    @Override
    public void onEnable() {
        if (TaterAPIProvider.isPrimaryServerType(Platform.SPONGE)) {
            // Register listeners
            EventManager eventManager = Sponge.eventManager();
            eventManager.registerListeners(container, new SpongeBlockListener());
            eventManager.registerListeners(container, new SpongeCommandListener());
            eventManager.registerListeners(container, new SpongeEntityListener());
            eventManager.registerListeners(container, new SpongePlayerListener());
            eventManager.registerListeners(container, new SpongeServerListener());
        }
    }

    @Override
    public void onDisable() {
        stop();
    }
}
