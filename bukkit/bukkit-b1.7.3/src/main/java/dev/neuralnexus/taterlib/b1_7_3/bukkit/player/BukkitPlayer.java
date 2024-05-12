package dev.neuralnexus.taterlib.b1_7_3.bukkit.player;

import dev.neuralnexus.taterlib.b1_7_3.bukkit.entity.BukkitLivingEntity;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.inventory.BukkitPlayerInventory;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.server.BukkitServer;
import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.player.GameMode;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.world.Location;

import org.bukkit.plugin.Plugin;

import java.util.UUID;

/** Bukkit implementation of {@link Player}. */
public class BukkitPlayer extends BukkitLivingEntity implements Player {
    private final org.bukkit.entity.Player player;
    private Plugin plugin = null;

    /**
     * Constructor.
     *
     * @param player The Bukkit player.
     */
    public BukkitPlayer(org.bukkit.entity.Player player) {
        super(player);
        this.player = player;
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
    public Server server() {
        return new BukkitServer(player.getServer());
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        player.sendMessage(message);
    }

    /** {@inheritDoc} */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        // TODO: Create some different way to send plugin messages
        throw new VersionFeatureNotSupportedException();
        //        player.sendPluginMessage(plugin, channel, data);
    }

    public void sendPluginMessage(Plugin plugin, String channel, byte[] data) {
        throw new VersionFeatureNotSupportedException();
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
        // TODO: Find the field that stores the ping
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
        // TODO: Write a module to set bed spawns/respawn points
        throw new VersionFeatureNotSupportedException();
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
