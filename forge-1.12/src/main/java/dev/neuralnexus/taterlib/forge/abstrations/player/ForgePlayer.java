package dev.neuralnexus.taterlib.forge.abstrations.player;

import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayerInventory;
import dev.neuralnexus.taterlib.common.abstractions.utils.Position;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.forge.abstrations.util.ForgeConversions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.server.permission.PermissionAPI;

import java.util.UUID;

/**
 * Abstracts a Forge player to an AbstractPlayer.
 */
public class ForgePlayer implements AbstractPlayer {
    private final EntityPlayer player;
    private String serverName;

    /**
     * Constructor.
     * @param player The Forge player.
     */
    public ForgePlayer(EntityPlayer player) {
        this.player = player;
        this.serverName = "local";
    }

    /**
     * Constructor.
     * @param player The Forge player.
     * @param serverName The server name.
     */
    public ForgePlayer(EntityPlayer player, String serverName) {
        this.player = player;
        this.serverName = serverName;
    }

    /**
     * Gets the Forge player
     * @return The Forge player
     */
    public EntityPlayer getPlayer() {
        return player;
    }

    /**
     * @inheritDoc
     */
    @Override
    public UUID getUUID() {
        return player.getUniqueID();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getName() {
        return player.getName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDisplayName() {
        return player.getDisplayName().getFormattedText();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Position getPosition() {
        return ForgeConversions.positionFromVector(player.getPositionVector());
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getServerName() {
        return serverName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setServerName(String server) {
        this.serverName = server;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void sendMessage(String message) {
        player.sendMessage(new TextComponentString(message));
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayerInventory getInventory() {
        return new ForgePlayerInventory(player.inventory);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void kickPlayer(String message) {
        ((EntityPlayerMP) player).connection.disconnect(new TextComponentString(message));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setSpawn(Position position) {
        player.setSpawnPoint(ForgeConversions.locationFromPosition(position), true);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasPermission(String permission) {
        if (!LuckPermsHook.isHooked()) return PermissionAPI.hasPermission(player, permission);
        LuckPermsHook luckPermsHook = LuckPermsHook.getInstance();
        return luckPermsHook.playerHasPermission(getUUID(), permission);
    }
}
