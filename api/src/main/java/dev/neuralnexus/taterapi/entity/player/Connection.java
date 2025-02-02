/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.entity.player;

import dev.neuralnexus.modapi.metadata.annotations.Range;
import dev.neuralnexus.modapi.metadata.annotations.VersionFeature;
import dev.neuralnexus.modapi.metadata.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.network.CustomPayloadPacket;
import dev.neuralnexus.taterapi.resource.ResourceKey;

/** Represents a connection to a player. */
public interface Connection {
    /**
     * Get the IP address of the player
     *
     * @return The IP address of the player
     */
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
     * Sends a packet using the specified channel
     *
     * @param channel The channel to send the message on
     * @param data The message to send
     */
    @VersionFeature(name = "Connection#sendPacket()", incompatible = @Range(MinecraftVersion.B1_7_3))
    void sendPacket(ResourceKey channel, byte[] data);

    /**
     * Sends a packet
     *
     * @param packet the packet
     */
    default void sendPacket(CustomPayloadPacket packet) {
        this.sendPacket(packet.channel(), packet.data());
    }
}
