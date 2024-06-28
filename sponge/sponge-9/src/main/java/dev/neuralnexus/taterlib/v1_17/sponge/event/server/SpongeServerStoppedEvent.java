/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_17.sponge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStoppedEvent;

import org.spongepowered.api.event.lifecycle.StoppedGameEvent;

/** Sponge implementation of {@link ServerStoppedEvent}. */
public class SpongeServerStoppedEvent extends SpongeServerEvent implements ServerStoppedEvent {
    public SpongeServerStoppedEvent(StoppedGameEvent event) {
        super(event);
    }
}
