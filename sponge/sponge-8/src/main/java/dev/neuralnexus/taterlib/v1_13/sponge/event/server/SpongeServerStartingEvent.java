/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_13.sponge.event.server;

import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;

import org.spongepowered.api.Server;
import org.spongepowered.api.event.lifecycle.StartingEngineEvent;

/** Sponge implementation of {@link ServerStartingEvent}. */
public class SpongeServerStartingEvent extends SpongeServerEvent implements ServerStartingEvent {
    public SpongeServerStartingEvent(StartingEngineEvent<Server> event) {
        super(event);
    }
}
