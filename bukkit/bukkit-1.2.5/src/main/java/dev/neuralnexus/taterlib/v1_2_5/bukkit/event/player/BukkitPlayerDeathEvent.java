package dev.neuralnexus.taterlib.v1_2_5.bukkit.event.player;

import dev.neuralnexus.taterlib.v1_2_5.bukkit.event.entity.BukkitEntityDeathEvent;
import dev.neuralnexus.taterlib.v1_2_5.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.event.player.PlayerDeathEvent;
import dev.neuralnexus.taterlib.player.Player;

/** Bukkit implementation of {@link PlayerDeathEvent}. */
public class BukkitPlayerDeathEvent extends BukkitEntityDeathEvent implements PlayerDeathEvent {
    private final org.bukkit.event.entity.PlayerDeathEvent event;

    public BukkitPlayerDeathEvent(org.bukkit.event.entity.PlayerDeathEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new BukkitPlayer(event.getEntity());
    }

    /** {@inheritDoc} */
    @Override
    public String deathMessage() {
        return event.getDeathMessage();
    }

    /** {@inheritDoc} */
    @Override
    public void setDeathMessage(String deathMessage) {
        event.setDeathMessage(deathMessage);
    }

    /** {@inheritDoc} */
    @Override
    public boolean keepInventory() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void setKeepInventory(boolean keepInventory) {}
}
