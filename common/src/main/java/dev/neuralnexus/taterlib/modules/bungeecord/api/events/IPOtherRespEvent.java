/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.modules.bungeecord.api.events;

import com.google.common.io.ByteArrayDataInput;

import dev.neuralnexus.taterlib.event.Event;

/** Response to BungeeCord plugin message IPOther request. */
public class IPOtherRespEvent implements Event {
    private final String player;
    private final String ip;
    private final int port;

    public IPOtherRespEvent(ByteArrayDataInput in) {
        player = in.readUTF();
        ip = in.readUTF();
        port = in.readInt();
    }

    public String player() {
        return player;
    }

    public String ip() {
        return ip;
    }

    public int port() {
        return port;
    }
}
