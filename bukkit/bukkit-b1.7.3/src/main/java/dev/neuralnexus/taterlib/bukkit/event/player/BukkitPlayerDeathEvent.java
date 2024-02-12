package dev.neuralnexus.taterlib.bukkit.event.player;

import dev.neuralnexus.taterlib.bukkit.event.entity.BukkitEntityDeathEvent;
import dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.event.player.PlayerDeathEvent;
import dev.neuralnexus.taterlib.player.Player;

import org.bukkit.event.entity.EntityDeathEvent;

/** Bukkit implementation of {@link PlayerDeathEvent}. */
public class BukkitPlayerDeathEvent extends BukkitEntityDeathEvent implements PlayerDeathEvent {
    private final EntityDeathEvent event;
    private String deathMessage = "";

    public BukkitPlayerDeathEvent(EntityDeathEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new BukkitPlayer((org.bukkit.entity.Player) event.getEntity());
    }

    /** {@inheritDoc} */
    @Override
    public String deathMessage() {
        if (!deathMessage.isEmpty()) {
            return deathMessage;
        }
        return player().name() + " died";
    }

    /** {@inheritDoc} */
    @Override
    public void setDeathMessage(String deathMessage) {
        this.deathMessage = deathMessage;
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
