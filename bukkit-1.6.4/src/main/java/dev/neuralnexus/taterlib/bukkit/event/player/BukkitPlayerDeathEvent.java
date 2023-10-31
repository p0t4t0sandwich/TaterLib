package dev.neuralnexus.taterlib.bukkit.event.player;

import dev.neuralnexus.taterlib.bukkit.event.entity.BukkitEntityDeathEvent;
import dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.common.event.player.AbstractPlayerDeathEvent;
import dev.neuralnexus.taterlib.common.player.AbstractPlayer;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Bukkit implementation of {@link AbstractPlayerDeathEvent}.
 */
public class BukkitPlayerDeathEvent extends BukkitEntityDeathEvent implements AbstractPlayerDeathEvent {
    private final PlayerDeathEvent event;

    public BukkitPlayerDeathEvent(PlayerDeathEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new BukkitPlayer(event.getEntity());
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDeathMessage() {
        return event.getDeathMessage();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDeathMessage(String deathMessage) {
        event.setDeathMessage(deathMessage);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasKeepInventory() {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setKeepInventory(boolean keepInventory) {}
}
