package dev.neuralnexus.taterlib.modules.bungeecord.api.events;

import com.google.common.io.ByteArrayDataInput;

import dev.neuralnexus.taterlib.event.Event;

/** Response to BungeeCord plugin message UUID request. */
public class UUIDRespEvent implements Event {
    private final String uuid;

    public UUIDRespEvent(ByteArrayDataInput in) {
        uuid = in.readUTF();
    }

    public String uuid() {
        return uuid;
    }
}
