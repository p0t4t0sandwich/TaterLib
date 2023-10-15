package dev.neuralnexus.taterlib.fabric.abstractions.player;

import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayerInventory;
import dev.neuralnexus.taterlib.common.abstractions.utils.Position;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.fabric.abstractions.util.FabricConversions;
import me.lucko.fabric.api.permissions.v0.Options;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.UUID;

/**
 * Abstracts a Fabric player to an AbstractPlayer.
 */
public class FabricPlayer implements AbstractPlayer {
    private final PlayerEntity player;
    private String serverName;

    /**
     * Constructor.
     * @param player The Fabric player.
     */
    public FabricPlayer(PlayerEntity player) {
        this.player = player;
        this.serverName = "local";
    }

    /**
     * Constructor.
     * @param player The Fabric player.
     * @param serverName The server name.
     */
    public FabricPlayer(PlayerEntity player, String serverName) {
        this.player = player;
        this.serverName = serverName;
    }

    /**
     * Gets the Fabric player
     * @return The Fabric player
     */
    public PlayerEntity getPlayer() {
        return player;
    }

    /**
     * @inheritDoc
     */
    @Override
    public UUID getUUID() {
        return player.getUuid();
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
    public Position getPosition() {
        return FabricConversions.positionFromVector(player.getPos());
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
        player.sendMessage(Text.of(message), false);
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayerInventory getInventory() {
        return new FabricPlayerInventory(player.getInventory());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void kickPlayer(String message) {
        ((ServerPlayerEntity) player).networkHandler.disconnect(Text.of(message));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setSpawn(Position position) {
        ((ServerPlayerEntity) player).setSpawnPoint(World.OVERWORLD, FabricConversions.locationFromPosition(position), 0, true, false);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getPrefix() {
        if (!LuckPermsHook.isHooked()) return "";
        return Options.get(player, "prefix", "");
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getSuffix() {
        if (!LuckPermsHook.isHooked()) return "";
        return Options.get(player, "suffix", "");
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasPermission(String permission) {
        if (!LuckPermsHook.isHooked()) return player.hasPermissionLevel(4);
        return Permissions.check(player, permission, 4);
    }
}
