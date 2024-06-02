package dev.neuralnexus.taterlib.modules.bungeecord.api.events;

import com.google.common.io.ByteArrayDataInput;

import dev.neuralnexus.taterlib.event.Event;

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
