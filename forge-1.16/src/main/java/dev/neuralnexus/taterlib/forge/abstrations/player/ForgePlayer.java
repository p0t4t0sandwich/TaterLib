package dev.neuralnexus.taterlib.forge.abstrations.player;

import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayerInventory;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

/**
 * Abstracts a Forge player to an AbstractPlayer.
 */
public class ForgePlayer implements AbstractPlayer {
    private final PlayerEntity player;
    private String serverName;

    /**
     * Constructor.
     * @param player The Forge player.
     */
    public ForgePlayer(PlayerEntity player) {
        this.player = player;
        this.serverName = "local";
    }

    /**
     * Constructor.
     * @param player The Forge player.
     * @param serverName The server name.
     */
    public ForgePlayer(PlayerEntity player, String serverName) {
        this.player = player;
        this.serverName = serverName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public java.util.UUID getUUID() {
        return player.getUniqueID();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getName() {
        return player.getName().getString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDisplayName() {
        return player.getDisplayName().getString();
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
        player.sendMessage(new StringTextComponent(message), getUUID());
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
        ((ServerPlayerEntity) player).connection.disconnect(new StringTextComponent(message));
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasPermission(String permission) {
        if (!LuckPermsHook.isHooked()) return player.hasPermissionLevel(4);
        LuckPermsHook luckPermsHook = LuckPermsHook.getInstance();
        return luckPermsHook.playerHasPermission(getUUID(), permission);
    }
}
