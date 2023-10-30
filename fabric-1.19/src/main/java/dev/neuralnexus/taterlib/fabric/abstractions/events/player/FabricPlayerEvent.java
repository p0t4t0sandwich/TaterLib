package dev.neuralnexus.taterlib.fabric.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.fabric.abstractions.player.FabricPlayer;
import net.minecraft.entity.player.PlayerEntity;

/**
 * Fabric implementation of {@link AbstractPlayerEvent}.
 */
public class FabricPlayerEvent implements AbstractPlayerEvent {
    private final PlayerEntity player;

    public FabricPlayerEvent(PlayerEntity player) {
        this.player = player;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new FabricPlayer(this.player);
    }
}