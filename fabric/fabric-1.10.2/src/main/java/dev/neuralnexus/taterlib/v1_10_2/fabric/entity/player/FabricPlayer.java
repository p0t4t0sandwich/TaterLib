/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_10_2.fabric.entity.player;

import dev.neuralnexus.taterapi.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_10_2.fabric.entity.FabricLivingEntity;
import dev.neuralnexus.taterlib.v1_10_2.fabric.inventory.FabricPlayerInventory;
import dev.neuralnexus.taterlib.v1_10_2.fabric.server.FabricServer;

import io.netty.buffer.Unpooled;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

/** Fabric implementation of {@link Player}. */
public class FabricPlayer extends FabricLivingEntity implements Player {
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

    /**
     * Gets the Fabric player
     *
     * @return The Fabric player
     */
    public PlayerEntity player() {
        return player;
    }

    @Override
    public UUID uuid() {
        return player.getUuid();
    }

    @Override
    public String ipAddress() {
        return ((ServerPlayerEntity) player).getIp();
    }

    @Override
    public String name() {
        return player.getName().asFormattedString();
    }

    @Override
    public String displayName() {
        return player.getCustomName();
    }

    @Override
    public Server server() {
        return new FabricServer(player.getMinecraftServer());
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(new TranslatableText(message));
    }

    @Override
    public void sendPluginMessage(ResourceKey channel, byte[] data) {
        PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());
        byteBuf.writeBytes(data);
        ((ServerPlayerEntity) player)
                .networkHandler.sendPacket(new CustomPayloadS2CPacket(channel.asString(), byteBuf));
    }

    @Override
    public PlayerInventory inventory() {
        return new FabricPlayerInventory(player.inventory);
    }

    @Override
    public int ping() {
        return ((ServerPlayerEntity) player).ping;
    }

    @Override
    public void kick(String message) {
        ((ServerPlayerEntity) player).networkHandler.onDisconnected(new TranslatableText(message));
    }

    @Override
    public void setSpawn(Location location, boolean forced) {
        // TODO: Dimension aware spawn setting
        player.setPlayerSpawn(new BlockPos(location.x(), location.y(), location.z()), forced);
    }

    @Override
    public void allowFlight(boolean allow) {
        player.abilities.allowFlying = allow;
    }

    @Override
    public boolean canFly() {
        return player.abilities.allowFlying;
    }

    @Override
    public boolean isFlying() {
        return player.abilities.flying;
    }

    @Override
    public void setFlying(boolean flying) {
        player.abilities.flying = flying;
    }

    @Override
    public GameMode gameMode() {
        return GameMode.fromName(
                ((ServerPlayerEntity) player).interactionManager.getGameMode().name());
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        ((ServerPlayerEntity) player)
                .interactionManager.setGameMode(
                        net.minecraft.world.GameMode.setGameModeWithId(gameMode.id()));
    }

    @Override
    public String prefix() {
        // TODO: Find a way to get LP prefixes/suffixes
        //        return Options.get(player, "prefix", "");
        return "";
    }

    @Override
    public String suffix() {
        // TODO: Find a way to get LP prefixes/suffixes
        //        return Options.get(player, "suffix", "");
        return "";
    }

    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }
}
