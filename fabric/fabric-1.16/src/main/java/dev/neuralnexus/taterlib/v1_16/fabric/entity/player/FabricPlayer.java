/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_16.fabric.entity.player;

import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_16.fabric.entity.FabricLivingEntity;
import dev.neuralnexus.taterlib.v1_16.fabric.item.inventory.FabricPlayerInventory;
import dev.neuralnexus.taterlib.v1_16.fabric.server.FabricServer;
import dev.neuralnexus.taterlib.v1_16.fabric.world.FabricWorld;

import io.netty.buffer.Unpooled;

import me.lucko.fabric.api.permissions.v0.Options;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
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
        return player.getName().getString();
    }

    @Override
    public String displayName() {
        return player.getDisplayName().getString();
    }

    @Override
    public Server server() {
        return new FabricServer(player.getServer());
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(new LiteralText(message), false);
    }

    @Override
    public void sendPluginMessage(ResourceKey channel, byte[] data) {
        Identifier id = (Identifier) (Object) channel;
        PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());
        byteBuf.writeBytes(data);
        ((ServerPlayerEntity) player)
                .networkHandler.sendPacket(new CustomPayloadS2CPacket(id, byteBuf));
    }

    @Override
    public PlayerInventory inventory() {
        return new FabricPlayerInventory(player.inventory);
    }

    @Override
    public int ping() {
        return ((ServerPlayerEntity) player).pingMilliseconds;
    }

    @Override
    public void kick(String message) {
        ((ServerPlayerEntity) player).networkHandler.disconnect(new LiteralText(message));
    }

    @Override
    public void setSpawn(Location location, boolean forced) {
        ((ServerPlayerEntity) player)
                .setSpawnPoint(
                        ((FabricWorld) location.world()).world().getRegistryKey(),
                        new BlockPos(location.x(), location.y(), location.z()),
                        forced,
                        false);
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
                .interactionManager.method_30118(net.minecraft.world.GameMode.byId(gameMode.id()));
    }

    @Override
    public String prefix() {
        return Options.get(player, "prefix", "");
    }

    @Override
    public String suffix() {
        return Options.get(player, "suffix", "");
    }

    @Override
    public boolean hasPermission(int permissionLevel) {
        return player.hasPermissionLevel(permissionLevel);
    }
}
