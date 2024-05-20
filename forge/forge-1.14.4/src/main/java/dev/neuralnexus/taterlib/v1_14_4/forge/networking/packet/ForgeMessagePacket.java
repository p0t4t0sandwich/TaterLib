package dev.neuralnexus.taterlib.v1_14_4.forge.networking.packet;

import net.minecraft.network.PacketBuffer;

public class ForgeMessagePacket {
    private final String message;

    public ForgeMessagePacket(PacketBuffer buf) {
        this.message = new String(buf.readByteArray());
    }

    public ForgeMessagePacket(String message) {
        this.message = message;
    }

    public static ForgeMessagePacket decode(PacketBuffer buf) {
        return new ForgeMessagePacket(buf);
    }

    public void encode(PacketBuffer buf) {
        buf.writeByteArray(this.message.getBytes());
    }
}
