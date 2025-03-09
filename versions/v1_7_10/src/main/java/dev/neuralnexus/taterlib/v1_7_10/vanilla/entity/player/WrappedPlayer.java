/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.player;

import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.entity.living.player.PlayerEntityBridge;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.WrappedLivingEntity;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.item.inventory.WrappedPlayerInventory;

import io.netty.buffer.Unpooled;

import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.server.entity.living.player.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.world.WorldSettings;

import java.util.UUID;

/** Vanilla implementation of {@link Player}. */
public class WrappedPlayer extends WrappedLivingEntity implements Player, ServerPlayer {
    private final PlayerEntity player;

    public WrappedPlayer(PlayerEntity player) {
        super(player);
        this.player = player;
    }

    @Override
    public PlayerEntity unwrap() {
        return this.player;
    }

    @Override
    public UUID uuid() {
        return this.player.getUuid();
    }

    @Override
    public String ipAddress() {
        return ((ServerPlayerEntity) this.player).getIp();
    }

    @Override
    public String name() {
        return this.player.getName();
    }

    @Override
    public String displayName() {
        return this.player.getDisplayName().getFormattedString();
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(new LiteralText(message));
    }

    @Override
    public void sendPacket(ResourceKey channel, byte[] data) {
        PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());
        byteBuf.writeBytes(data);
        ((ServerPlayerEntity) this.player)
                .networkHandler.sendPacket(new CustomPayloadS2CPacket(channel.asString(), byteBuf));
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

    @Override
    public GameMode gameMode() {
        return ((PlayerEntityBridge) this.player).bridge$gameMode();
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        this.player.setGameMode((WorldSettings.getGameModeById(gameMode.id())));
    }
}
