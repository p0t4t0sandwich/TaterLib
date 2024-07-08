/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_7_10.forge.player;

import dev.neuralnexus.taterapi.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.player.GameMode;
import dev.neuralnexus.taterapi.player.Player;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_7_10.forge.ForgeTaterLibPlugin;
import dev.neuralnexus.taterlib.v1_7_10.forge.entity.ForgeLivingEntity;
import dev.neuralnexus.taterlib.v1_7_10.forge.inventory.ForgePlayerInventory;
import dev.neuralnexus.taterlib.v1_7_10.forge.server.ForgeServer;
import dev.neuralnexus.taterlib.v1_7_10.forge.world.ForgeServerWorld;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
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

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        return player.getUniqueID();
    }

    /** {@inheritDoc} */
    @Override
    public String ipAddress() {
        return ((EntityPlayerMP) player).getPlayerIP();
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return player.getCommandSenderName();
    }

    /** {@inheritDoc} */
    @Override
    public Optional<String> displayName() {
        return player.getDisplayName();
    }

    /** {@inheritDoc} */
    @Override
    public Server server() {
        return new ForgeServer(((EntityPlayerMP) player).mcServer);
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        player.addChatMessage(new ChatComponentText(message));
    }

    /** {@inheritDoc} */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {}

    /** {@inheritDoc} */
    @Override
    public PlayerInventory inventory() {
        return new ForgePlayerInventory(player.inventory);
    }

    /** {@inheritDoc} */
    @Override
    public int ping() {
        return ((EntityPlayerMP) player).ping;
    }

    /** {@inheritDoc} */
    @Override
    public void kick(String message) {
        ((EntityPlayerMP) player)
                .playerNetServerHandler.onDisconnect(new ChatComponentText(message));
    }

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location, boolean forced) {
        Optional<WorldServer> serverLevel =
                new ForgeServer(ForgeTaterLibPlugin.minecraftServer)
                        .world(location.world().dimension())
                        .map(ForgeServerWorld.class::cast)
                        .map(ForgeServerWorld::world);
        if (!serverLevel.isPresent()) return;
        player.setSpawnChunk(
                new ChunkCoordinates((int) location.x(), (int) location.y(), (int) location.z()),
                forced,
                serverLevel.get().provider.dimensionId);
    }

    /** {@inheritDoc} */
    @Override
    public void allowFlight(boolean allow) {
        player.capabilities.allowFlying = allow;
    }

    /** {@inheritDoc} */
    @Override
    public boolean canFly() {
        return player.capabilities.allowFlying;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isFlying() {
        return player.capabilities.isFlying;
    }

    /** {@inheritDoc} */
    @Override
    public void setFlying(boolean flying) {
        player.capabilities.isFlying = flying;
    }

    /** {@inheritDoc} */
    @Override
    public GameMode gameMode() {
        // TODO: Fix once mapping issue is resolved
        //  EntityPlayer.itemInWorldManager.getGameType().getName() is
        //  EntityPlayer.field_71134_c.func_73081_b().func_77149_b()
        try {
            ItemInWorldManager itemInWorldManager = ((EntityPlayerMP) player).theItemInWorldManager;
            Object gameType =
                    itemInWorldManager
                            .getClass()
                            .getMethod("func_73081_b")
                            .invoke(itemInWorldManager);
            return GameMode.fromName(
                    gameType.getClass().getMethod("func_77149_b").invoke(gameType).toString());
        } catch (Exception e) {
            return GameMode.UNKNOWN;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setGameMode(GameMode gameMode) {
        player.setGameType((net.minecraft.world.WorldSettings.getGameTypeById(gameMode.id())));
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }
}
