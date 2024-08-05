/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_16.vanilla.event.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.player.PlayerDeathEvent;
import dev.neuralnexus.taterlib.v1_16.vanilla.event.entity.VanillaEntityDeathEvent;

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

    @Override
    public Player player() {
        return (Player) player;
    }

    @Override
    public String deathMessage() {
        return source.getLocalizedDeathMessage(player).getString();
    }

    @Override
    public void setDeathMessage(String deathMessage) {}

    @Override
    public boolean keepInventory() {
        return false;
    }

    @Override
    public void setKeepInventory(boolean keepInventory) {}
}
