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
        return this.player.getCommandSenderName();
    }

    @Override
    public String displayName() {
        return this.player.getDisplayName();
    }

    @Override
    public Server server() {
        return new ForgeServer(((EntityPlayerMP) this.player).mcServer);
    }

    @Override
    public void sendMessage(String message) {
        this.player.addChatMessage(message);
    }

    @Override
    public void sendPacket(ResourceKey channel, byte[] data) {
        ((EntityPlayerMP) this.player)
                .playerNetServerHandler.sendPacketToPlayer(
                        new Packet250CustomPayload(channel.asString(), data));
    }

    @Override
    public PlayerInventory inventory() {
        return new ForgePlayerInventory(this.player.inventory);
    }

    @Override
    public int ping() {
        return ((EntityPlayerMP) this.player).ping;
    }

    @Override
    public void kick(String message) {
        ((EntityPlayerMP) this.player).playerNetServerHandler.kickPlayerFromServer(message);
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void setSpawn(Location location, boolean forced) {
        Optional<WorldServer> serverLevel =
                ((Server) TaterAPIProvider.api().get().server())
                        .world(location.world().dimension())
                        .map(ForgeServerWorld.class::cast)
                        .map(ForgeServerWorld::unwrap);
        if (!serverLevel.isPresent()) return;
        this.player.setSpawnChunk(
                new ChunkCoordinates((int) location.x(), (int) location.y(), (int) location.z()),
                forced,
                serverLevel.get().provider.dimensionId);
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
        return GameMode.fromName(
                ((EntityPlayerMP) this.player).theItemInWorldManager.getGameType().getName());
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        this.player.setGameType(EnumGameType.getByID(gameMode.id()));
    }
}
