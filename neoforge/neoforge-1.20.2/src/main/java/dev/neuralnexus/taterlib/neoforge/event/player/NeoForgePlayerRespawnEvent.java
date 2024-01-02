package dev.neuralnexus.taterlib.neoforge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.vanilla.util.VanillaLocation;

import net.neoforged.neoforge.event.entity.player.PlayerEvent;

/** NeoForge implementation of {@link PlayerRespawnEvent}. */
public class NeoForgePlayerRespawnEvent extends NeoForgePlayerEvent implements PlayerRespawnEvent {
    private final PlayerEvent.PlayerRespawnEvent event;

    public NeoForgePlayerRespawnEvent(PlayerEvent.PlayerRespawnEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Location getRespawnLocation() {
        return new VanillaLocation(event.getEntity());
    }

    /** {@inheritDoc} */
    @Override
    public boolean isBedSpawn() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isAnchorSpawn() {
        return false;
    }
}
