/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8_8.bukkit.block;

import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.world.BlockPos;

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
