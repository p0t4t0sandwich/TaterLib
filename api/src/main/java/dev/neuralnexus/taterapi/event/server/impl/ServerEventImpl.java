/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.event.server.impl;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.event.server.ServerEvent;
import dev.neuralnexus.taterapi.server.SimpleServer;

/** General implementation of {@link ServerEvent}. */
public class ServerEventImpl implements ServerEvent {
    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public SimpleServer server() {
        return TaterAPIProvider.api().get().server();
    }
}
