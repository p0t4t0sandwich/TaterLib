package dev.neuralnexus.taterlib.sponge.block;

import dev.neuralnexus.taterlib.common.block.Block;
import dev.neuralnexus.taterlib.common.utils.Position;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

/** Sponge implementation of {@link Block}. */
public class SpongeBlock implements Block {
    private final Location<World> pos;
    private final BlockState block;

    public SpongeBlock(Location<World> location) {
        this.pos = location;
        this.block = location.getBlock();
    }

    /** {@inheritDoc} */
    @Override
    public String getType() {
        return block.getType().toString().split("entity\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public Position getBlockPos() {
        return new Position(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ());
    }
}
