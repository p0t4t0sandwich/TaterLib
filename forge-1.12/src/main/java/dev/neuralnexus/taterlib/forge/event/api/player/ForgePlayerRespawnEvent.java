package dev.neuralnexus.taterlib.forge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.forge.entity.ForgeEntity;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * Forge implementation of {@link PlayerRespawnEvent}.
 */
public class ForgePlayerRespawnEvent extends ForgePlayerEvent implements PlayerRespawnEvent {
    private final PlayerEvent.PlayerRespawnEvent event;

    public ForgePlayerRespawnEvent(PlayerEvent.PlayerRespawnEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Location getRespawnLocation() {
        return new Location(getPlayer().getPosition(), new ForgeEntity(event.player).getDimension());
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isBedSpawn() {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isAnchorSpawn() {
        return false;
    }
}