package dev.neuralnexus.taterlib.modules.bungeecord.api.events;

import com.google.common.io.ByteArrayDataInput;

import dev.neuralnexus.taterlib.event.Event;

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
