package dev.neuralnexus.taterlib.forge.player;

import dev.neuralnexus.taterlib.forge.entity.ForgeEntity;
import dev.neuralnexus.taterlib.forge.inventory.ForgePlayerInventory;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.player.GameMode;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.utils.Location;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

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
    public String getIPAddress(){return ((EntityPlayerMP) player).getPlayerIP();}

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return player.getName();
    }

    /** {@inheritDoc} */
    @Override
    public String getDisplayName() {
        return player.getDisplayName().getFormattedText();
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
    public void sendPluginMessage(String channel, byte[] data) {}

    /** {@inheritDoc} */
    @Override
    public PlayerInventory getInventory() {
        return new ForgePlayerInventory(player.inventory);
    }

    /** {@inheritDoc} */
    @Override
    public int getPing() {
        return ((EntityPlayerMP) player).ping;
    }

    /** {@inheritDoc} */
    @Override
    public void kickPlayer(String message) {
        ((EntityPlayerMP) player).connection.disconnect(message);
    }

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location, boolean forced) {
        player.setSpawnPoint(
                new BlockPos(location.getX(), location.getY(), location.getZ()), forced);
    }

    /** {@inheritDoc} */
    @Override
    public GameMode getGameMode() {
        return GameMode.fromName(
                ((EntityPlayerMP) player).interactionManager.getGameType().getName());
    }

    /** {@inheritDoc} */
    @Override
    public void setGameMode(GameMode gameMode) {
        player.setGameType(net.minecraft.world.GameType.getByID(gameMode.getId()));
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }
}
