/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.network.protocol.Packet;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.network.ConnectionBridge;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.entity.living.player.PlayerEntityBridge;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.WrappedLivingEntity;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.item.inventory.WrappedPlayerInventory;

import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.server.entity.living.player.ServerPlayerEntity;
import net.minecraft.text.LiteralText;

import org.jspecify.annotations.NonNull;

/** Vanilla implementation of {@link Player}. */
public class WrappedPlayer extends WrappedLivingEntity implements Player, ServerPlayer {
    private final @NonNull PlayerEntity player;

    public WrappedPlayer(final @NonNull PlayerEntity player) {
        super(player);
        this.player = player;
    }

    @Override
    public @NonNull PlayerEntity unwrap() {
        return this.player;
    }

    @Override
    public String ipAddress() {
        return ((ServerPlayerEntity) this.player).getIp();
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(new LiteralText(message));
    }

    @Override
    public void sendPacket(final @NonNull Packet packet) {
        ((ConnectionBridge) ((ServerPlayerEntity) this.player).networkHandler).bridge$send(packet);
    }

    @Override
    public PlayerInventory inventory() {
        return new WrappedPlayerInventory(this.player.inventory);
    }

    @Override
    public int ping() {
        return ((ServerPlayerEntity) this.player).ping;
    }

    @Override
    public void kick(String message) {
        ((ServerPlayerEntity) this.player).networkHandler.onDisconnect(new LiteralText(message));
    }

    @Override
    public void setSpawn(Location location, boolean forced) {
        ((PlayerEntityBridge) this.player).bridge$setSpawn(location, forced);
    }

    @Override
    public void allowFlight(boolean allow) {
        this.player.abilities.canFly = allow;
    }

    @Override
    public boolean canFly() {
        return this.player.abilities.canFly;
    }

    @Override
    public boolean isFlying() {
        return this.player.abilities.flying;
    }

    @Override
    public void setFlying(boolean flying) {
        this.player.abilities.flying = flying;
    }
}
