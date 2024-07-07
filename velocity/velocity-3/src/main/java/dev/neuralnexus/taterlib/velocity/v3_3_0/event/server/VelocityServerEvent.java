/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.velocity.v3_3_0.event.server;

import dev.neuralnexus.taterapi.event.server.ServerEvent;
import dev.neuralnexus.taterapi.server.SimpleServer;
import dev.neuralnexus.taterlib.velocity.v3_3_0.server.VelocityProxyServer;

/** Velocity implementation of {@link ServerEvent}. */
public class VelocityServerEvent implements ServerEvent {
    /** {@inheritDoc} */
    @Override
    public SimpleServer server() {
        return VelocityProxyServer.instance();
    }
}
