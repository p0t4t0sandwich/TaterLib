/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.modules.bungeecord.api.events;

import com.google.common.io.ByteArrayDataInput;

import dev.neuralnexus.taterapi.event.Event;

/** Response to BungeeCord plugin message GetServers request. */
public class GetServersRespEvent implements Event {
    private final String[] servers;

    public GetServersRespEvent(ByteArrayDataInput in) {
        servers = in.readUTF().split(", ");
    }

    public String[] servers() {
        return servers;
    }
}
