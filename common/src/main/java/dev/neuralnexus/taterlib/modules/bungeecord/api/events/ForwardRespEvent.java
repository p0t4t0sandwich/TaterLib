package dev.neuralnexus.taterlib.modules.bungeecord.api.events;

import com.google.common.io.ByteArrayDataInput;

import dev.neuralnexus.taterlib.event.Event;

/** Response to BungeeCord plugin message Forward request. */
public class ForwardRespEvent implements Event {
    private final String subChannel;
    private final byte[] msgbytes;

    public ForwardRespEvent(ByteArrayDataInput in) {
        subChannel = in.readUTF();
        short len = in.readShort();
        msgbytes = new byte[len];
        in.readFully(msgbytes);
    }

    public String subChannel() {
        return subChannel;
    }

    public byte[] msgbytes() {
        return msgbytes;
    }
}
