package dev.neuralnexus.taterlib.modules.bungeecord.api.events;

import com.google.common.io.ByteArrayDataInput;

import dev.neuralnexus.taterlib.event.Event;

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
