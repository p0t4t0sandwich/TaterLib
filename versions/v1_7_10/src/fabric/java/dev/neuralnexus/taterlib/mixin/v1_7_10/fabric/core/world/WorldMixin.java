/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_7_10.fabric.core.world;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.world.WorldBridge;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.WrappedEntity;

import net.minecraft.entity.EntityFilter;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ReqMappings(Mappings.LEGACY_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V7_2, max = MinecraftVersion.V7_10)
@Mixin(World.class)
public abstract class WorldMixin implements WorldBridge {
    @Shadow @Final public Dimension dimension;

    @Shadow
    public abstract List<net.minecraft.entity.Entity> shadow$getEntities(
            net.minecraft.entity.Entity exclude, Box bounds, EntityFilter filter);

    @Override
    public ResourceKey bridge$dimension() {
        return ResourceKey.of(this.dimension.getName().replace(" ", "_").toLowerCase());
    }

    @Override
    public List<Entity> bridge$entities(Entity entity, double radius, Predicate<Entity> predicate) {
        net.minecraft.entity.Entity mcEntity = ((WrappedEntity) entity).unwrap();
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
            Entity entity, Location pos1, Location pos2, Predicate<Entity> predicate) {
        return this.shadow$getEntities(
                        ((WrappedEntity) entity).unwrap(),
                        Box.of(pos1.x(), pos1.y(), pos1.z(), pos2.x(), pos2.y(), pos2.z()),
                        e -> predicate.test(new WrappedEntity(e)))
                .stream()
                .map(WrappedEntity::new)
                .collect(Collectors.toList());
    }
}
