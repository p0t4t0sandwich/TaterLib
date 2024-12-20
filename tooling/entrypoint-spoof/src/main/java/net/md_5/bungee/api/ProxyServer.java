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

    /**
     * Gets the name of the currently running proxy software.
     *
     * @return the name of this instance
     */
    public abstract String getName();

    /**
     * Gets the version of the currently running proxy software.
     *
     * @return the version of this instance
     */
    public abstract String getVersion();

    /**
     * Gets the main logger which can be used as a suitable replacement for {@link System#out} and
     * {@link System#err}.
     *
     * @return the {@link Logger} instance
     */
    public abstract Logger getLogger();

    /**
     * Get the Minecraft version supported by this proxy.
     *
     * @return the supported Minecraft version
     */
    @Deprecated
    public abstract String getGameVersion();

    /**
     * Get the Minecraft protocol version supported by this proxy.
     *
     * @return the Minecraft protocol version
     */
    @Deprecated
    public abstract int getProtocolVersion();

    /**
     * Get the {@link PluginManager} associated with loading plugins and dispatching events. It is
     * recommended that implementations use the provided PluginManager class.
     *
     * @return the plugin manager
     */
    public abstract PluginManager getPluginManager();
}
