package dev.neuralnexus.taterapi.bungee.player;

import dev.neuralnexus.taterapi.bungee.BungeeTaterAPIPlugin;
import dev.neuralnexus.taterapi.common.player.TaterPlayer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

/**
 * Abstracts a BungeeCord player to a TaterPlayer.
 */
public class BungeeTaterPlayer implements TaterPlayer {
    private final ProxiedPlayer player;
    private String serverName;

    /**
     * Constructor.
     * @param player The BungeeCord player.
     */
    public BungeeTaterPlayer(ProxiedPlayer player) {
        this.player = player;
        if (player.getServer() != null) {
            this.serverName = player.getServer().getInfo().getName();
        } else {
            this.serverName = null;
        }
    }

    /**
     * Connect the player to a server.
     * @param serverName The name of the server to connect to.
     */
    public void connect(String serverName) {
        if (BungeeTaterAPIPlugin.getProxyServer().getServerInfo(serverName) == null) return;

        ServerInfo server = BungeeTaterAPIPlugin.getProxyServer().getServerInfo(serverName);
        player.connect(server);
    }

    /**
     * @inheritDoc
     */
    @Override
    public UUID getUUID() {
        return player.getUniqueId();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getName() {
        return player.getName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDisplayName() {
        return player.getDisplayName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getServerName() {
        return serverName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setServerName(String server) {
        this.serverName = server;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void sendMessage(String message) {
        player.sendMessage(new ComponentBuilder(message).create());
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasPermission(String permission) {
        return player.hasPermission(permission);
    }
}
