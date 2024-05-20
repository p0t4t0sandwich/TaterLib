package dev.neuralnexus.taterlib.v1_11.sponge;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.PluginInfo;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterlib.v1_11.sponge.event.command.SpongeCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_11.sponge.hooks.permissions.SpongePermissionsHook;
import dev.neuralnexus.taterlib.v1_11.sponge.listeners.block.SpongeBlockListener;
import dev.neuralnexus.taterlib.v1_11.sponge.listeners.entity.SpongeEntityListener;
import dev.neuralnexus.taterlib.v1_11.sponge.listeners.player.SpongePlayerListener;
import dev.neuralnexus.taterlib.v1_11.sponge.listeners.server.SpongeServerListener;
import dev.neuralnexus.taterlib.v1_11.sponge.server.SpongeServer;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.plugin.PluginContainer;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SpongeTaterLibPlugin implements TaterLibPlugin {
    private static PluginContainer container;

    /**
     * Gets the instance of the plugin
     *
     * @return The instance of the plugin
     */
    public static PluginContainer getInstance() {
        return container;
    }

    @Override
    public void platformInit(Object plugin, Object server, Object logger) {
        container = (PluginContainer) plugin;

        TaterAPIProvider.addHook(new SpongePermissionsHook());
        pluginStart(
                plugin, server, logger, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPI api = TaterAPIProvider.get(ServerType.SPONGE);
        api.setPluginList(
                () ->
                        Sponge.getPluginManager().getPlugins().stream()
                                .map(
                                        pluginContainer ->
                                                new PluginInfo(
                                                        pluginContainer.getId(),
                                                        pluginContainer
                                                                .getVersion()
                                                                .orElse("Unknown")))
                                .collect(Collectors.toList()));
        api.setServer(() -> new SpongeServer(Sponge.getServer()));
        TaterAPIProvider.setPrimaryServerType(ServerType.SPONGE);
    }

    @Override
    public void platformEnable() {
        if (TaterAPIProvider.isPrimaryServerType(ServerType.SPONGE)) {
            // Register listeners
            EventManager eventManager = Sponge.getEventManager();
            eventManager.registerListeners(container, new SpongeBlockListener());
            Sponge.getScheduler()
                    .createTaskBuilder()
                    .delay(10, TimeUnit.SECONDS)
                    .execute(
                            () ->
                                    CommandEvents.REGISTER_COMMAND.invoke(
                                            new SpongeCommandRegisterEvent()))
                    .submit(container);
            eventManager.registerListeners(container, new SpongeEntityListener());
            eventManager.registerListeners(container, new SpongePlayerListener());
            eventManager.registerListeners(container, new SpongeServerListener());
        }
    }

    @Override
    public void platformDisable() {
        pluginStop();
    }
}
