package dev.neuralnexus.taterlib.bukkit.player;

import dev.neuralnexus.taterlib.bukkit.entity.BukkitLivingEntity;
import dev.neuralnexus.taterlib.bukkit.inventory.BukkitPlayerInventory;
import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.player.GameMode;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.utils.Location;

import org.bukkit.plugin.Plugin;

import java.util.UUID;

/** Bukkit implementation of {@link Player}. */
public class BukkitPlayer extends BukkitLivingEntity implements Player {
    private final org.bukkit.entity.Player player;
    private Plugin plugin = null;
    private String serverName;

    /**
     * Constructor.
     *
     * @param player The Bukkit player.
     */
    public BukkitPlayer(org.bukkit.entity.Player player) {
        super(player);
        this.player = player;
        this.serverName = "local";
    }

    /**
     * Constructor.
     *
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
     *
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
     *
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
     *
     * @return The Bukkit player
     */
    public org.bukkit.entity.Player player() {
        return player;
    }

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        return player.getUniqueId();
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
    public String serverName() {
        return serverName;
    }

    /** {@inheritDoc} */
    @Override
    public void setServerName(String server) {
        this.serverName = server;
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        player.sendMessage(message);
    }

    /** {@inheritDoc} */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        //        player.sendPluginMessage(plugin, channel, data);
    }

    public void sendPluginMessage(Plugin plugin, String channel, byte[] data) {
        //        player.sendPluginMessage(plugin, channel, data);
    }

    /** {@inheritDoc} */
    @Override
    public PlayerInventory inventory() {
        return new BukkitPlayerInventory(player.getInventory());
    }

    /** {@inheritDoc} */
    @Override
    public int ping() {
        //        ((CraftPlayer) player).getHandle().netServerHandler.networkManager.f;
        return -1;
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void kick(String reason) {
        player.kickPlayer(reason);
    }

    @Override
    public void setSpawn(Location location, boolean forced) {
        //        player.setBedSpawnLocation(BukkitConversions.locationFromPosition(position),
        // forced);
    }

    /** {@inheritDoc} */
    @Override
    public void allowFlight(boolean allow) {
        // TODO: Write a module to allow flight
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public boolean canFly() {
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isFlying() {
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public void setFlying(boolean flying) {
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public GameMode gameMode() {
        return GameMode.fromName(player.getGameMode().name());
    }

    /** {@inheritDoc} */
    @Override
    public void setGameMode(GameMode gameMode) {
        player.setGameMode(org.bukkit.GameMode.valueOf(gameMode.name()));
    }
}
