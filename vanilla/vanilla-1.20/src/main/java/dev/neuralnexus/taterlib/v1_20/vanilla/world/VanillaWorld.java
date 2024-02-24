package dev.neuralnexus.taterlib.v1_20.vanilla.world;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_20.vanilla.entity.VanillaEntity;
import dev.neuralnexus.taterlib.v1_20.vanilla.player.VanillaPlayer;
import dev.neuralnexus.taterlib.world.Location;
import dev.neuralnexus.taterlib.world.World;

import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Vanilla implementation of {@link World}. */
public class VanillaWorld implements World {
    private final Level level;

    public VanillaWorld(Level level) {
        this.level = level;
    }

    /**
     * Gets the level.
     *
     * @return The level.
     */
    public Level world() {
        return level;
    }

    /** {@inheritDoc} */
    @Override
    public List<Player> players() {
        return level.players().stream().map(VanillaPlayer::new).collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public String dimension() {
        return level.dimension().location().toString();
    }

    /** {@inheritDoc} */
    @Override
    public List<Entity> entities(Entity entity, double radius, Predicate<Entity> predicate) {
        net.minecraft.world.entity.Entity mcEntity = ((VanillaEntity) entity).entity();
        return level
                .getEntities(
                        mcEntity,
                        mcEntity.getBoundingBox().inflate(radius),
                        e -> predicate.test(new VanillaEntity(e)))
                .stream()
                .map(VanillaEntity::new)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public List<Entity> entities(
            Entity entity, Location pos1, Location pos2, Predicate<Entity> predicate) {
        return level
                .getEntities(
                        ((VanillaEntity) entity).entity(),
                        new net.minecraft.world.phys.AABB(
                                pos1.x(), pos1.y(), pos1.z(), pos2.x(), pos2.y(), pos2.z()),
                        e -> predicate.test(new VanillaEntity(e)))
                .stream()
                .map(VanillaEntity::new)
                .collect(Collectors.toList());
    }
}
