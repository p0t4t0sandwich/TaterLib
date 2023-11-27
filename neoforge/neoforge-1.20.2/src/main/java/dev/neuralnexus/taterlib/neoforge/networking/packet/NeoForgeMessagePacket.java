package dev.neuralnexus.taterlib.neoforge.networking.packet;

import net.minecraft.network.FriendlyByteBuf;

public class NeoForgeMessagePacket {
    private final String message;

    public NeoForgeMessagePacket(FriendlyByteBuf buf) {
        this.message = new String(buf.readByteArray());
    }

    public NeoForgeMessagePacket(String message) {
        this.message = message;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeByteArray(this.message.getBytes());
    }

    public static NeoForgeMessagePacket decode(FriendlyByteBuf buf) {
        return new NeoForgeMessagePacket(buf);
    }
}
