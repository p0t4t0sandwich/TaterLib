/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_1.fabric.core.world.level.block;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.level.block.BlockBridge;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import org.spongepowered.asm.mixin.Mixin;

@AConstraint(
        mappings = Mappings.YARN_INTERMEDIARY,
        version = @Versions(min = MinecraftVersion.V19_3))
@Mixin(Block.class)
public class BlockMixin implements BlockBridge {
    @Override
    public ResourceLocation bridge$type() {
        return BuiltInRegistries.BLOCK.getKey((Block) (Object) this);
    }
}
