package dev.neuralnexus.taterlib.forge.networking.packet;

import net.minecraft.network.PacketBuffer;

public class ForgeMessagePacket {
    private final String message;

    public ForgeMessagePacket(PacketBuffer buf) {
        this.message = new String(buf.readByteArray());
    }

    public ForgeMessagePacket(String message) {
        this.message = message;
    }

    public void encode(PacketBuffer buf) {
        buf.writeByteArray(this.message.getBytes());
    }

    public static ForgeMessagePacket decode(PacketBuffer buf) {
        return new ForgeMessagePacket(buf);
    }
}
