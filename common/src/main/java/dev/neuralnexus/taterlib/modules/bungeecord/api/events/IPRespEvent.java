package dev.neuralnexus.taterlib.modules.bungeecord.api.events;

import com.google.common.io.ByteArrayDataInput;

import dev.neuralnexus.taterlib.event.Event;

/** Response to BungeeCord plugin message IP request. */
public class IPRespEvent implements Event {
    private final String ip;
    private final int port;

    public IPRespEvent(ByteArrayDataInput in) {
        ip = in.readUTF();
        port = in.readInt();
    }

    public String ip() {
        return ip;
    }

    public int port() {
        return port;
    }
}
