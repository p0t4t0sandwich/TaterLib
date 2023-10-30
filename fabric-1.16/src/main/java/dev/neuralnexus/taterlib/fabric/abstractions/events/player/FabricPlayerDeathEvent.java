package dev.neuralnexus.taterlib.fabric.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerDeathEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.fabric.abstractions.events.entity.FabricEntityDeathEvent;
import dev.neuralnexus.taterlib.fabric.abstractions.player.FabricPlayer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

public class FabricPlayerDeathEvent extends FabricEntityDeathEvent implements AbstractPlayerDeathEvent {
    private final PlayerEntity player;
    private final DamageSource source;

    public FabricPlayerDeathEvent(PlayerEntity player, DamageSource source) {
        super(player, source);
        this.player = player;
        this.source = source;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new FabricPlayer(player);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDeathMessage() {
        return source.getDeathMessage(player).getString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDeathMessage(String deathMessage) {}

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasKeepInventory() {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setKeepInventory(boolean keepInventory) {}
}
