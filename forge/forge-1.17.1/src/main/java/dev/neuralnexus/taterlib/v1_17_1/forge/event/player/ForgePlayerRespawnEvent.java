package dev.neuralnexus.taterlib.v1_17_1.forge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterlib.v1_17_1.forge.player.ForgePlayer;
import dev.neuralnexus.taterlib.v1_17_1.forge.world.ForgeLocation;
import dev.neuralnexus.taterlib.world.Location;

import net.minecraftforge.event.entity.player.PlayerEvent;

/** Forge implementation of {@link PlayerRespawnEvent}. */
public class ForgePlayerRespawnEvent extends ForgePlayerEvent implements PlayerRespawnEvent {
    private final PlayerEvent.PlayerRespawnEvent event;

    public ForgePlayerRespawnEvent(PlayerEvent.PlayerRespawnEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Location respawnLocation() {
        return new ForgeLocation(((ForgePlayer) player()).player());
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