package dev.neuralnexus.taterlib.sponge.abstractions.player;

import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayerInventory;
import dev.neuralnexus.taterlib.common.abstractions.utils.Position;
import dev.neuralnexus.taterlib.sponge.abstractions.util.SpongeConversions;
import net.kyori.adventure.text.Component;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.network.channel.ChannelManager;
import org.spongepowered.api.network.channel.raw.RawDataChannel;

import java.util.UUID;

/**
 * Abstracts a Sponge player to an AbstractPlayer.
 */
public class SpongePlayer implements AbstractPlayer {
    private final Player player;
    private String serverName;

    /**
     * Constructor.
     * @param player The Sponge player.
     */
    public SpongePlayer(Player player) {
        this.player = player;
        this.serverName = "local";
    }

    /**
     * Constructor.
     * @param player The Sponge player.
     * @param serverName The name of the server the player is on.
     */
    public SpongePlayer(Player player, String serverName) {
        this.player = player;
        this.serverName = serverName;
    }

    /**
     * Gets the Sponge player
     * @return The Sponge player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @inheritDoc
     */
    @Override
    public UUID getUUID() {
        return player.uniqueId();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getName() {
        return player.name();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDisplayName() {
        return player.displayName().get().toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Position getPosition() {
        return SpongeConversions.positionFromVector(player.position());
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
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void sendMessage(String message) {
        player.sendMessage(Component.text(message));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        ChannelManager channelManager = Sponge.channelManager();
        String[] channelParts = channel.split(":");
        channelManager.ofType(ResourceKey.of(channelParts[0], channelParts[1]), RawDataChannel.class).play().sendTo((ServerPlayer) player, (buffer) -> buffer.writeBytes(data));
    }


    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayerInventory getInventory() {
        return new SpongePlayerInventory(player.inventory());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void kickPlayer(String message) {
        ((ServerPlayer) player).kick(Component.text(message));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setSpawn(Position position) {}

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasPermission(String permission) {
        return ((ServerPlayer) player).hasPermission(permission);
    }
}
