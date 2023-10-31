package dev.neuralnexus.taterlib.neoforge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.neoforge.entity.NeoForgeEntity;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

/**
 * NeoForge implementation of {@link PlayerRespawnEvent}.
 */
public class NeoForgePlayerRespawnEvent extends NeoForgePlayerEvent implements PlayerRespawnEvent {
    private final PlayerEvent.PlayerRespawnEvent event;

    public NeoForgePlayerRespawnEvent(PlayerEvent.PlayerRespawnEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Location getRespawnLocation() {
        return new Location(getPlayer().getPosition(), new NeoForgeEntity(event.getEntity()).getDimension());
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
