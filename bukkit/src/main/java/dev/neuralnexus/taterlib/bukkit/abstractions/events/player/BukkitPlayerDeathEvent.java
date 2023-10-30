package dev.neuralnexus.taterlib.bukkit.abstractions.events.player;

import dev.neuralnexus.taterlib.bukkit.abstractions.events.entity.BukkitEntityDeathEvent;
import dev.neuralnexus.taterlib.bukkit.abstractions.player.BukkitPlayer;
import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerDeathEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
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
        return event.getKeepInventory();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setKeepInventory(boolean keepInventory) {
        event.setKeepInventory(keepInventory);
    }
}
