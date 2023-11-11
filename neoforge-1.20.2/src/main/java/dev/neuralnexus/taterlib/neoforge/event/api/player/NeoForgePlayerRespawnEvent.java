package dev.neuralnexus.taterlib.neoforge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.neoforge.player.NeoForgePlayer;
import dev.neuralnexus.taterlib.neoforge.util.NeoForgeLocation;
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
     * {@inheritDoc}
     */
    @Override
    public Location getRespawnLocation() {
        return new NeoForgeLocation(((NeoForgePlayer) getPlayer()).getPlayer());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBedSpawn() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAnchorSpawn() {
        return false;
    }
}
