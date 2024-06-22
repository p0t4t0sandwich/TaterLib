package com.velocitypowered.api.proxy;

import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.plugin.PluginManager;
import com.velocitypowered.api.util.ProxyVersion;

/** Fake Velocity ProxyServer interface to allow for dependency injection. */
public interface ProxyServer {
    EventManager getEventManager();

    PluginManager getPluginManager();

    ProxyVersion getVersion();
}
