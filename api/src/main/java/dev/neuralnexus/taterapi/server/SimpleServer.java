/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.server;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.mc.server.MinecraftServer;
import dev.neuralnexus.taterapi.mc.server.players.CachedUserNameToIdResolver;
import dev.neuralnexus.taterapi.mc.server.players.NameAndId;
import dev.neuralnexus.taterapi.mc.server.players.UserWhiteListEntry;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.Platform;
import dev.neuralnexus.taterapi.meta.Side;
import dev.neuralnexus.taterapi.network.Connection;
import dev.neuralnexus.taterapi.network.protocol.Packet;
import dev.neuralnexus.taterapi.network.protocol.PacketFlow;
import dev.neuralnexus.taterapi.network.protocol.common.ClientboundCustomPayloadPacket;
import dev.neuralnexus.taterapi.network.protocol.common.ServerboundCustomPayloadPacket;
import dev.neuralnexus.taterapi.network.protocol.common.custom.CustomPacketPayload;
import dev.neuralnexus.taterapi.resources.Identifier;
import org.jspecify.annotations.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Simple abstraction for a Minecraft server. Holds common traits between regular servers and
 * proxies.
 */
public interface SimpleServer {
    /**
     * Get the name of the server.
     *
     * @return The name of the server.
     */
    default String name() {
        return TaterAPI.serverName();
    }

    /** Get the server's brand. */
    String brand();

    /**
     * Get the set of online players.
     *
     * @return The set of online players.
     */
    List<User> players();

    /**
     * Get the server's whitelist
     *
     * @return The whitelist
     */
    default Collection<NameAndId> whitelist() {
        return MinecraftServer.getPlayerList().getWhiteList().getEntries().stream()
                .map(UserWhiteListEntry::getUser)
                .collect(Collectors.toSet());
    }

    /**
     * Get the server's player cache
     *
     * @return The player cache
     */
    default Collection<NameAndId> playercache() {
        return MinecraftServer.getProfileCache().getProfilesByName().values().stream()
                .map(CachedUserNameToIdResolver.GameProfileInfo::nameAndId)
                .collect(Collectors.toSet());
    }

    /**
     * Sends a packet using the specified channel
     *
     * @param channel The channel to send the message on
     * @param data The message to send
     */
    @Deprecated
    default void sendPacket(Identifier channel, byte[] data) {
        this.players().stream()
                .findFirst()
                .map(Connection.class::cast)
                .ifPresent(c -> c.sendPacket(channel, data));
    }

    /**
     * Sends a packet using a player and the specified channel
     *
     * @param playerName The player's name
     * @param channel The channel to send the message on
     * @param data The message to send
     */
    @Deprecated
    default void sendPacket(String playerName, Identifier channel, byte[] data) {
        this.getPlayer(playerName)
                .map(Connection.class::cast)
                .ifPresent(c -> c.sendPacket(channel, data));
    }

    /**
     * Sends a packet using a player and the specified channel
     *
     * @param playerUUID The player's UUID
     * @param channel The channel to send the message on
     * @param data The message to send
     */
    @Deprecated
    default void sendPacket(UUID playerUUID, Identifier channel, byte[] data) {
        this.getPlayer(playerUUID)
                .map(Connection.class::cast)
                .ifPresent(c -> c.sendPacket(channel, data));
    }

    /**
     * Sends a packet
     *
     * @param packet the packet
     */
    void sendPacket(Packet packet);

    /**
     * Sends a custom payload packet
     *
     * @param payload the payload packet
     */
    default void sendPacket(CustomPacketPayload payload) {
        // TODO: Add PacketFlow to Connection
        // TODO: INTEGRATED SERVER WILL NOT WORK CORRECTLY
        final Side side = MetaAPI.instance().side();
        final PacketFlow flow = switch (side) {
            case CLIENT, PROXY -> PacketFlow.SERVERBOUND;
            case SERVER -> PacketFlow.CLIENTBOUND;
            default -> throw new IllegalStateException("NOT IMPLEMENTED");
        };
        Packet packet = switch (flow) {
            case CLIENTBOUND -> new ClientboundCustomPayloadPacket(payload);
            case SERVERBOUND -> new ServerboundCustomPayloadPacket(payload);
            default -> throw new IllegalStateException("Unexpected value: " + flow);
        };
        this.sendPacket(packet);
    }

    /**
     * Sends a packet using a player and the specified channel
     *
     * @param playerName The player's name
     * @param packet The packet
     */
    default void sendPacket(final @NonNull String playerName, final @NonNull Packet packet) {
        this.getPlayer(playerName)
                .map(Connection.class::cast)
                .ifPresent(c -> c.sendPacket(packet));
    }

    /**
     * Sends a custom payload using a player and the specified channel
     *
     * @param playerName The player's name
     * @param payload The payload
     */
    default void sendPacket(final @NonNull String playerName, final @NonNull CustomPacketPayload payload) {
        this.getPlayer(playerName)
                .map(Connection.class::cast)
                .ifPresent(c -> c.sendPacket(payload));
    }

    /**
     * Sends a packet using a player and the specified channel
     *
     * @param playerUUID The player's UUID
     * @param packet The packet
     */
    default void sendPacket(final @NonNull UUID playerUUID, final @NonNull Packet packet) {
        this.getPlayer(playerUUID)
                .map(Connection.class::cast)
                .ifPresent(c -> c.sendPacket(packet));
    }

    /**
     * Sends a custom payload using a player and the specified channel
     *
     * @param playerUUID The player's UUID
     * @param payload The payload
     */
    default void sendPacket(final @NonNull UUID playerUUID, final @NonNull CustomPacketPayload payload) {
        this.getPlayer(playerUUID)
                .map(Connection.class::cast)
                .ifPresent(c -> c.sendPacket(payload));
    }

    /**
     * Get a player by their name.
     *
     * @param name The name of the player.
     * @return The player, if found.
     */
    default Optional<User> getPlayer(String name) {
        return this.players().stream().filter(player -> player.name().equals(name)).findFirst();
    }

    /**
     * Get a player by their UUID.
     *
     * @param uuid The UUID of the player.
     * @return The player, if found.
     */
    default Optional<User> getPlayer(UUID uuid) {
        return this.players().stream().filter(player -> player.uuid().equals(uuid)).findFirst();
    }

    /**
     * Broadcast a message to the server.
     *
     * @param message The message to broadcast.
     */
    default void broadcastMessage(String message) {
        this.players().forEach(player -> player.sendMessage(message));
    }

    /**
     * Get the server's version.
     *
     * @return The server's version.
     */
    default MinecraftVersion version() {
        return MetaAPI.instance().version();
    }

    /**
     * Get the server's type.
     *
     * @return The server's type.
     */
    default Platform type() {
        return MetaAPI.instance().platform();
    }
}
