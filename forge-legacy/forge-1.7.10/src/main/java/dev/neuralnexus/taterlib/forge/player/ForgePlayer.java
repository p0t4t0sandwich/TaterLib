package dev.neuralnexus.taterlib.forge.player;

import dev.neuralnexus.taterlib.forge.entity.ForgeLivingEntity;
import dev.neuralnexus.taterlib.forge.inventory.ForgePlayerInventory;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.player.GameMode;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.utils.Location;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;

import java.util.UUID;

/** Forge implementation of {@link Player}. */
public class ForgePlayer extends ForgeLivingEntity implements Player {
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
    public String displayName() {
        return player.getDisplayName();
    }

    /** {@inheritDoc} */
    @Override
    public String serverName() {
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
        player.setSpawnChunk(
                new ChunkCoordinates((int) location.x(), (int) location.y(), (int) location.z()),
                forced);
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
        return GameMode.fromName(
                ((EntityPlayerMP) player).theItemInWorldManager.getGameType().getName());
    }

    /** {@inheritDoc} */
    @Override
    public void setGameMode(GameMode gameMode) {
        player.setGameType(net.minecraft.world.WorldSettings.GameType.getByID(gameMode.id()));
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }
}
