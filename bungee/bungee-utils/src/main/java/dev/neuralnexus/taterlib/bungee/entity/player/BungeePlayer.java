/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.bungee.entity.player;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.modapi.crossperms.PermsAPI;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterlib.bungee.server.BungeeServer;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

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
    public void sendPacket(ResourceKey channel, byte[] data) {
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
