/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_9_4.forge.event.player;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.player.PlayerDeathEvent;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.WrappedEntity;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.player.WrappedPlayer;
import dev.neuralnexus.taterlib.v1_9_4.forge.event.entity.ForgeEntityDeathEvent;

import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

/** Forge implementation of {@link PlayerDeathEvent}. */
public class ForgePlayerDeathEvent extends ForgeEntityDeathEvent implements PlayerDeathEvent {
    private final LivingDeathEvent event;

    public ForgePlayerDeathEvent(LivingDeathEvent event) {
        super(event);
        this.event = event;
    }

    @Override
    public Entity entity() {
        return new WrappedEntity(this.event.getEntity());
    }

    @Override
    public Player player() {
        return new WrappedPlayer((PlayerEntity) this.event.getEntity());
    }

    @Override
    public String deathMessage() {
        return this.event
                .getSource()
                .getDeathMessage(this.event.getEntityLiving())
                .getFormattedString();
    }

    // TODO: Hook into Forge's event system somehow (probs mixin)
    @Override
    public void setDeathMessage(String deathMessage) {
        throw new VersionFeatureNotSupportedException();
    }

    // TODO: Set up per-player keepInventory module
    @Override
    public boolean keepInventory() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setKeepInventory(boolean keepInventory) {
        throw new VersionFeatureNotSupportedException();
    }
}
