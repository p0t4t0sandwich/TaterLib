/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.event.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.player.PlayerDeathEvent;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.event.entity.VanillaEntityDeathEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

/** Vanilla implementation of {@link PlayerDeathEvent}. */
public class VanillaPlayerDeathEvent extends VanillaEntityDeathEvent implements PlayerDeathEvent {
    private final EntityPlayer player;
    private final DamageSource source;

    public VanillaPlayerDeathEvent(EntityPlayer player, DamageSource source) {
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
        return this.source.getDeathMessage(this.player).getFormattedText();
    }

    // TODO: Hook into Vanilla's message somehow (probs mixin)
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
