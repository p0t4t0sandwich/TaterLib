/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.modules.bungeecord.api.events;

import com.google.common.io.ByteArrayDataInput;

import dev.neuralnexus.taterapi.event.Event;

/** Response to BungeeCord plugin message PlayerCount request. */
public class PlayerCountRespEvent implements Event {
    private final String server;
    private final int count;

    public PlayerCountRespEvent(ByteArrayDataInput in) {
        server = in.readUTF();
        count = in.readInt();
    }

    public String server() {
        return server;
    }

    public int count() {
        return count;
    }
}
