package dev.neuralnexus.taterlib.sponge.abstractions.player;

import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayerInventory;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

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
        return player.getUniqueId();
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
        return player.getDisplayNameData().displayName().get().toPlain();
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
        player.sendMessage(Text.of(message));
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayerInventory getInventory() {
        return new SpongePlayerInventory(player.getInventory().first());
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasPermission(String permission) {
        return player.hasPermission(permission);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void kickPlayer(String message) {
        player.kick(Text.of(message));
    }
}
