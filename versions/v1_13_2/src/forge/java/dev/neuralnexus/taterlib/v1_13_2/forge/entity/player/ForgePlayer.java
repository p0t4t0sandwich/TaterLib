/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.entity.player;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.network.chat.Component;
import dev.neuralnexus.taterapi.network.protocol.Packet;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.network.ConnectionBridge;
import dev.neuralnexus.taterlib.v1_13_2.forge.entity.ForgeLivingEntity;
import dev.neuralnexus.taterlib.v1_13_2.forge.item.inventory.ForgePlayerInventory;
import dev.neuralnexus.taterlib.v1_13_2.forge.world.ForgeServerWorld;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

import org.jspecify.annotations.NonNull;

import java.util.Optional;
import java.util.UUID;

/** Forge implementation of {@link Player}. */
public class ForgePlayer extends ForgeLivingEntity implements Player, ServerPlayer {
    private final EntityPlayer player;

    /**
     * Constructor.
     *
     * @param player The Forge player.
     */
    public ForgePlayer(EntityPlayer player) {
        super(player);
        this.player = player;
    }

    /**
     * Gets the Forge player
     *
     * @return The Forge player
     */
    public EntityPlayer player() {
        return player;
    }

    @Override
    public UUID uuid() {
        return this.player.getUniqueID();
    }

    @Override
    public String ipAddress() {
        return ((EntityPlayerMP) this.player).getPlayerIP();
    }

    @Override
    public String name() {
        return player.getName().getString();
    }

    @Override
    public String displayName() {
        return player.getDisplayName().getString();
    }

    @Override
    public void sendPacket(final @NonNull Packet packet) {
        ((ConnectionBridge) ((EntityPlayerMP) this.player).connection).bridge$send(packet);
    }

    @Override
    public PlayerInventory inventory() {
        return new ForgePlayerInventory(this.player.inventory);
    }

    @Override
    public int ping() {
        return ((EntityPlayerMP) this.player).ping;
    }

    @Override
    public void kick(String message) {
        ((EntityPlayerMP) this.player).connection.disconnect(Component.literal(message));
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void setSpawn(Location location, boolean forced) {
        Optional<WorldServer> serverLevel =
                ((Server) TaterAPI.instance().server())
                        .world(location.world().dimension())
                        .map(ForgeServerWorld.class::cast)
                        .map(ForgeServerWorld::unwrap);
        player.setSpawnPoint(
                new BlockPos(location.x(), location.y(), location.z()),
                forced,
                serverLevel.get().dimension.getType());
    }

    @Override
    public void allowFlight(boolean allow) {
        this.player.abilities.allowFlying = allow;
    }

    @Override
    public boolean canFly() {
        return this.player.abilities.allowFlying;
    }

    @Override
    public boolean isFlying() {
        return player.abilities.isFlying;
    }

    @Override
    public void setFlying(boolean flying) {
        player.abilities.isFlying = flying;
    }

    @Override
    public GameMode gameMode() {
        return GameMode.fromName(
                ((EntityPlayerMP) this.player).interactionManager.getGameType().getName());
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        this.player.setGameType(net.minecraft.world.GameType.getByID(gameMode.id()));
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(Component.literal(message));
    }
}
