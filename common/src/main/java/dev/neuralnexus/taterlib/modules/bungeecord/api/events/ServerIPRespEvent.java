package dev.neuralnexus.taterlib.modules.bungeecord.api.events;

import com.google.common.io.ByteArrayDataInput;

import dev.neuralnexus.taterlib.event.Event;

/** Response to BungeeCord plugin message IP request. */
public class ServerIPRespEvent implements Event {
    private final String server;
    private final String ip;
    private final int port;

    public ServerIPRespEvent(ByteArrayDataInput in) {
        server = in.readUTF();
        ip = in.readUTF();
        port = in.readInt();
    }

    public String server() {
        return server;
    }

    public String ip() {
        return ip;
    }

    public int port() {
        return port;
    }
}
