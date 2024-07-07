/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_19.sponge.event.server;

import dev.neuralnexus.taterapi.event.server.ServerEvent;
import dev.neuralnexus.taterapi.server.SimpleServer;
import dev.neuralnexus.taterlib.v1_19.sponge.server.SpongeServer;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.lifecycle.LifecycleEvent;

/** Sponge implementation of {@link ServerEvent}. */
public class SpongeServerEvent implements ServerEvent {
    private final LifecycleEvent event;

    public SpongeServerEvent(LifecycleEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public SimpleServer server() {
        return new SpongeServer(Sponge.server());
    }
}
