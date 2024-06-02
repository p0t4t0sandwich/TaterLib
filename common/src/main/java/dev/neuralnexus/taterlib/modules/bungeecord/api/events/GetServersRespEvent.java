package dev.neuralnexus.taterlib.modules.bungeecord.api.events;

import com.google.common.io.ByteArrayDataInput;

import dev.neuralnexus.taterlib.event.Event;

/** Response to BungeeCord plugin message GetServers request. */
public class GetServersRespEvent implements Event {
    private final String[] servers;

    public GetServersRespEvent(ByteArrayDataInput in) {
        servers = in.readUTF().split(", ");
    }

    public String[] servers() {
        return servers;
    }
}
