package dev.neuralnexus.taterlib.bungee;

import dev.neuralnexus.taterlib.bungee.event.command.BungeeCommandRegisterEvent;
import dev.neuralnexus.taterlib.bungee.event.server.BungeeServerStartedEvent;
import dev.neuralnexus.taterlib.bungee.event.server.BungeeServerStartingEvent;
import dev.neuralnexus.taterlib.bungee.event.server.BungeeServerStoppedEvent;
import dev.neuralnexus.taterlib.bungee.event.server.BungeeServerStoppingEvent;
import dev.neuralnexus.taterlib.bungee.listeners.player.BungeePlayerListener;
import dev.neuralnexus.taterlib.bungee.listeners.pluginmessages.BungeePluginMessageListener;
import dev.neuralnexus.taterlib.bungee.logger.BungeeLogger;
import dev.neuralnexus.taterlib.bungee.server.BungeeProxyServer;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.api.TaterAPI;
import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.api.info.ServerType;
import dev.neuralnexus.taterlib.common.event.api.CommandEvents;
import dev.neuralnexus.taterlib.common.event.api.PluginEvents;
import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.common.event.plugin.CommonPluginEnableEvent;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.util.concurrent.TimeUnit;

/** Bungee entry point. */
public class BungeeTaterLibPlugin extends Plugin implements TaterLibPlugin {
    private static ProxyServer proxyServer;

    public BungeeTaterLibPlugin() {
        TaterAPIProvider.register(getProxy().getVersion());
        pluginStart(this, new BungeeLogger(getLogger()));
        TaterAPI api = TaterAPIProvider.get(ServerType.BUNGEECORD);
        api.setIsPluginLoaded((plugin) -> getProxy().getPluginManager().getPlugin(plugin) != null);
        api.setServer(() -> new BungeeProxyServer(getProxy()));
    }

    /**
     * Get the proxy server.
     *
     * @return The proxy server.
     */
    public static ProxyServer getProxyServer() {
        return proxyServer;
    }

    @Override
    public void onEnable() {
        PluginEvents.ENABLED.invoke(new CommonPluginEnableEvent());
        proxyServer = getProxy();

        // Register listeners
        PluginManager pluginManager = getProxy().getPluginManager();
        getProxy()
                .getScheduler()
                .schedule(
                        this,
                        () ->
                                CommandEvents.REGISTER_COMMAND.invoke(
                                        new BungeeCommandRegisterEvent()),
                        5L,
                        TimeUnit.SECONDS);
        pluginManager.registerListener(this, new BungeePlayerListener());
        pluginManager.registerListener(this, new BungeePluginMessageListener());
        ServerEvents.STARTING.invoke(new BungeeServerStartingEvent());
        getProxy()
                .getScheduler()
                .schedule(
                        this,
                        () -> ServerEvents.STARTED.invoke(new BungeeServerStartedEvent()),
                        5L,
                        TimeUnit.SECONDS);
    }

    @Override
    public void onDisable() {
        // Run server stopping events
        ServerEvents.STOPPING.invoke(new BungeeServerStoppingEvent());
        ServerEvents.STOPPED.invoke(new BungeeServerStoppedEvent());
        pluginStop();
    }
}
