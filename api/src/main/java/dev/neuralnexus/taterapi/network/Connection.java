/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.network;

import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Side;
import dev.neuralnexus.taterapi.meta.annotations.Range;
import dev.neuralnexus.taterapi.meta.annotations.VersionFeature;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.network.protocol.Packet;
import dev.neuralnexus.taterapi.network.protocol.PacketFlow;
import dev.neuralnexus.taterapi.network.protocol.common.ClientboundCustomPayloadPacket;
import dev.neuralnexus.taterapi.network.protocol.common.ServerboundCustomPayloadPacket;
import dev.neuralnexus.taterapi.network.protocol.common.custom.CustomPacketPayload;
import dev.neuralnexus.taterapi.resources.Identifier;

import org.jspecify.annotations.NonNull;

/** Represents a connection to a player. */
public interface Connection {
    /**
     * Get the IP address of the player
     *
     * @return The IP address of the player
     */
    // TODO: Update usages to proper IP object
    String ipAddress();

    /**
     * Get player's ping
     *
     * @return The player's ping
     */
    @VersionFeature(name = "Connection#ping()", incompatible = @Range(MinecraftVersion.B1_7_3))
    int ping();

    /**
     * Kick the player
     *
     * @param message The reason to kick the player
     */
    void kick(String message);

    /** Disconnect the player (kick with no message) */
    default void disconnect() {
        kick("");
    }

    /**
     * Sends a packet
     *
     * @param packet the packet
     */
    @VersionFeature(
            name = "Connection#sendPacket()",
            incompatible = @Range(max = MinecraftVersion.V6_4))
    void sendPacket(final @NonNull Packet packet);

    /**
     * Sends a custom payload packet
     *
     * @param payload the payload packet
     */
    @VersionFeature(
            name = "Connection#sendPacket()",
            incompatible = @Range(max = MinecraftVersion.V6_4))
    default void sendPacket(final @NonNull CustomPacketPayload payload) {
        // TODO: Add PacketFlow to ConnectionBridge?
        // TODO: INTEGRATED SERVER WILL NOT WORK CORRECTLY
        final Side side = MetaAPI.instance().side();
        final PacketFlow flow =
                switch (side) {
                    case CLIENT, PROXY -> PacketFlow.SERVERBOUND;
                    case SERVER -> PacketFlow.CLIENTBOUND;
                    default -> throw new IllegalStateException("NOT IMPLEMENTED");
                };
        Packet packet =
                switch (flow) {
                    case CLIENTBOUND -> new ClientboundCustomPayloadPacket(payload);
                    case SERVERBOUND -> new ServerboundCustomPayloadPacket(payload);
                    default -> throw new IllegalStateException("Unexpected value: " + flow);
                };
        this.sendPacket(packet);
    }

    /**
     * Sends a packet using the specified channel
     *
     * @param channel The channel to send the message on
     * @param data The message to send
     */
    @VersionFeature(
            name = "Connection#sendPacket()",
            incompatible = @Range(MinecraftVersion.B1_7_3))
    default void sendPacket(final @NonNull Identifier channel, final byte @NonNull [] data) {
        final FriendlyByteBuf buf = new FriendlyByteBuf(data);
        final CustomPacketPayload.Raw payload = new CustomPacketPayload.Raw(channel, buf);
        this.sendPacket(payload);
    }
}
