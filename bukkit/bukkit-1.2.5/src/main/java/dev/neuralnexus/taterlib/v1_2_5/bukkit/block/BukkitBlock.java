/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_2_5.bukkit.block;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.world.BlockPos;

/** Bukkit implementation of {@link Block}. */
public class BukkitBlock implements Block, Wrapped<org.bukkit.block.Block> {
    private final org.bukkit.block.Block block;

    public BukkitBlock(org.bukkit.block.Block block) {
        this.block = block;
    }

    @Override
    public org.bukkit.block.Block unwrap() {
        return this.block;
    }

    @Override
    public Identifier type() {
        return Identifier.of("minecraft", this.block.getType().toString().toLowerCase());
    }

    @Override
    public BlockPos blockPos() {
        return new BlockPos(this.block.getX(), this.block.getY(), this.block.getZ());
    }
}
