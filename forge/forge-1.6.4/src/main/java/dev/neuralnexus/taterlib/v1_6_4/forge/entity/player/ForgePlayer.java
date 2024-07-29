/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_6_4.forge.entity.player;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_6_4.forge.entity.ForgeLivingEntity;
import dev.neuralnexus.taterlib.v1_6_4.forge.item.inventory.ForgePlayerInventory;
import dev.neuralnexus.taterlib.v1_6_4.forge.server.ForgeServer;
import dev.neuralnexus.taterlib.v1_6_4.forge.world.ForgeServerWorld;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.WorldServer;

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
        return player.getCommandSenderName();
    }

    @Override
    public String displayName() {
        return player.getDisplayName();
    }

    @Override
    public Server server() {
        return new ForgeServer(((EntityPlayerMP) player).mcServer);
    }

    @Override
    public void sendMessage(String message) {
        player.addChatMessage(message);
    }

    @Override
    public void sendPluginMessage(ResourceKey channel, byte[] data) {
        ((EntityPlayerMP) player)
                .playerNetServerHandler.sendPacketToPlayer(
                        new Packet250CustomPayload(channel.asString(), data));
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
        ((EntityPlayerMP) player).playerNetServerHandler.kickPlayerFromServer(message);
    }

    @Override
    public void setSpawn(Location location, boolean forced) {
        Optional<WorldServer> serverLevel =
                ((Server) TaterAPIProvider.api().get().server())
                        .world(location.world().dimension())
                        .map(ForgeServerWorld.class::cast)
                        .map(ForgeServerWorld::world);
        if (!serverLevel.isPresent()) return;
        player.setSpawnChunk(
                new ChunkCoordinates((int) location.x(), (int) location.y(), (int) location.z()),
                forced,
                serverLevel.get().provider.dimensionId);
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
                ((EntityPlayerMP) player).theItemInWorldManager.getGameType().getName());
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        player.setGameType(EnumGameType.getByID(gameMode.id()));
    }

    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }
}
