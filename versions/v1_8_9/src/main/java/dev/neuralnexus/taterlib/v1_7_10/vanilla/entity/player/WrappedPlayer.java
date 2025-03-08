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
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.WrappedLivingEntity;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.item.inventory.WrappedPlayerInventory;

import io.netty.buffer.Unpooled;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.WorldSettings;

import java.util.UUID;

/** Vanilla implementation of {@link Player}. */
public class WrappedPlayer extends WrappedLivingEntity implements Player, ServerPlayer {
    private final EntityPlayer player;

    public WrappedPlayer(EntityPlayer player) {
        super(player);
        this.player = player;
    }

    @Override
    public EntityPlayer unwrap() {
        return this.player;
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
        return this.player.getName();
    }

    @Override
    public String displayName() {
        return this.player.getDisplayName().getFormattedText();
    }

    @Override
    public void sendMessage(String message) {
        player.addChatMessage(new ChatComponentText(message));
    }

    @Override
    public void sendPacket(ResourceKey channel, byte[] data) {
        PacketBuffer byteBuf = new PacketBuffer(Unpooled.buffer());
        byteBuf.writeBytes(data);
        ((EntityPlayerMP) this.player)
                .playerNetServerHandler.sendPacket(
                        new S3FPacketCustomPayload(channel.asString(), byteBuf));
    }

    @Override
    public PlayerInventory inventory() {
        return new WrappedPlayerInventory(this.player.inventory);
    }

    @Override
    public int ping() {
        return ((EntityPlayerMP) this.player).ping;
    }

    @Override
    public void kick(String message) {
        ((EntityPlayerMP) this.player)
                .playerNetServerHandler.onDisconnect(new ChatComponentText(message));
    }

    @Override
    public void setSpawn(Location location, boolean forced) {
        this.player.setSpawnPoint(
                new BlockPos((int) location.x(), (int) location.y(), (int) location.z()), forced);
    }

    @Override
    public void allowFlight(boolean allow) {
        this.player.capabilities.allowFlying = allow;
    }

    @Override
    public boolean canFly() {
        return this.player.capabilities.allowFlying;
    }

    @Override
    public boolean isFlying() {
        return this.player.capabilities.isFlying;
    }

    @Override
    public void setFlying(boolean flying) {
        this.player.capabilities.isFlying = flying;
    }

    @Override
    public GameMode gameMode() {
        WorldSettings.GameType gameType =
                ((EntityPlayerMP) this.player).theItemInWorldManager.getGameType();
        return GameMode.fromName(gameType.getName());
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        this.player.setGameType((WorldSettings.getGameTypeById(gameMode.id())));
    }
}
