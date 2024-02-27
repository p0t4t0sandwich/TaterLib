package dev.neuralnexus.taterlib.v1_20.bukkit.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.world.BlockPos;

/** Bukkit implementation of {@link Block}. */
public class BukkitBlock implements Block {
    private final org.bukkit.block.Block block;

    public BukkitBlock(org.bukkit.block.Block block) {
        this.block = block;
    }

    /** {@inheritDoc} */
    @Override
    public String type() {
        String blockType = block.getType().toString();
        return "minecraft:" + blockType.toLowerCase();
    }

    /** {@inheritDoc} */
    @Override
    public BlockPos blockPos() {
        return new BlockPos(block.getX(), block.getY(), block.getZ());
    }
}
