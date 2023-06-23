package dev.neuralnexus.taterapi.velocity;

import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import dev.neuralnexus.taterapi.common.TaterAPIPlugin;
import dev.neuralnexus.taterapi.common.commands.TaterAPICommand;
import dev.neuralnexus.taterapi.velocity.commands.VelocityTaterAPICommand;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.neuralnexus.taterapi.velocity.listeners.player.VelocityPlayerLoginListener;
import dev.neuralnexus.taterapi.velocity.listeners.player.VelocityPlayerLogoutListener;
import dev.neuralnexus.taterapi.velocity.listeners.player.VelocityPlayerMessageListener;
import dev.neuralnexus.taterapi.velocity.listeners.player.VelocityPlayerServerSwitchListener;
import org.slf4j.Logger;

/**
 * The TaterAPI Velocity plugin.
 */
@Plugin(
        id = "taterapi",
        name = "TaterAPI",
        version = "1.0.0",
        authors = "p0t4t0sandwich",
        description = "A cross API code library for various generalizations used in the Tater* plugins",
        url = "https://github.com/p0t4t0sandwich/TaterAPI",
        dependencies = {}
)
public class VelocityTaterAPIPlugin implements TaterAPIPlugin {
    @Inject private ProxyServer server;
    @Inject private Logger logger;

    /**
     * @inheritDoc
     */
    @Override
    public Object pluginLogger() {
        return logger;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String pluginConfigPath() {
        return "plugins";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getServerType() {
        return "Velocity";
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerEventListeners() {
        EventManager eventManager = server.getEventManager();
        eventManager.register(this, new VelocityPlayerLoginListener());
        eventManager.register(this, new VelocityPlayerLogoutListener());
        eventManager.register(this, new VelocityPlayerMessageListener());
        eventManager.register(this, new VelocityPlayerServerSwitchListener());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerCommands() {
        CommandManager commandManager = server.getCommandManager();
        commandManager.register(TaterAPICommand.commandName, new VelocityTaterAPICommand());
    }

    /**
     * Called when the proxy is initialized.
     * @param event The event.
     */
    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        pluginStart();
    }

    /**
     * Called when the proxy is shutting down.
     * @param event The event.
     */
    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        pluginStop();
    }
}
