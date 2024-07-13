/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_10_2.forge.player;

import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_10_2.forge.ForgeTaterLibPlugin;
import dev.neuralnexus.taterlib.v1_10_2.forge.entity.ForgeLivingEntity;
import dev.neuralnexus.taterlib.v1_10_2.forge.item.inventory.ForgePlayerInventory;
import dev.neuralnexus.taterlib.v1_10_2.forge.server.ForgeServer;
import dev.neuralnexus.taterlib.v1_10_2.forge.world.ForgeServerWorld;

import io.netty.buffer.Unpooled;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketCustomPayload;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.WorldServer;

import java.util.Optional;
import java.util.UUID;

/** Forge implementation of {@link Player}. */
public class ForgePlayer extends ForgeLivingEntity implements Player {
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
    public EntityPlayer getPlayer() {
        return player;
    }

    @Override
    public UUID uuid() {
        return player.getUniqueID();
    }

    @Override
    public String ipAddress() {
        return ((EntityPlayerMP) player).getPlayerIP();
    }

    @Override
    public String name() {
        return player.getName();
    }

    @Override
    public String displayName() {
        return player.getDisplayName().getFormattedText();
    }

    @Override
    public Server server() {
        return new ForgeServer(player.getServer());
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(new TextComponentString(message));
    }

    @Override
    public void sendPluginMessage(ResourceKey channel, byte[] data) {
        PacketBuffer byteBuf = new PacketBuffer(Unpooled.buffer());
        byteBuf.writeBytes(data);
        ((EntityPlayerMP) player)
                .connection.sendPacket(new SPacketCustomPayload(channel.asString(), byteBuf));
    }

    @Override
    public PlayerInventory inventory() {
        return new ForgePlayerInventory(player.inventory);
    }

    @Override
    public int ping() {
        return ((EntityPlayerMP) player).ping;
    }

    @Override
    public void kick(String message) {
        ((EntityPlayerMP) player).connection.onDisconnect(new TextComponentString(message));
    }

    @Override
    public void setSpawn(Location location, boolean forced) {
        Optional<WorldServer> serverLevel =
                new ForgeServer(ForgeTaterLibPlugin.minecraftServer)
                        .world(location.world().dimension())
                        .map(ForgeServerWorld.class::cast)
                        .map(ForgeServerWorld::world);
        if (!serverLevel.isPresent()) return;
        player.setSpawnChunk(
                new BlockPos(location.x(), location.y(), location.z()),
                forced,
                serverLevel.get().provider.getDimensionType().getId());
    }

    @Override
    public void allowFlight(boolean allow) {
        player.capabilities.allowFlying = allow;
    }

    @Override
    public boolean canFly() {
        return player.capabilities.allowFlying;
    }

    @Override
    public boolean isFlying() {
        return player.capabilities.isFlying;
    }

    @Override
    public void setFlying(boolean flying) {
        player.capabilities.isFlying = flying;
    }

    @Override
    public GameMode gameMode() {
        return GameMode.fromName(
                ((EntityPlayerMP) player).interactionManager.getGameType().getName());
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        player.setGameType(net.minecraft.world.GameType.getByID(gameMode.id()));
    }

    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }
}
