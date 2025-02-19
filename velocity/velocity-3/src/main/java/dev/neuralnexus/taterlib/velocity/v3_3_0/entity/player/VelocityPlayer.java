/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.velocity.v3_3_0.entity.player;

import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import com.velocitypowered.api.proxy.server.RegisteredServer;

import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterlib.velocity.v3_3_0.server.VelocityServer;

import net.kyori.adventure.text.Component;

import java.util.UUID;

/** Velocity implementation of {@link ProxyPlayer}. */
public class VelocityPlayer implements ProxyPlayer, Wrapped<com.velocitypowered.api.proxy.Player> {
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
        if (!this.player.getCurrentServer().isPresent()) return null;
        return new VelocityServer(this.player.getCurrentServer().get().getServer());
    }

    @Override
    public void sendMessage(String message) {
        this.player.sendMessage(Component.text(message));
    }

    @Override
    public void sendPacket(ResourceKey channel, byte[] data) {
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
}
