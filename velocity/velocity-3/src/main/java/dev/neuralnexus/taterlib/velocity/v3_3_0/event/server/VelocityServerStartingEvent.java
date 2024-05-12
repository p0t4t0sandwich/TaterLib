package dev.neuralnexus.taterlib.velocity.v3_3_0.event.server;

import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;

import dev.neuralnexus.taterlib.event.server.ServerStartingEvent;

/** Velocity implementation of {@link ServerStartingEvent}. */
public class VelocityServerStartingEvent extends VelocityServerEvent
        implements ServerStartingEvent {
    private final ProxyInitializeEvent event;

    public VelocityServerStartingEvent(ProxyInitializeEvent event) {
        this.event = event;
    }
}
