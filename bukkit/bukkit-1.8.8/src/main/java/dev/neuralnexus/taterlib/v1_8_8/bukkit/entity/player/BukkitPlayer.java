/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_8.bukkit.entity.player;

import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.v1_8_8.bukkit.entity.BukkitLivingEntity;
import dev.neuralnexus.taterlib.v1_8_8.bukkit.item.inventory.BukkitPlayerInventory;
import dev.neuralnexus.taterlib.v1_8_8.bukkit.world.BukkitWorld;
import dev.neuralnexus.taterloader.Loader;

import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

/** Bukkit implementation of {@link Player}. */
public class BukkitPlayer extends BukkitLivingEntity implements Player, ServerPlayer {
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
    public void sendMessage(String message) {
        player.sendMessage(message);
    }

    @Override
    public void sendPluginMessage(ResourceKey channel, byte[] data) {
        player.sendPluginMessage((Plugin) Loader.instance().plugin(), channel.asString(), data);
    }

    @Override
    public PlayerInventory inventory() {
        return new BukkitPlayerInventory(player.getInventory());
    }

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
            TaterLib.logger().error("Could not reflect to get the player's ping", e);
            return 0;
        }
    }

    @Override
    public void kick(String reason) {
        player.kickPlayer(reason);
    }

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

    @Override
    public void allowFlight(boolean allow) {
        player.setAllowFlight(allow);
    }

    @Override
    public boolean canFly() {
        return player.getAllowFlight();
    }

    @Override
    public boolean isFlying() {
        return player.isFlying();
    }

    @Override
    public void setFlying(boolean flying) {
        player.setFlying(flying);
    }

    @Override
    public GameMode gameMode() {
        return GameMode.fromName(player.getGameMode().name());
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        player.setGameMode(org.bukkit.GameMode.valueOf(gameMode.name()));
    }
}
