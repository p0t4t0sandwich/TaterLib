package dev.neuralnexus.taterlib.v1_17.sponge.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.world.BlockPos;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.registry.Registry;
import org.spongepowered.api.registry.RegistryTypes;
import org.spongepowered.api.world.server.ServerLocation;

/** Sponge implementation of {@link Block}. */
public class SpongeBlock implements Block {
    private final ServerLocation pos;
    private final BlockState block;

    public SpongeBlock(ServerLocation location) {
        this.pos = location;
        this.block = location.block();
    }

    /** {@inheritDoc} */
    @Override
    public String type() {
        Registry<BlockType> registry = RegistryTypes.BLOCK_TYPE.get();
        return registry.findValueKey(block.type()).get().asString();
    }

    /** {@inheritDoc} */
    @Override
    public BlockPos blockPos() {
        return new BlockPos(pos.blockX(), pos.blockY(), pos.blockZ());
    }
}
