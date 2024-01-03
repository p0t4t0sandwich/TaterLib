package dev.neuralnexus.taterlib.fabric.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerDeathEvent;
import dev.neuralnexus.taterlib.fabric.event.entity.FabricEntityDeathEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.vanilla.player.VanillaPlayer;

import net.minecraft.world.damagesource.DamageSource;

public class FabricPlayerDeathEvent extends FabricEntityDeathEvent implements PlayerDeathEvent {
    private final net.minecraft.world.entity.player.Player player;
    private final DamageSource source;

    public FabricPlayerDeathEvent(
            net.minecraft.world.entity.player.Player player, DamageSource source) {
        super(player, source);
        this.player = player;
        this.source = source;
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer() {
        return new VanillaPlayer(player);
    }

    /** {@inheritDoc} */
    @Override
    public String getDeathMessage() {
        return source.getLocalizedDeathMessage(player).getString();
    }

    /** {@inheritDoc} */
    @Override
    public void setDeathMessage(String deathMessage) {}

    /** {@inheritDoc} */
    @Override
    public boolean hasKeepInventory() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void setKeepInventory(boolean keepInventory) {}
}
