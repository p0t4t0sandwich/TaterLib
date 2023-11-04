package dev.neuralnexus.taterlib.bungee;

import dev.neuralnexus.taterlib.bungee.event.server.BungeeServerStartedEvent;
import dev.neuralnexus.taterlib.bungee.event.server.BungeeServerStartingEvent;
import dev.neuralnexus.taterlib.bungee.event.server.BungeeServerStoppedEvent;
import dev.neuralnexus.taterlib.bungee.event.server.BungeeServerStoppingEvent;
import dev.neuralnexus.taterlib.bungee.commands.BungeeTaterLibCommand;
import dev.neuralnexus.taterlib.bungee.listeners.player.BungeePlayerListener;
import dev.neuralnexus.taterlib.bungee.listeners.pluginmessages.BungeePluginMessageListener;
import dev.neuralnexus.taterlib.bungee.logger.BungeeLogger;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.util.concurrent.TimeUnit;

/**
 * The TaterLib BungeeCord plugin.
 */
public class BungeeTaterLibPlugin extends Plugin implements TaterLibPlugin {
    private static ProxyServer proxyServer;

    /**
     * Get the proxy server.
     * @return The proxy server.
     */
    public static ProxyServer getProxyServer() {
        return proxyServer;
    }

    @Override
    public void onEnable() {
        proxyServer = getProxy();
        pluginStart(this, new BungeeLogger(getLogger()));
        TaterLib.configFolder = "plugins";
        TaterLib.serverType = "BungeeCord";
        TaterLib.minecraftVersion = getProxy().getVersion();

        // Register LuckPerms hook
        if (getProxy().getPluginManager().getPlugin("LuckPerms") != null) {
            TaterLib.logger.info("LuckPerms detected, enabling LuckPerms hook.");
            TaterLib.addHook("luckperms", new LuckPermsHook());
        }

        PluginManager pluginManager = getProxy().getPluginManager();

        // Register commands
        pluginManager.registerCommand(this, new BungeeTaterLibCommand());

        // Register player listeners
        pluginManager.registerListener(this, new BungeePlayerListener());

        // Register plugin message listeners
        pluginManager.registerListener(this, new BungeePluginMessageListener());

        // Register server listeners
        ServerEvents.STARTING.invoke(new BungeeServerStartingEvent());
        getProxy().getScheduler().schedule(this, () -> ServerEvents.STARTED.invoke(new BungeeServerStartedEvent()), 5L, TimeUnit.SECONDS);
    }

    @Override
    public void onDisable() {
        // Run server stopping events
        ServerEvents.STOPPING.invoke(new BungeeServerStoppingEvent());
        ServerEvents.STOPPED.invoke(new BungeeServerStoppedEvent());
        pluginStop();
    }
}
