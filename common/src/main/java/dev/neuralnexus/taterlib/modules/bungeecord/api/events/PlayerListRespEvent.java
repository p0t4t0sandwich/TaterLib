/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.modules.bungeecord.api.events;

import com.google.common.io.ByteArrayDataInput;

import dev.neuralnexus.taterapi.event.Event;

/** Response to BungeeCord plugin message PlayerList request. */
public class PlayerListRespEvent implements Event {
    private final String server;
    private final String[] list;

    public PlayerListRespEvent(ByteArrayDataInput in) {
        server = in.readUTF();
        list = in.readUTF().split(", ");
    }

    public String server() {
        return server;
    }

    public String[] list() {
        return list;
    }
}
