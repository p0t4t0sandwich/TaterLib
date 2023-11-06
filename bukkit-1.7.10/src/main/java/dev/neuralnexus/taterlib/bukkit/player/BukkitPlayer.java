package dev.neuralnexus.taterlib.bukkit.player;

import dev.neuralnexus.taterlib.bukkit.BukkitTaterLibPlugin;
import dev.neuralnexus.taterlib.bukkit.entity.BukkitEntity;
import dev.neuralnexus.taterlib.bukkit.inventory.BukkitPlayerInventory;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.common.utils.Location;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

/**
 * Bukkit implementation of {@link Player}.
 */
public class BukkitPlayer extends BukkitEntity implements Player {
    private final org.bukkit.entity.Player player;
    private Plugin plugin = BukkitTaterLibPlugin.getInstance();
    private String serverName;

    /**
     * Constructor.
     * @param player The Bukkit player.
     */
    public BukkitPlayer(org.bukkit.entity.Player player) {
        super(player);
        this.player = player;
        this.serverName = "local";
    }

    /**
     * Constructor.
     * @param player The Bukkit player.
     * @param serverName The name of the server the player is on.
     */
    public BukkitPlayer(org.bukkit.entity.Player player, String serverName) {
        super(player);
        this.player = player;
        this.serverName = serverName;
    }

    /**
     * Constructor.
     * @param player The Bukkit player.
     * @param plugin The plugin.
     */
    public BukkitPlayer(org.bukkit.entity.Player player, Plugin plugin) {
        super(player);
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
        super(player);
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
    public UUID getUniqueId() {
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
    public void sendPluginMessage(String channel, byte[] data) {
        player.sendPluginMessage(plugin, channel, data);
    }

    public void sendPluginMessage(Plugin source, String channel, byte[] data) {
        player.sendPluginMessage(source, channel, data);
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
    public boolean hasPermission(int permissionLevel) {
        return false;
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
    public void setSpawn(Location location, boolean forced) {
        player.setBedSpawnLocation(new org.bukkit.Location(org.bukkit.Bukkit.getWorld(location.getWorld()), location.getX(), location.getY(), location.getZ()), forced);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setSpawn(Location location) {
        setSpawn(location, false);
    }
}
