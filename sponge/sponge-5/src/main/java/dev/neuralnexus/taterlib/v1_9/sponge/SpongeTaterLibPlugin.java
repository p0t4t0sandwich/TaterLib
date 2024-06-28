package dev.neuralnexus.taterlib.v1_9.sponge;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.logger.impl.LoggerAdapter;
import dev.neuralnexus.taterlib.v1_9.sponge.event.command.SpongeCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_9.sponge.hooks.permissions.SpongePermissionsHook;
import dev.neuralnexus.taterlib.v1_9.sponge.listeners.block.SpongeBlockListener;
import dev.neuralnexus.taterlib.v1_9.sponge.listeners.entity.SpongeEntityListener;
import dev.neuralnexus.taterlib.v1_9.sponge.listeners.player.SpongePlayerListener;
import dev.neuralnexus.taterlib.v1_9.sponge.listeners.server.SpongeServerListener;
import dev.neuralnexus.taterlib.v1_9.sponge.server.SpongeServer;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.plugin.PluginContainer;

import java.util.concurrent.TimeUnit;

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
    public void onInit(Object plugin, Object server, Object logger) {
        container = (PluginContainer) plugin;

        TaterAPIProvider.addHook(new SpongePermissionsHook());
        start(plugin, server, new LoggerAdapter(TaterLib.Constants.PROJECT_ID, logger));
        TaterAPIProvider.api(Platform.SPONGE)
                .ifPresent(api -> api.setServer(() -> new SpongeServer(Sponge.getServer())));
    }

    @Override
    public void onEnable() {
        if (TaterAPIProvider.isPrimaryPlatform(Platform.SPONGE)) {
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
    public void onDisable() {
        stop();
    }
}
