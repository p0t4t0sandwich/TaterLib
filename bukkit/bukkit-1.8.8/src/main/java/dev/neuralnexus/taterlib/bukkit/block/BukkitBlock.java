package dev.neuralnexus.taterlib.bukkit.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.utils.Position;

/** Bukkit implementation of {@link Block}. */
public class BukkitBlock implements Block {
    private final org.bukkit.block.Block block;

    public BukkitBlock(org.bukkit.block.Block block) {
        this.block = block;
    }

    /** {@inheritDoc} */
    @Override
    public String getType() {
        String blockType = block.getType().toString();
        return "minecraft:" + blockType.toLowerCase();
    }

    /** {@inheritDoc} */
    @Override
    public Position getBlockPos() {
        return new Position(block.getX(), block.getY(), block.getZ());
    }
}
