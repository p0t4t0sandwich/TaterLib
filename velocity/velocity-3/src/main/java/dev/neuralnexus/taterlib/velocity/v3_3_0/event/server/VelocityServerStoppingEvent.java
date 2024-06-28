/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.velocity.v3_3_0.event.server;

import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;

import dev.neuralnexus.taterlib.event.server.ServerStoppingEvent;

/** Velocity implementation of {@link ServerStoppingEvent}. */
public class VelocityServerStoppingEvent extends VelocityServerEvent
        implements ServerStoppingEvent {
    private final ProxyShutdownEvent event;

    public VelocityServerStoppingEvent(ProxyShutdownEvent event) {
        this.event = event;
    }
}
