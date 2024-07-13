/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_16_3.forge.player;

import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_16_3.forge.entity.ForgeLivingEntity;
import dev.neuralnexus.taterlib.v1_16_3.forge.item.inventory.ForgePlayerInventory;
import dev.neuralnexus.taterlib.v1_16_3.forge.server.ForgeServer;
import dev.neuralnexus.taterlib.v1_16_3.forge.world.ForgeWorld;

import io.netty.buffer.Unpooled;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CCustomPayloadPacket;
import net.minecraft.util.ResourceLocation;
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

    @Override
    public UUID uuid() {
        return player.getUUID();
    }

    @Override
    public String ipAddress() {
        return ((ServerPlayerEntity) player).getIpAddress();
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
        return new ForgeServer(player.getServer());
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(new StringTextComponent(message), uuid());
    }

    @Override
    public void sendPluginMessage(ResourceKey channel, byte[] data) {
        ResourceLocation id = (ResourceLocation) (Object) channel;
        PacketBuffer byteBuf = new PacketBuffer(Unpooled.buffer());
        byteBuf.writeBytes(data);
        ((ServerPlayerEntity) player).connection.send(new CCustomPayloadPacket(id, byteBuf));
    }

    @Override
    public PlayerInventory inventory() {
        return new ForgePlayerInventory(player.inventory);
    }

    @Override
    public int ping() {
        return ((ServerPlayerEntity) player).latency;
    }

    @Override
    public void kick(String message) {
        ((ServerPlayerEntity) player).connection.disconnect(new StringTextComponent(message));
    }

    @Override
    public void setSpawn(Location location, boolean forced) {
        ((ServerPlayerEntity) player)
                .setRespawnPosition(
                        ((ForgeWorld) location.world()).world().dimension(),
                        new BlockPos(location.x(), location.y(), location.z()),
                        0.0F,
                        forced,
                        false);
    }

    @Override
    public void allowFlight(boolean allow) {
        player.abilities.mayfly = allow;
    }

    @Override
    public boolean canFly() {
        return player.abilities.mayfly;
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
                ((ServerPlayerEntity) player).gameMode.getGameModeForPlayer().getName());
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        player.setGameMode(net.minecraft.world.GameType.byId(gameMode.id()));
    }

    @Override
    public boolean hasPermission(int permissionLevel) {
        return player.hasPermissions(permissionLevel);
    }
}
