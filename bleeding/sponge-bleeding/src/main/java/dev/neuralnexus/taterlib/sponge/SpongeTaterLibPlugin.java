package dev.neuralnexus.taterlib.sponge;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterlib.sponge.hooks.permissions.SpongePermissionsHook;
import dev.neuralnexus.taterlib.sponge.listeners.command.SpongeCommandListener;
import dev.neuralnexus.taterlib.sponge.server.SpongeServer;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.plugin.PluginContainer;

public class SpongeTaterLibPlugin implements TaterLibPlugin {
    private PluginContainer container;

    @Override
    public void platformInit(Object plugin, Object logger) {
        container = (PluginContainer) plugin;

        TaterAPIProvider.register();
        TaterAPIProvider.addHook(new SpongePermissionsHook());
        pluginStart(container, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPI api = TaterAPIProvider.get(ServerType.SPONGE);
        api.setIsPluginLoaded((pluginId) -> Sponge.pluginManager().plugin(pluginId).isPresent());
        api.setServer(() -> new SpongeServer(Sponge.server()));
    }

    @Override
    public void platformEnable() {
        // Register listeners
        EventManager eventManager = Sponge.eventManager();
        //        eventManager.registerListeners(container, new SpongeBlockListener());
        eventManager.registerListeners(container, new SpongeCommandListener());
        //        eventManager.registerListeners(container, new SpongeEntityListener());
        //        eventManager.registerListeners(container, new SpongePlayerListener());
        //        eventManager.registerListeners(container, new SpongeServerListener());
    }

    @Override
    public void platformDisable() {
        pluginStop();
    }
}
