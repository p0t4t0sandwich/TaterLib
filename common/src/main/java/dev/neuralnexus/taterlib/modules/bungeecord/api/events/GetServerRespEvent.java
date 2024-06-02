package dev.neuralnexus.taterlib.modules.bungeecord.api.events;

import com.google.common.io.ByteArrayDataInput;

import dev.neuralnexus.taterlib.event.Event;

/** Response to BungeeCord plugin message GetServer request. */
public class GetServerRespEvent implements Event {
    private final String server;

    public GetServerRespEvent(ByteArrayDataInput in) {
        server = in.readUTF();
    }

    public String server() {
        return server;
    }
}
