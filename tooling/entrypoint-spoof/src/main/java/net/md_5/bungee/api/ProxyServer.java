/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package net.md_5.bungee.api;

import net.md_5.bungee.api.plugin.PluginManager;

import java.util.logging.Logger;

/** Fake BungeeCord ProxyServer class. */
public abstract class ProxyServer {
    private static ProxyServer instance;

    public static ProxyServer getInstance() {
        return instance;
    }

    public abstract String getVersion();

    public abstract PluginManager getPluginManager();

    public abstract Logger getLogger();
}
