package dev.neuralnexus.taterapi.bungee;

import dev.neuralnexus.taterapi.bungee.commands.BungeeTaterAPICommand;
import dev.neuralnexus.taterapi.bungee.listeners.player.BungeePlayerLoginListener;
import dev.neuralnexus.taterapi.bungee.listeners.player.BungeePlayerLogoutListener;
import dev.neuralnexus.taterapi.bungee.listeners.player.BungeePlayerMessageListener;
import dev.neuralnexus.taterapi.bungee.listeners.player.BungeePlayerServerSwitchListener;
import dev.neuralnexus.taterapi.common.TaterAPIPlugin;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

/**
 * The TaterAPI BungeeCord plugin.
 */
public class BungeeTaterAPIPlugin extends Plugin implements TaterAPIPlugin {
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
    public Object pluginLogger() {
        return getLogger();
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
    public void registerEventListeners() {
        PluginManager pluginManager = getProxy().getPluginManager();
        pluginManager.registerListener(this, new BungeePlayerLoginListener());
        pluginManager.registerListener(this, new BungeePlayerLogoutListener());
        pluginManager.registerListener(this, new BungeePlayerMessageListener());
        pluginManager.registerListener(this, new BungeePlayerServerSwitchListener());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerCommands() {
        PluginManager pluginManager = getProxy().getPluginManager();
        pluginManager.registerCommand(this, new BungeeTaterAPICommand());
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
        pluginStop();
    }
}
