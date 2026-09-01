/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.bungee.entity.player;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.network.FriendlyByteBuf;
import dev.neuralnexus.taterapi.network.codec.StreamCodec;
import dev.neuralnexus.taterapi.network.protocol.Packet;
import dev.neuralnexus.taterapi.network.protocol.common.ServerboundCustomPayloadPacket;
import dev.neuralnexus.taterapi.network.protocol.common.custom.CustomPacketPayload;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterlib.bungee.server.BungeeServer;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import org.jspecify.annotations.NonNull;

import java.util.UUID;

/** BungeeCord implementation of {@link ProxyPlayer}. */
public class BungeePlayer implements ProxyPlayer, Wrapped<ProxiedPlayer> {
    private final ProxiedPlayer player;

    /**
     * Constructor.
     *
     * @param player The BungeeCord player.
     */
    public BungeePlayer(ProxiedPlayer player) {
        this.player = player;
    }

    @Override
    public ProxiedPlayer unwrap() {
        return this.player;
    }

    @Override
    public void connect(String serverName) {
        if (ProxyServer.getInstance().getServerInfo(serverName) == null) return;
        ServerInfo server = ProxyServer.getInstance().getServerInfo(serverName);
        this.player.connect(server);
    }

    @Override
    public UUID uuid() {
        return this.player.getUniqueId();
    }

    @SuppressWarnings("deprecation")
    @Override
    public String ipAddress() {
        return this.player.getAddress().getAddress().getHostAddress();
    }

    @Override
    public String name() {
        return this.player.getName();
    }

    @Override
    public String displayName() {
        return this.player.getDisplayName();
    }

    @Override
    public Server server() {
        return new BungeeServer(this.player.getServer().getInfo());
    }

    @Override
    public void sendMessage(String message) {
        this.player.sendMessage(new ComponentBuilder(message).create());
    }

    @Override
    public void sendPacket(final @NonNull Packet packet) {
        final CustomPacketPayload payload = ((ServerboundCustomPayloadPacket) packet).payload();
        this.sendPacket(payload);
    }

    @Override
    public void sendPacket(final @NonNull CustomPacketPayload payload) {
        final FriendlyByteBuf data = new FriendlyByteBuf();
        //noinspection unchecked
        ((StreamCodec<FriendlyByteBuf, CustomPacketPayload>) payload.type().codec())
                .encode(data, payload);
        this.player.getServer().getInfo().sendData(payload.type().id(), data.array());
    }

    @Override
    public void sendPacket(final @NonNull Identifier channel, byte @NonNull [] data) {
        this.player.getServer().getInfo().sendData(channel.asString(), data);
    }

    @Override
    public int ping() {
        return this.player.getPing();
    }

    @Override
    public void kick(String message) {
        this.player.disconnect(new ComponentBuilder(message).create());
    }

    @Override
    public boolean hasPermission(String permission) {
        // TODO: Replace with new CrossPerms
        throw new VersionFeatureNotSupportedException();
        // return PermsAPI.instance().hasPermission(this, permission);
    }

    @Override
    public boolean hasPermission(int permissionLevel) {
        // TODO: Replace with new CrossPerms
        throw new VersionFeatureNotSupportedException();
        // return PermsAPI.instance().hasPermission(this, permissionLevel);
    }

    @Override
    public boolean hasPermission(String permission, int permissionLevel) {
        // TODO: Replace with new CrossPerms
        throw new VersionFeatureNotSupportedException();
        // return PermsAPI.instance().hasPermission(this, permission, permissionLevel);
    }
}
