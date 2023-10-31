package dev.neuralnexus.taterlib.bukkit.event.player;

import dev.neuralnexus.taterlib.bukkit.event.entity.BukkitEntityDeathEvent;
import dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.common.event.player.PlayerDeathEvent;
import dev.neuralnexus.taterlib.common.player.Player;

/**
 * Bukkit implementation of {@link PlayerDeathEvent}.
 */
public class BukkitPlayerDeathEvent extends BukkitEntityDeathEvent implements PlayerDeathEvent {
    private final org.bukkit.event.entity.PlayerDeathEvent event;

    public BukkitPlayerDeathEvent(org.bukkit.event.entity.PlayerDeathEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Player getPlayer() {
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
