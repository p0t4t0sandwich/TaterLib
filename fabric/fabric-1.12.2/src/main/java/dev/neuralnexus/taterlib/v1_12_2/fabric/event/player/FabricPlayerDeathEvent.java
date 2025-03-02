/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12_2.fabric.event.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.player.PlayerDeathEvent;
import dev.neuralnexus.taterlib.v1_12_2.fabric.entity.player.FabricPlayer;
import dev.neuralnexus.taterlib.v1_12_2.fabric.event.entity.FabricEntityDeathEvent;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

public class FabricPlayerDeathEvent extends FabricEntityDeathEvent implements PlayerDeathEvent {
    private final PlayerEntity player;
    private final DamageSource source;

    public FabricPlayerDeathEvent(PlayerEntity player, DamageSource source) {
        super(player);
        this.player = player;
        this.source = source;
    }

    @Override
    public Player player() {
        return new FabricPlayer(player);
    }

    @Override
    public String deathMessage() {
        return source.getDeathMessage(player).asFormattedString();
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
