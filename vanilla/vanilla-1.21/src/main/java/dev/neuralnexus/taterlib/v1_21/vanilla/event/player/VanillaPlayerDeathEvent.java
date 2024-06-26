/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_21.vanilla.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerDeathEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_21.vanilla.event.entity.VanillaEntityDeathEvent;
import dev.neuralnexus.taterlib.v1_21.vanilla.player.VanillaPlayer;

import net.minecraft.world.damagesource.DamageSource;

/** Vanilla implementation of {@link PlayerDeathEvent}. */
public class VanillaPlayerDeathEvent extends VanillaEntityDeathEvent implements PlayerDeathEvent {
    private final net.minecraft.world.entity.player.Player player;
    private final DamageSource source;

    public VanillaPlayerDeathEvent(
            net.minecraft.world.entity.player.Player player, DamageSource source) {
        super(player, source);
        this.player = player;
        this.source = source;
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new VanillaPlayer(player);
    }

    /** {@inheritDoc} */
    @Override
    public String deathMessage() {
        return source.getLocalizedDeathMessage(player).getString();
    }

    /** {@inheritDoc} */
    @Override
    public void setDeathMessage(String deathMessage) {}

    /** {@inheritDoc} */
    @Override
    public boolean keepInventory() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void setKeepInventory(boolean keepInventory) {}
}
