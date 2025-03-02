/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.modules.bungeecord.api.events;

import com.google.common.io.ByteArrayDataInput;

import dev.neuralnexus.taterapi.event.Event;

/** Response to BungeeCord plugin message GetPlayerServer request. */
public class GetPlayerServerRespEvent implements Event {
    private final String player;
    private final String server;

    public GetPlayerServerRespEvent(ByteArrayDataInput in) {
        player = in.readUTF();
        server = in.readUTF();
    }

    public String player() {
        return player;
    }

    public String server() {
        return server;
    }
}
