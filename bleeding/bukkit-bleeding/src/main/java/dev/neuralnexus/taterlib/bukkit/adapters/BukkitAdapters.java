package dev.neuralnexus.taterlib.bukkit.adapters;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.advancement.Advancement;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_20_R3.CraftServer;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R3.advancement.CraftAdvancement;
import org.bukkit.craftbukkit.v1_20_R3.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.event.entity.EntityDamageEvent;

/** Adapts Bukkit classes to abstracted library classes. */
public class BukkitAdapters {
    /**
     * Abstracts the server.
     *
     * @return The abstracted server.
     */
    public static MinecraftServer getServer() {
        return ReflectionAdapters.getServer();
    }

    /**
     * Returns a CommandDispatcher.
     *
     * @return The CommandDispatcher.
     */
    public static CommandDispatcher<CommandSourceStack> getCommandDispatcher() {
        return (((CraftServer) Bukkit.getServer()).getServer())
                .resources
                .managers()
                .commands
                .getDispatcher();
    }

    /**
     * Returns Commands.CommandSelection
     *
     * @return The Commands.CommandSelection
     */
    public static Commands.CommandSelection getCommandSelection() {
        return Commands.CommandSelection.DEDICATED;
    }

    /**
     * Returns a Vanilla block position from a Bukkit block.
     *
     * @param block The Bukkit block.
     * @return The Vanilla block.
     */
    public static BlockPos getBlockPos(Block block) {
        return ((CraftBlock) block).getPosition();
    }

    /**
     * Returns a Vanilla block state from a Bukkit block.
     *
     * @param block The Bukkit block.
     * @return The Vanilla block state.
     */
    public static BlockState getBlockState(Block block) {
        return ((CraftBlock) block).getNMS();
    }

    /**
     * Returns a Vanilla player from a Bukkit player.
     *
     * @param player The Bukkit player.
     * @return The Vanilla player.
     */
    public static ServerPlayer getPlayer(org.bukkit.entity.Player player) {
        return ((CraftPlayer) player).getHandle();
    }

    /**
     * Returns a Vanilla level from a Bukkit world.
     *
     * @param world The Bukkit world.
     * @return The Vanilla level.
     */
    public static Level getLevel(World world) {
        return ((CraftWorld) world).getHandle();
    }

    /**
     * Returns a Vanilla entity from a Bukkit entity.
     *
     * @param entity The Bukkit entity.
     * @return The Vanilla entity.
     */
    public static Entity getEntity(org.bukkit.entity.Entity entity) {
        return ((CraftEntity) entity).getHandle();
    }

    /**
     * Adapts a Bukkit DamageCause to a Vanilla DamageSource.
     *
     * @param damageCause The damage cause.
     * @param world The world.
     * @param entity The entity.
     * @param damager The damager.
     * @return The Vanilla DamageSource.
     */
    public static DamageSource getDamageSource(
            EntityDamageEvent.DamageCause damageCause,
            World world,
            org.bukkit.entity.Entity entity,
            Object damager) {
        DamageSources damageSources = getLevel(world).damageSources();
        LivingEntity source = (LivingEntity) getEntity(entity);
        if (damager == null) {
            switch (damageCause) {
                case KILL:
                    return damageSources.genericKill();
                case WORLD_BORDER:
                    return damageSources.outOfBorder();
                case CONTACT:
                    return damageSources.cactus();
                case SUFFOCATION:
                    return damageSources.inWall();
                case FALL:
                    return damageSources.fall();
                case FIRE:
                    return damageSources.inFire();
                case FIRE_TICK:
                    return damageSources.onFire();
                case MELTING:
                    return damageSources.melting;
                case LAVA:
                    return damageSources.lava();
                case DROWNING:
                    return damageSources.drown();
                case VOID:
                    return damageSources.fellOutOfWorld();
                case LIGHTNING:
                    return damageSources.lightningBolt();
                case SUICIDE:
                    // TODO: Check this
                    return damageSources.genericKill();
                case STARVATION:
                    return damageSources.starve();
                case POISON:
                    return damageSources.poison;
                case MAGIC:
                    return damageSources.magic();
                case WITHER:
                    return damageSources.wither();
                case DRAGON_BREATH:
                    return damageSources.dragonBreath();
                case FLY_INTO_WALL:
                    return damageSources.flyIntoWall();
                case HOT_FLOOR:
                    return damageSources.hotFloor();
                case CRAMMING:
                    return damageSources.cramming();
                case DRYOUT:
                    return damageSources.dryOut();
                case FREEZE:
                    return damageSources.freeze();
            }
        } else if (damager instanceof org.bukkit.entity.Entity) {
            LivingEntity attacker = (LivingEntity) getEntity((org.bukkit.entity.Entity) damager);
            switch (damageCause) {
                case ENTITY_ATTACK:
                case ENTITY_SWEEP_ATTACK:
                    return damageSources.mobAttack(attacker);
                case PROJECTILE:
                    // TODO: Find how to get the projectile
                    return damageSources.genericKill();
                case BLOCK_EXPLOSION:
                case ENTITY_EXPLOSION:
                    // TODO: Check this
                    return damageSources.explosion(source, attacker);
                case FALLING_BLOCK:
                case THORNS:
                    return damageSources.thorns(attacker);
                case SONIC_BOOM:
                    return damageSources.sonicBoom(attacker);
            }
        } else if (damager instanceof Block) {
            Block attacker = (Block) damager;
            switch (damageCause) {
                case BLOCK_EXPLOSION:
                    // TODO: Check this
                    return damageSources.explosion(null);
                case FALLING_BLOCK:
                    // TODO: Check this
                    if (attacker.getType().equals(org.bukkit.Material.ANVIL)) {
                        return damageSources.anvil(source);
                    }
                    return damageSources.fallingBlock(source);
                default:
                    return damageSources.generic();
            }
        }
        return damageSources.generic();
    }

    /**
     * Returns a Vanilla damage source from a Bukkit player's last damage source.
     *
     * @param entity The Bukkit entity.
     * @return The Vanilla damage source.
     */
    public static DamageSource getLastDamageSource(org.bukkit.entity.Entity entity) {
        return ((LivingEntity) ((CraftEntity) entity).getHandle()).getLastDamageSource();
    }

    /**
     * Returns a Vanilla advancement holder from a Bukkit advancement.
     *
     * @param advancement The Bukkit advancement.
     * @return The Vanilla advancement holder.
     */
    public static AdvancementHolder getAdvancement(Advancement advancement) {
        return ((CraftAdvancement) advancement).getHandle();
    }
}
