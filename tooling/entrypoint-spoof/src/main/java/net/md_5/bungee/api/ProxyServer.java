package net.md_5.bungee.api;

import net.md_5.bungee.api.plugin.PluginManager;

/** Fake BungeeCord ProxyServer class. */
public abstract class ProxyServer {
    private static ProxyServer instance;

    public static ProxyServer getInstance() {
        return instance;
    }

    public abstract String getVersion();

    public abstract PluginManager getPluginManager();
}
