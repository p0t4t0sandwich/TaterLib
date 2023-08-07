package dev.neuralnexus.taterlib.sponge.abstractions.player;

import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayerInventory;
import net.kyori.adventure.text.Component;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;

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
}
