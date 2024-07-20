/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12_2.bungee.entity.player;

import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.ProxyServer;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterlib.v1_12_2.bungee.server.BungeeProxyServer;
import dev.neuralnexus.taterlib.v1_12_2.bungee.server.BungeeServer;

import net.md_5.bungee.api.chat.ComponentBuilder;
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
     * Gets the proxy server the player is connected to.
     *
     * @return The server the player is connected to.
     */
    public ProxyServer proxyServer() {
        return BungeeProxyServer.instance();
    }

    /**
     * Connect the player to a server.
     *
     * @param serverName The name of the server to connect to.
     */
    @Override
    public void connect(String serverName) {
        if (net.md_5.bungee.api.ProxyServer.getInstance().getServerInfo(serverName) == null) return;
        ServerInfo server = net.md_5.bungee.api.ProxyServer.getInstance().getServerInfo(serverName);
        player.connect(server);
    }

    @Override
    public UUID uuid() {
        return player.getUniqueId();
    }

    @Override
    public String ipAddress() {
        return player.getAddress().getAddress().getHostAddress();
    }

    @Override
    public String name() {
        return player.getName();
    }

    @Override
    public String displayName() {
        return player.getDisplayName();
    }

    @Override
    public Server server() {
        return new BungeeServer(player.getServer().getInfo());
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(new ComponentBuilder(message).create());
    }

    @Override
    public void sendPluginMessage(ResourceKey channel, byte[] data) {
        player.getServer().getInfo().sendData(channel.asString(), data);
    }

    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }

    @Override
    public int ping() {
        return player.getPing();
    }

    @Override
    public void kick(String message) {
        player.disconnect(new ComponentBuilder(message).create());
    }
}
