package dev.neuralnexus.taterlib.modules.bungeecord.api.events;

import com.google.common.io.ByteArrayDataInput;

import dev.neuralnexus.taterlib.event.Event;

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
