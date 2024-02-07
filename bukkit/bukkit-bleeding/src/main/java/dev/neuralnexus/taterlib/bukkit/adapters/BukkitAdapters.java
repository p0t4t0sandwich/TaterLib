package dev.neuralnexus.taterlib.bukkit.adapters;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.lang.reflect.InvocationTargetException;

/** Adapts Bukkit classes to NMS classes. */
public class BukkitAdapters {
    private static final String CRAFTBUKKIT_PACKAGE =
            Bukkit.getServer().getClass().getPackage().getName();

    /**
     * Returns the CraftBukkit class name for the given class.
     *
     * @param clazz The class name.
     * @return The CraftBukkit class name.
     */
    public static String cbClass(String clazz) {
        return CRAFTBUKKIT_PACKAGE + "." + clazz;
    }

    /**
     * Reflects and casts an object.
     *
     * @param clazz The CraftBukkit class name.
     * @param object The object to cast.
     * @return The cast object.
     */
    public static Object reflectAndCast(String clazz, Object object) {
        try {
            return Class.forName(cbClass(clazz)).cast(object);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reflect and get the handle of an object.
     *
     * @param clazz The CraftBukkit class name.
     * @param object The object to cast.
     * @return The handle of the object.
     */
    public static Object reflectAndGetHandle(String clazz, Object object) {
        try {
            return Class.forName(cbClass(clazz)).getDeclaredMethod("getHandle").invoke(object);
        } catch (ClassNotFoundException
                | NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the Minecraft server.
     *
     * @return The Minecraft server.
     */
    public static MinecraftServer getServer() {
        // ((CraftServer) Bukkit.getServer()).getServer();
        Object craftServer = reflectAndCast("CraftServer", Bukkit.getServer());
        try {
            return (MinecraftServer)
                    craftServer.getClass().getDeclaredMethod("getServer").invoke(craftServer);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a CommandDispatcher.
     *
     * @return The CommandDispatcher.
     */
    public static CommandDispatcher<CommandSourceStack> getCommandDispatcher() {
        // ((CraftServer)
        // Bukkit.getServer()).getServer().resources.managers().getCommands().getDispatcher();
        return getServer().resources.managers().getCommands().getDispatcher();
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
        // ((CraftBlock) block).getPosition();
        Object craftBlock = reflectAndCast("block.CraftBlock", block);
        try {
            return (BlockPos)
                    craftBlock.getClass().getDeclaredMethod("getPosition").invoke(craftBlock);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a Vanilla block state from a Bukkit block.
     *
     * @param block The Bukkit block.
     * @return The Vanilla block state.
     */
    public static BlockState getBlockState(Block block) {
        // ((CraftBlock) block).getNMS();
        Object craftBlock = reflectAndCast("block.CraftBlock", block);
        try {
            return (BlockState)
                    craftBlock.getClass().getDeclaredMethod("getNMS").invoke(craftBlock);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a Vanilla player from a Bukkit player.
     *
     * @param player The Bukkit player.
     * @return The Vanilla player.
     */
    public static ServerPlayer getPlayer(org.bukkit.entity.Player player) {
        // ((CraftPlayer) player).getHandle();
        return (ServerPlayer) reflectAndGetHandle("entity.CraftPlayer", player);
    }

    /**
     * Returns a Vanilla level from a Bukkit world.
     *
     * @param world The Bukkit world.
     * @return The Vanilla level.
     */
    public static Level getLevel(World world) {
        // ((CraftWorld) world).getHandle();
        return (Level) reflectAndGetHandle("CraftWorld", world);
    }

    /**
     * Returns a Vanilla entity from a Bukkit entity.
     *
     * @param entity The Bukkit entity.
     * @return The Vanilla entity.
     */
    public static Entity getEntity(org.bukkit.entity.Entity entity) {
        // ((CraftEntity) entity).getHandle();
        return (Entity) reflectAndGetHandle("entity.CraftEntity", entity);
    }

    /**
     * Returns a Vanilla damage source from a Bukkit player's last damage source.
     *
     * @param entity The Bukkit entity.
     * @return The Vanilla damage source.
     */
    public static DamageSource getLastDamageSource(org.bukkit.entity.Entity entity) {
        // ((LivingEntity) ((CraftEntity) entity).getHandle()).getLastDamageSource();
        return ((LivingEntity) getEntity(entity)).getLastDamageSource();
    }

    /**
     * Returns a Vanilla advancement from a Bukkit advancement.
     *
     * @param advancement The Bukkit advancement.
     * @return The Vanilla advancement.
     */
    public static AdvancementHolder getAdvancement(org.bukkit.advancement.Advancement advancement) {
        // ((CraftAdvancement) advancement).getHandle();
        return (AdvancementHolder) reflectAndGetHandle("advancement.CraftAdvancement", advancement);
    }
}
