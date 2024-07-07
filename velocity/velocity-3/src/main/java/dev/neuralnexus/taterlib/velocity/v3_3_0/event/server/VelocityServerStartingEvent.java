/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.velocity.v3_3_0.event.server;

import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;

import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;

/** Velocity implementation of {@link ServerStartingEvent}. */
public class VelocityServerStartingEvent extends VelocityServerEvent
        implements ServerStartingEvent {
    private final ProxyInitializeEvent event;

    public VelocityServerStartingEvent(ProxyInitializeEvent event) {
        this.event = event;
    }
}
