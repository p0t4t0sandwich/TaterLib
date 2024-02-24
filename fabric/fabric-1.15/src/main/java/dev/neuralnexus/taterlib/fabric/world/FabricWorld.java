package dev.neuralnexus.taterlib.fabric.world;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.fabric.entity.FabricEntity;
import dev.neuralnexus.taterlib.fabric.player.FabricPlayer;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.world.Location;
import dev.neuralnexus.taterlib.world.World;

import net.minecraft.util.math.Box;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Fabric implementation of {@link World}. */
public class FabricWorld implements World {
    private final net.minecraft.world.World level;

    public FabricWorld(net.minecraft.world.World level) {
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
        return level.getPlayers().stream().map(FabricPlayer::new).collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public String dimension() {
        return level.getDimension().getType().toString();
    }

    /** {@inheritDoc} */
    @Override
    public List<Entity> entities(Entity entity, double radius, Predicate<Entity> predicate) {
        net.minecraft.entity.Entity mcEntity = ((FabricEntity) entity).entity();
        return level
                .getEntities(
                        mcEntity,
                        mcEntity.getBoundingBox().expand(radius),
                        e -> predicate.test(new FabricEntity(e)))
                .stream()
                .map(FabricEntity::new)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public List<Entity> entities(
            Entity entity, Location pos1, Location pos2, Predicate<Entity> predicate) {
        return level
                .getEntities(
                        ((FabricEntity) entity).entity(),
                        new Box(pos1.x(), pos1.y(), pos1.z(), pos2.x(), pos2.y(), pos2.z()),
                        e -> predicate.test(new FabricEntity(e)))
                .stream()
                .map(FabricEntity::new)
                .collect(Collectors.toList());
    }
}
