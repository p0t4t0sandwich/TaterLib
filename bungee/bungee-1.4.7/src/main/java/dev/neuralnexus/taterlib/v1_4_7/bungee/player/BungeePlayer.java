/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_4_7.bungee.player;

import dev.neuralnexus.taterlib.player.ProxyPlayer;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.v1_4_7.bungee.server.BungeeServer;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

/** BungeeCord implementation of {@link ProxyPlayer}. */
public class BungeePlayer implements ProxyPlayer {
    private final ProxiedPlayer player;

    /**
     * Constructor.
     *
     * @param player The BungeeCord player.
     */
    public BungeePlayer(ProxiedPlayer player) {
        this.player = player;
    }

    /**
     * Gets the BungeeCord player
     *
     * @return The BungeeCord player
     */
    public ProxiedPlayer player() {
        return player;
    }

    /**
     * Connect the player to a server.
     *
     * @param serverName The name of the server to connect to.
     */
    @Override
    public void connect(String serverName) {
        if (ProxyServer.getInstance().getServerInfo(serverName) == null) return;
        ServerInfo server = ProxyServer.getInstance().getServerInfo(serverName);
        player.connect(server);
    }

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        // TODO: Find some implementation that provides the real UUID.
        return UUID.randomUUID();
    }

    /** {@inheritDoc} */
    @Override
    public String ipAddress() {
        return player.getAddress().getAddress().getHostAddress();
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return player.getName();
    }

    /** {@inheritDoc} */
    @Override
    public String displayName() {
        return player.getDisplayName();
    }

    /** {@inheritDoc} */
    @Override
    public Server server() {
        return new BungeeServer(player.getServer().getInfo());
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        player.sendMessage(message);
    }

    /** {@inheritDoc} */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        player.getServer().getInfo().sendData(channel, data);
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public int ping() {
        return player.getPing();
    }

    /** {@inheritDoc} */
    @Override
    public void kick(String message) {
        player.disconnect(message);
    }
}
