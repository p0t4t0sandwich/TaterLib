package com.velocitypowered.api.event;

/** Fake Velocity EventManager interface to allow for event registration. */
public interface EventManager {
    void register(Object plugin, Object listener);
}
