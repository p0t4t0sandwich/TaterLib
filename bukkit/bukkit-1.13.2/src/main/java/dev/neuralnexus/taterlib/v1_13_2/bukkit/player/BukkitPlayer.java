/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_13_2.bukkit.player;

import dev.neuralnexus.taterlib.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.loader.Loader;
import dev.neuralnexus.taterlib.player.GameMode;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.v1_13_2.bukkit.entity.BukkitLivingEntity;
import dev.neuralnexus.taterlib.v1_13_2.bukkit.inventory.BukkitPlayerInventory;
import dev.neuralnexus.taterlib.v1_13_2.bukkit.server.BukkitServer;
import dev.neuralnexus.taterlib.v1_13_2.bukkit.world.BukkitWorld;
import dev.neuralnexus.taterlib.world.Location;

import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

/** Bukkit implementation of {@link Player}. */
public class BukkitPlayer extends BukkitLivingEntity implements Player {
    private final org.bukkit.entity.Player player;

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
        player.sendPluginMessage((Plugin) Loader.instance().plugin(), channel, data);
    }

    public void sendPluginMessage(Plugin source, String channel, byte[] data) {
        player.sendPluginMessage(source, channel, data);
    }

    /** {@inheritDoc} */
    @Override
    public PlayerInventory inventory() {
        return new BukkitPlayerInventory(player.getInventory());
    }

    /** {@inheritDoc} */
    @Override
    public int ping() {
        try {
            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            return (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
        } catch (IllegalAccessException
                | IllegalArgumentException
                | InvocationTargetException
                | NoSuchMethodException
                | SecurityException
                | NoSuchFieldException e) {
            e.printStackTrace();
            return 0;
        }
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

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location, boolean forced) {
        player.setBedSpawnLocation(
                new org.bukkit.Location(
                        ((BukkitWorld) location.world()).world(),
                        location.x(),
                        location.y(),
                        location.z()),
                forced);
    }

    /** {@inheritDoc} */
    @Override
    public void allowFlight(boolean allow) {
        player.setAllowFlight(allow);
    }

    /** {@inheritDoc} */
    @Override
    public boolean canFly() {
        return player.getAllowFlight();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isFlying() {
        return player.isFlying();
    }

    /** {@inheritDoc} */
    @Override
    public void setFlying(boolean flying) {
        player.setFlying(flying);
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
