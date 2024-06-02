package dev.neuralnexus.taterlib.modules.bungeecord.api.events;

import com.google.common.io.ByteArrayDataInput;

import dev.neuralnexus.taterlib.event.Event;

/** Response to BungeeCord plugin message ForwardToPlayer request. */
public class ForwardToPlayerRespEvent implements Event {
    private final String subChannel;
    private final byte[] msgBytes;

    public ForwardToPlayerRespEvent(ByteArrayDataInput in) {
        subChannel = in.readUTF();
        short len = in.readShort();
        msgBytes = new byte[len];
        in.readFully(msgBytes);
    }

    public String subChannel() {
        return subChannel;
    }

    public byte[] msgbytes() {
        return msgBytes;
    }
}
