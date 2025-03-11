/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge.event.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.player.PlayerDeathEvent;
import dev.neuralnexus.taterlib.v1_8_9.sponge.SpongeFactories;
import dev.neuralnexus.taterlib.v1_8_9.sponge.entity.player.SpongePlayer;
import dev.neuralnexus.taterlib.v1_8_9.sponge.event.entity.SpongeEntityDeathEvent;

import org.spongepowered.api.event.entity.DestructEntityEvent;
import org.spongepowered.api.text.Text;

/** Sponge implementation of {@link PlayerDeathEvent}. */
public class SpongePlayerDeathEvent extends SpongeEntityDeathEvent implements PlayerDeathEvent {
    private final DestructEntityEvent.Death event;

    public SpongePlayerDeathEvent(DestructEntityEvent.Death event) {
        super(event);
        this.event = event;
    }

    @Override
    public Player player() {
        return new SpongePlayer(
                (org.spongepowered.api.entity.living.player.Player) this.event.getTargetEntity());
    }

    @Override
    public String deathMessage() {
        return this.event.getMessage().toPlain();
    }

    @Override
    public void setDeathMessage(String deathMessage) {
        this.event.setMessage(Text.of(deathMessage));
    }

    // TODO: Prefer Sponge's Death Event when possible
    @Override
    public boolean keepInventory() {
        return SpongeFactories.keepInventory.get(this.event);
    }

    @Override
    public void setKeepInventory(boolean keepInventory) {
        SpongeFactories.setKeepInventory.set(this.event, keepInventory);
    }
}
