/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_9_4.fabric.entity.player;

import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_9_4.fabric.entity.FabricLivingEntity;
import dev.neuralnexus.taterlib.v1_9_4.fabric.item.inventory.FabricPlayerInventory;

import io.netty.buffer.Unpooled;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

/** Fabric implementation of {@link Player}. */
public class FabricPlayer extends FabricLivingEntity implements Player, ServerPlayer {
    private final PlayerEntity player;

    /**
     * Constructor.
     *
     * @param player The Fabric player.
     */
    public FabricPlayer(PlayerEntity player) {
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
        return this.player.getName().asFormattedString();
    }

    @Override
    public String displayName() {
        return player.getCustomName();
    }

    @Override
    @SuppressWarnings("VulnerableCodeUsages")
    public void sendPacket(ResourceKey channel, byte[] data) {
        PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());
        byteBuf.writeBytes(data);
        ((ServerPlayerEntity) this.player)
                .networkHandler.sendPacket(new CustomPayloadS2CPacket(channel.asString(), byteBuf));
    }

    @Override
    public PlayerInventory inventory() {
        return new FabricPlayerInventory(this.player.inventory);
    }

    @Override
    public int ping() {
        return ((ServerPlayerEntity) this.player).ping;
    }

    @Override
    public void kick(String message) {
        ((ServerPlayerEntity) this.player)
                .networkHandler.onDisconnected(new TranslatableText(message));
    }

    @Override
    public void setSpawn(Location location, boolean forced) {
        // TODO: Dimension aware spawn setting
        player.setPlayerSpawn(new BlockPos(location.x(), location.y(), location.z()), forced);
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
        return this.player.abilities.flying;
    }

    @Override
    public void setFlying(boolean flying) {
        this.player.abilities.flying = flying;
    }

    @Override
    public GameMode gameMode() {
        return GameMode.fromName(
                ((ServerPlayerEntity) this.player).interactionManager.getGameMode().name());
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        ((ServerPlayerEntity) player)
                .interactionManager.setGameMode(
                        net.minecraft.world.level.LevelInfo.GameMode.byId(gameMode.id()));
    }
}
