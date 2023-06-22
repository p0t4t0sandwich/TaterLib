package dev.neuralnexus.taterapi.bungee.player;

import dev.neuralnexus.taterapi.common.player.TaterPlayer;
import net.md_5.bungee.api.chat.ComponentBuilder;
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
}
