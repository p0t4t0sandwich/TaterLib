/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_7_10.fabric.core.world;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.world.WorldBridge;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.WrappedEntity;

import net.minecraft.entity.EntityFilter;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeSource;
import net.minecraft.world.dimension.Dimension;

import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AConstraint(
        mappings = Mappings.LEGACY_INTERMEDIARY,
        version = @Versions(min = MinecraftVersion.V7_2, max = MinecraftVersion.V7_10))
@Mixin(World.class)
public abstract class WorldMixin implements WorldBridge {
    // spotless:off
    @Shadow @Final public Dimension dimension;
    @Shadow public abstract Biome shadow$getBiome(int x, int z);
    @Shadow public abstract List<net.minecraft.entity.Entity> shadow$getEntities(
            net.minecraft.entity.Entity exclude, Box bounds, EntityFilter filter);
    // spotless:on

    @Override
    public @NonNull Identifier bridge$dimension() {
        return Identifier.of(this.dimension.getName().replace(" ", "_").toLowerCase());
    }

    @Override
    public @NonNull Identifier bridge$biome(final @NonNull BlockPos pos) {
        return Identifier.of(this.shadow$getBiome(pos.blockX(), pos.blockY()).name);
    }

    @Override
    public List<Entity> bridge$entities(
            final @NonNull Entity entity,
            final double radius,
            final @NonNull Predicate<Entity> predicate) {
        final net.minecraft.entity.Entity mcEntity = ((WrappedEntity) entity).unwrap();
        return this.shadow$getEntities(
                        mcEntity,
                        mcEntity.getCollisionShape().expand(radius, radius, radius),
                        e -> predicate.test(new WrappedEntity(e)))
                .stream()
                .map(WrappedEntity::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Entity> bridge$entities(
            final @NonNull Entity entity,
            final @NonNull Location pos1,
            final @NonNull Location pos2,
            final @NonNull Predicate<Entity> predicate) {
        return this.shadow$getEntities(
                        ((WrappedEntity) entity).unwrap(),
                        Box.of(pos1.x(), pos1.y(), pos1.z(), pos2.x(), pos2.y(), pos2.z()),
                        e -> predicate.test(new WrappedEntity(e)))
                .stream()
                .map(WrappedEntity::new)
                .collect(Collectors.toList());
    }
}
