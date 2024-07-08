/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_16.fabric.player;

import dev.neuralnexus.taterapi.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.player.GameMode;
import dev.neuralnexus.taterapi.player.Player;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_16.fabric.entity.FabricLivingEntity;
import dev.neuralnexus.taterlib.v1_16.fabric.inventory.FabricPlayerInventory;
import dev.neuralnexus.taterlib.v1_16.fabric.server.FabricServer;
import dev.neuralnexus.taterlib.v1_16.fabric.world.FabricWorld;

import me.lucko.fabric.api.permissions.v0.Options;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
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

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        return player.getUuid();
    }

    /** {@inheritDoc} */
    @Override
    public String ipAddress() {
        return ((ServerPlayerEntity) player).getIp();
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
        return new FabricServer(player.getServer());
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        player.sendMessage(new LiteralText(message), false);
    }

    /** {@inheritDoc} */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        String[] channelParts = channel.split(":");
        ServerPlayNetworking.send(
                (ServerPlayerEntity) player,
                new Identifier(channelParts[0], channelParts[1]),
                PacketByteBufs.create().writeByteArray(data));
    }

    /** {@inheritDoc} */
    @Override
    public PlayerInventory inventory() {
        return new FabricPlayerInventory(player.inventory);
    }

    /** {@inheritDoc} */
    @Override
    public int ping() {
        return ((ServerPlayerEntity) player).pingMilliseconds;
    }

    /** {@inheritDoc} */
    @Override
    public void kick(String message) {
        ((ServerPlayerEntity) player).networkHandler.disconnect(new LiteralText(message));
    }

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location, boolean forced) {
        ((ServerPlayerEntity) player)
                .setSpawnPoint(
                        ((FabricWorld) location.world()).world().getRegistryKey(),
                        new BlockPos(location.x(), location.y(), location.z()),
                        forced,
                        false);
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
        return player.abilities.flying;
    }

    /** {@inheritDoc} */
    @Override
    public void setFlying(boolean flying) {
        player.abilities.flying = flying;
    }

    /** {@inheritDoc} */
    @Override
    public GameMode gameMode() {
        return GameMode.fromName(
                ((ServerPlayerEntity) player).interactionManager.getGameMode().name());
    }

    /** {@inheritDoc} */
    @Override
    public void setGameMode(GameMode gameMode) {
        ((ServerPlayerEntity) player)
                .interactionManager.method_30118(net.minecraft.world.GameMode.byId(gameMode.id()));
    }

    /** {@inheritDoc} */
    @Override
    public String prefix() {
        return Options.get(player, "prefix", "");
    }

    /** {@inheritDoc} */
    @Override
    public String suffix() {
        return Options.get(player, "suffix", "");
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return player.hasPermissionLevel(permissionLevel);
    }
}
