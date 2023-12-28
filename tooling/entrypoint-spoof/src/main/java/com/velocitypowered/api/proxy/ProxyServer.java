package com.velocitypowered.api.proxy;

import com.velocitypowered.api.event.EventManager;

/** Fake Velocity ProxyServer interface to allow for dependency injection. */
public interface ProxyServer {
    EventManager getEventManager();
}
