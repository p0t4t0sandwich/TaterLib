/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.velocity.v3_3_0.entity.player;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import com.velocitypowered.api.proxy.server.RegisteredServer;

import dev.neuralnexus.modapi.crossperms.PermsAPI;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.network.FriendlyByteBuf;
import dev.neuralnexus.taterapi.network.codec.StreamCodec;
import dev.neuralnexus.taterapi.network.protocol.Packet;
import dev.neuralnexus.taterapi.network.protocol.common.ServerboundCustomPayloadPacket;
import dev.neuralnexus.taterapi.network.protocol.common.custom.CustomPacketPayload;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterlib.velocity.v3_3_0.server.VelocityServer;

import net.kyori.adventure.text.Component;

import org.jspecify.annotations.NonNull;

import java.util.UUID;

/** Velocity implementation of {@link ProxyPlayer}. */
public class VelocityPlayer implements ProxyPlayer, Wrapped<Player> {
    private final com.velocitypowered.api.proxy.Player player;
    private RegisteredServer server = null;

    /**
     * Constructor.
     *
     * @param player The Velocity player.
     */
    public VelocityPlayer(com.velocitypowered.api.proxy.Player player) {
        this.player = player;
    }

    /**
     * Constructor.
     *
     * @param player The Velocity player.
     * @param server The server the player is connected to.
     */
    public VelocityPlayer(com.velocitypowered.api.proxy.Player player, RegisteredServer server) {
        this.player = player;
        this.server = server;
    }

    @Override
    public com.velocitypowered.api.proxy.Player unwrap() {
        return this.player;
    }

    /**
     * Connect the player to a server.
     *
     * @param serverName The name of the server to connect to.
     */
    @Override
    public void connect(String serverName) {
        ProxyServer proxyServer = (ProxyServer) MetaAPI.instance().server();
        if (proxyServer.getServer(serverName).isEmpty()) return;
        RegisteredServer server = proxyServer.getServer(serverName).get();
        this.player.createConnectionRequest(server).fireAndForget();
    }

    @Override
    public UUID uuid() {
        return this.player.getUniqueId();
    }

    @Override
    public String ipAddress() {
        return this.player.getRemoteAddress().getAddress().getHostAddress();
    }

    @Override
    public String name() {
        return this.player.getUsername();
    }

    @Override
    public String displayName() {
        return this.player.getUsername();
    }

    @Override
    public Server server() {
        if (server != null) return new VelocityServer(server);
        if (this.player.getCurrentServer().isEmpty()) return null;
        return new VelocityServer(this.player.getCurrentServer().get().getServer());
    }

    @Override
    public void sendMessage(String message) {
        this.player.sendMessage(Component.text(message));
    }

    @Override
    public void sendPacket(final @NonNull Packet packet) {
        // TODO: Use PluginMessageEncoder
        final CustomPacketPayload payload = ((ServerboundCustomPayloadPacket) packet).payload();
        this.sendPacket(payload);
    }

    @Override
    public void sendPacket(final @NonNull CustomPacketPayload payload) {
        // TODO: Use PluginMessageEncoder
        final FriendlyByteBuf data = new FriendlyByteBuf();
        //noinspection unchecked
        ((StreamCodec<FriendlyByteBuf, CustomPacketPayload>) payload.type().codec())
                .encode(data, payload);
        this.player
                .getCurrentServer()
                .ifPresent(
                        serverConnection ->
                                serverConnection.sendPluginMessage(
                                        MinecraftChannelIdentifier.from(payload.type().id()),
                                        data.array()));
    }

    @Override
    public void sendPacket(@NonNull Identifier channel, byte @NonNull [] data) {
        this.player
                .getCurrentServer()
                .ifPresent(
                        serverConnection ->
                                serverConnection.sendPluginMessage(
                                        MinecraftChannelIdentifier.from(channel.asString()), data));
    }

    @Override
    public int ping() {
        return (int) this.player.getPing();
    }

    @Override
    public void kick(String message) {
        this.player.disconnect(Component.text(message));
    }

    @Override
    public boolean hasPermission(String permission) {
        return PermsAPI.instance().hasPermission(this, permission);
    }

    @Override
    public boolean hasPermission(int permissionLevel) {
        return PermsAPI.instance().hasPermission(this, permissionLevel);
    }

    @Override
    public boolean hasPermission(String permission, int permissionLevel) {
        return PermsAPI.instance().hasPermission(this, permission, permissionLevel);
    }
}
