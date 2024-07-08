/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_14_4.forge.player;

import dev.neuralnexus.taterapi.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.player.GameMode;
import dev.neuralnexus.taterapi.player.Player;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_14_4.forge.entity.ForgeLivingEntity;
import dev.neuralnexus.taterlib.v1_14_4.forge.inventory.ForgePlayerInventory;
import dev.neuralnexus.taterlib.v1_14_4.forge.networking.ModMessages;
import dev.neuralnexus.taterlib.v1_14_4.forge.networking.packet.ForgeMessagePacket;
import dev.neuralnexus.taterlib.v1_14_4.forge.server.ForgeServer;
import dev.neuralnexus.taterlib.v1_14_4.forge.world.ForgeWorld;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;

import java.util.UUID;

/** Forge implementation of {@link Player}. */
public class ForgePlayer extends ForgeLivingEntity implements Player {
    private final PlayerEntity player;

    /**
     * Constructor.
     *
     * @param player The Forge player.
     */
    public ForgePlayer(PlayerEntity player) {
        super(player);
        this.player = player;
    }

    /**
     * Gets the Forge player
     *
     * @return The Forge player
     */
    public PlayerEntity player() {
        return player;
    }

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        return player.getUniqueID();
    }

    /** {@inheritDoc} */
    @Override
    public String ipAddress() {
        return ((ServerPlayerEntity) player).getPlayerIP();
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return player.getName().getString();
    }

    /** {@inheritDoc} */
    @Override
    public Optional<String> displayName() {
        return player.getDisplayName().getString();
    }

    /** {@inheritDoc} */
    @Override
    public Server server() {
        return new ForgeServer(player.getServer());
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        player.sendMessage(new StringTextComponent(message));
    }

    /** {@inheritDoc} */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        ModMessages.sendPluginMessage(
                new ForgeMessagePacket(new String(data)), channel, (ServerPlayerEntity) player);
    }

    /** {@inheritDoc} */
    @Override
    public PlayerInventory inventory() {
        return new ForgePlayerInventory(player.inventory);
    }

    /** {@inheritDoc} */
    @Override
    public int ping() {
        return ((ServerPlayerEntity) player).ping;
    }

    /** {@inheritDoc} */
    @Override
    public void kick(String message) {
        ((ServerPlayerEntity) player).connection.disconnect(new StringTextComponent(message));
    }

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location, boolean forced) {
        player.setSpawnPoint(
                new BlockPos(location.x(), location.y(), location.z()),
                forced,
                ((ForgeWorld) location.world()).world().dimension.getType());
    }

    /** {@inheritDoc} */
    @Override
    public void allowFlight(boolean allow) {
        player.abilities.allowFlying = allow;
    }

    /** {@inheritDoc} */
    @Override
    public boolean canFly() {
        return player.abilities.allowFlying;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isFlying() {
        return player.abilities.isFlying;
    }

    /** {@inheritDoc} */
    @Override
    public void setFlying(boolean flying) {
        player.abilities.isFlying = flying;
    }

    /** {@inheritDoc} */
    @Override
    public GameMode gameMode() {
        return GameMode.fromName(
                ((ServerPlayerEntity) player).interactionManager.getGameType().getName());
    }

    /** {@inheritDoc} */
    @Override
    public void setGameMode(GameMode gameMode) {
        player.setGameType(net.minecraft.world.GameType.getByID(gameMode.id()));
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return player.hasPermissionLevel(permissionLevel);
    }
}
