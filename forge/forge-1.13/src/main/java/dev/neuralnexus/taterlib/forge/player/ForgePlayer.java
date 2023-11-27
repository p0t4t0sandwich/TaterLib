package dev.neuralnexus.taterlib.forge.player;

import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.common.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.forge.entity.ForgeEntity;
import dev.neuralnexus.taterlib.forge.inventory.ForgePlayerInventory;
import dev.neuralnexus.taterlib.forge.networking.ModMessages;
import dev.neuralnexus.taterlib.forge.networking.packet.ForgeMessagePacket;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.dimension.DimensionType;

import java.util.UUID;

/** Forge implementation of {@link Player}. */
public class ForgePlayer extends ForgeEntity implements Player {
    private final EntityPlayer player;
    private String serverName;

    /**
     * Constructor.
     *
     * @param player The Forge player.
     */
    public ForgePlayer(EntityPlayer player) {
        super(player);
        this.player = player;
        this.serverName = "local";
    }

    /**
     * Constructor.
     *
     * @param player The Forge player.
     * @param serverName The server name.
     */
    public ForgePlayer(EntityPlayer player, String serverName) {
        super(player);
        this.player = player;
        this.serverName = serverName;
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
    public UUID getUniqueId() {
        return player.getUniqueID();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return player.getName().getString();
    }

    /** {@inheritDoc} */
    @Override
    public String getDisplayName() {
        return player.getDisplayName().getString();
    }

    /** {@inheritDoc} */
    @Override
    public String getServerName() {
        return serverName;
    }

    /** {@inheritDoc} */
    @Override
    public void setServerName(String server) {
        this.serverName = server;
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        player.sendMessage(new TextComponentString(message));
    }

    /** {@inheritDoc} */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        ModMessages.sendPluginMessage(
                new ForgeMessagePacket(new String(data)), channel, (EntityPlayerMP) player);
    }

    /** {@inheritDoc} */
    @Override
    public PlayerInventory getInventory() {
        return new ForgePlayerInventory(player.inventory);
    }

    /** {@inheritDoc} */
    @Override
    public void kickPlayer(String message) {
        ((EntityPlayerMP) player).connection.disconnect(new TextComponentString(message));
    }

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location, boolean forced) {
        player.setSpawnPoint(
                new BlockPos(location.getX(), location.getY(), location.getZ()),
                forced,
                DimensionType.OVERWORLD);
    }

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location) {
        setSpawn(location, false);
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(String permission) {
        if (!TaterAPIProvider.isHooked("luckperms")) return player.hasPermissionLevel(4);
        LuckPermsHook luckPermsHook = LuckPermsHook.get();
        return luckPermsHook.playerHasPermission(getUniqueId(), permission);
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return player.hasPermissionLevel(permissionLevel);
    }
}
