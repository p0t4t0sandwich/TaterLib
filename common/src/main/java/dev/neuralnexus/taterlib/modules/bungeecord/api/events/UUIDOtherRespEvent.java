/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.modules.bungeecord.api.events;

import com.google.common.io.ByteArrayDataInput;

import dev.neuralnexus.taterapi.event.Event;

/** Response to BungeeCord plugin message UUIDOther request. */
public class UUIDOtherRespEvent implements Event {
    private final String player;
    private final String uuid;

    public UUIDOtherRespEvent(ByteArrayDataInput in) {
        player = in.readUTF();
        uuid = in.readUTF();
    }

    public String player() {
        return player;
    }

    public String uuid() {
        return uuid;
    }
}
