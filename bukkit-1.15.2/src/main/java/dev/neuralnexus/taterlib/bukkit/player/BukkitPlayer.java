package dev.neuralnexus.taterlib.bukkit.player;

import dev.neuralnexus.taterlib.bukkit.BukkitTaterLibPlugin;
import dev.neuralnexus.taterlib.bukkit.inventory.BukkitPlayerInventory;
import dev.neuralnexus.taterlib.bukkit.util.BukkitConversions;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.common.utils.Position;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

/**
 * Abstracts a Bukkit player to an Player.
 */
public class BukkitPlayer implements Player {
    private final org.bukkit.entity.Player player;
    private Plugin plugin = BukkitTaterLibPlugin.getInstance();
    private String serverName;

    /**
     * Constructor.
     * @param player The Bukkit player.
     */
    public BukkitPlayer(org.bukkit.entity.Player player) {
        this.player = player;
        this.serverName = "local";
    }

    /**
     * Constructor.
     * @param player The Bukkit player.
     * @param serverName The name of the server the player is on.
     */
    public BukkitPlayer(org.bukkit.entity.Player player, String serverName) {
        this.player = player;
        this.serverName = serverName;
    }

    /**
     * Constructor.
     * @param player The Bukkit player.
     * @param plugin The plugin.
     */
    public BukkitPlayer(org.bukkit.entity.Player player, Plugin plugin) {
        this.player = player;
        this.plugin = plugin;
        this.serverName = "local";
    }

    /**
     * Constructor.
     * @param player The Bukkit player.
     * @param plugin The plugin.
     * @param serverName The name of the server the player is on.
     */
    public BukkitPlayer(org.bukkit.entity.Player player, Plugin plugin, String serverName) {
        this.player = player;
        this.plugin = plugin;
        this.serverName = serverName;
    }

    /**
     * Gets the Bukkit player
     * @return The Bukkit player
     */
    public org.bukkit.entity.Player getPlayer() {
        return player;
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
    public Position getPosition() {
        return BukkitConversions.positionFromLocation(player.getLocation());
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
    public void sendPluginMessage(String channel, byte[] data) {
        player.sendPluginMessage(plugin, channel, data);
    }

    public void sendPluginMessage(Plugin plugin, String channel, byte[] data) {
        player.sendPluginMessage(plugin, channel, data);
    }

    /**
     * @inheritDoc
     */
    @Override
    public PlayerInventory getInventory() {
        return new BukkitPlayerInventory(player.getInventory());
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasPermission(String permission) {
        return player.hasPermission(permission);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void kickPlayer(String reason) {
        player.kickPlayer(reason);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setSpawn(Position position) {
        player.setBedSpawnLocation(BukkitConversions.locationFromPosition(position));
    }
}
