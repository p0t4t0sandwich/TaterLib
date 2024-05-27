package dev.neuralnexus.taterlib.v1_15_1.forge.world;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_15_1.forge.entity.ForgeEntity;
import dev.neuralnexus.taterlib.v1_15_1.forge.player.ForgePlayer;
import dev.neuralnexus.taterlib.world.Location;
import dev.neuralnexus.taterlib.world.World;

import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Forge implementation of {@link World}. */
public class ForgeWorld implements World {
    private final net.minecraft.world.World level;

    public ForgeWorld(net.minecraft.world.World level) {
        this.level = level;
    }

    /**
     * Gets the level.
     *
     * @return The level.
     */
    public net.minecraft.world.World world() {
        return level;
    }

    /** {@inheritDoc} */
    @Override
    public List<Player> players() {
        return level.players().stream().map(ForgePlayer::new).collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public String dimension() {
        return level.dimension.getType().getRegistryName().toString();
    }

    /** {@inheritDoc} */
    @Override
    public List<Entity> entities(Entity entity, double radius, Predicate<Entity> predicate) {
        net.minecraft.entity.Entity mcEntity = ((ForgeEntity) entity).entity();
        return level
                .getEntities(
                        mcEntity,
                        mcEntity.getBoundingBox().inflate(radius),
                        e -> predicate.test(new ForgeEntity(e)))
                .stream()
                .map(ForgeEntity::new)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public List<Entity> entities(
            Entity entity, Location pos1, Location pos2, Predicate<Entity> predicate) {
        return level
                .getEntities(
                        ((ForgeEntity) entity).entity(),
                        new AxisAlignedBB(
                                pos1.x(), pos1.y(), pos1.z(), pos2.x(), pos2.y(), pos2.z()),
                        e -> predicate.test(new ForgeEntity(e)))
                .stream()
                .map(ForgeEntity::new)
                .collect(Collectors.toList());
    }
}
