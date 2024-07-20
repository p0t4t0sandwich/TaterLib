/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_11.sponge.event.server;

import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;

import org.spongepowered.api.event.game.state.GameStartedServerEvent;

/** Sponge implementation of {@link ServerStartedEvent}. */
public class SpongeServerStartedEvent extends SpongeServerEvent implements ServerStartedEvent {
    public SpongeServerStartedEvent(GameStartedServerEvent event) {
        super(event);
    }
}
