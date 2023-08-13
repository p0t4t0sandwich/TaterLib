package dev.neuralnexus.taterlib.bungee;

import dev.neuralnexus.taterlib.bungee.abstractions.logger.BungeeLogger;
import dev.neuralnexus.taterlib.bungee.commands.BungeeTaterLibCommand;
import dev.neuralnexus.taterlib.bungee.listeners.player.BungeePlayerListener;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.common.listeners.server.ServerListener;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.PluginManager;

import java.util.concurrent.TimeUnit;

/**
 * The TaterLib BungeeCord plugin.
 */
public class BungeeTaterLibPlugin extends TemplateBungeePlugin implements TaterLibPlugin {
    private static ProxyServer proxyServer;

    /**
     * Get the proxy server.
     * @return The proxy server.
     */
    public static ProxyServer getProxyServer() {
        return proxyServer;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractLogger pluginLogger() {
        return new BungeeLogger(getLogger());
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
        return "BungeeCord";
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerHooks() {
        // Register LuckPerms hook
        if (getProxy().getPluginManager().getPlugin("LuckPerms") != null) {
            useLogger("LuckPerms detected, enabling LuckPerms hook.");
            TaterLib.addHook(new LuckPermsHook());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerEventListeners() {
        PluginManager pluginManager = getProxy().getPluginManager();

        // Register player listeners
        pluginManager.registerListener(this, new BungeePlayerListener());

        // Register server listeners
        ServerListener.onServerStarting();
        getProxy().getScheduler().schedule(this, ServerListener::onServerStarted, 5L, TimeUnit.SECONDS);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerCommands() {
        PluginManager pluginManager = getProxy().getPluginManager();
        pluginManager.registerCommand(this, new BungeeTaterLibCommand());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void onEnable() {
        proxyServer = getProxy();
        pluginStart();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void onDisable() {
        ServerListener.onServerStopping();
        ServerListener.onServerStopped();
        pluginStop();
    }
}
