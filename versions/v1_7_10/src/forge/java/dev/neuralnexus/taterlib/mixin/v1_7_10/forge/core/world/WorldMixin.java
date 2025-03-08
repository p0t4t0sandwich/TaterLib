/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_7_10.forge.core.world;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.world.WorldBridge;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.WrappedEntity;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.player.WrappedPlayer;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ReqMappings(Mappings.LEGACY_SEARGE)
@ReqMCVersion(min = MinecraftVersion.V7_2, max = MinecraftVersion.V7_10)
@Mixin(World.class)
public abstract class WorldMixin implements WorldBridge {
    @Shadow public List<EntityPlayer> playerEntities;

    @Shadow
    public abstract List<net.minecraft.entity.Entity> getEntitiesWithinAABBExcludingEntity(
            net.minecraft.entity.Entity entity, AxisAlignedBB box, IEntitySelector predicate);

    @Override
    public List<Player> bridge$players() {
        return this.playerEntities.stream().map(WrappedPlayer::new).collect(Collectors.toList());
    }

    @Override
    public List<Entity> bridge$entities(Entity entity, double radius, Predicate<Entity> predicate) {
        net.minecraft.entity.Entity mcEntity = ((WrappedEntity) entity).unwrap();
        return this.getEntitiesWithinAABBExcludingEntity(
                        mcEntity,
                        mcEntity.getBoundingBox().expand(radius, radius, radius),
                        e -> predicate.test(new WrappedEntity(e)))
                .stream()
                .map(WrappedEntity::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Entity> bridge$entities(
            Entity entity, Location pos1, Location pos2, Predicate<Entity> predicate) {
        return this.getEntitiesWithinAABBExcludingEntity(
                        ((WrappedEntity) entity).unwrap(),
                        AxisAlignedBB.getBoundingBox(
                                pos1.x(), pos1.y(), pos1.z(), pos2.x(), pos2.y(), pos2.z()),
                        e -> predicate.test(new WrappedEntity(e)))
                .stream()
                .map(WrappedEntity::new)
                .collect(Collectors.toList());
    }
}
