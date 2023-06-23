package dev.neuralnexus.taterapi.bukkit.player;

import dev.neuralnexus.taterapi.common.player.TaterPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Abstracts a Bukkit player to a TaterPlayer.
 */
public class BukkitTaterPlayer implements TaterPlayer {
    private final Player player;
    private String serverName;

    /**
     * Constructor.
     * @param player The Bukkit player.
     */
    public BukkitTaterPlayer(Player player) {
        this.player = player;
        this.serverName = "";
    }

    /**
     * Constructor.
     * @param player The Bukkit player.
     * @param serverName The name of the server the player is on.
     */
    public BukkitTaterPlayer(Player player, String serverName) {
        this.player = player;
        this.serverName = serverName;
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
        player.sendMessage(message);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasPermission(String permission) {
        return player.hasPermission(permission);
    }
}
