package dev.neuralnexus.taterlib.bukkit.event.api.player;

import dev.neuralnexus.taterlib.bukkit.event.api.entity.BukkitEntityDeathEvent;
import dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.common.event.player.PlayerDeathEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * Bukkit implementation of {@link PlayerDeathEvent}.
 */
public class BukkitPlayerDeathEvent extends BukkitEntityDeathEvent implements PlayerDeathEvent {
    private final EntityDeathEvent event;
    private String deathMessage = "";

    public BukkitPlayerDeathEvent(EntityDeathEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return new BukkitPlayer((org.bukkit.entity.Player) event.getEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathMessage() {
        if (!deathMessage.isEmpty()) {
            return deathMessage;
        }
        return getPlayer().getName() + " died";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDeathMessage(String deathMessage) {
        this.deathMessage = deathMessage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasKeepInventory() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setKeepInventory(boolean keepInventory) {}
}
