package dev.neuralnexus.taterlib.forge.networking.packet;

import net.minecraft.network.FriendlyByteBuf;

public class ForgeMessagePacket {
    private final String message;

    public ForgeMessagePacket(FriendlyByteBuf buf) {
        this.message = new String(buf.readByteArray());
    }

    public ForgeMessagePacket(String message) {
        this.message = message;
    }

    public static ForgeMessagePacket decode(FriendlyByteBuf buf) {
        return new ForgeMessagePacket(buf);
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeByteArray(this.message.getBytes());
    }
}
