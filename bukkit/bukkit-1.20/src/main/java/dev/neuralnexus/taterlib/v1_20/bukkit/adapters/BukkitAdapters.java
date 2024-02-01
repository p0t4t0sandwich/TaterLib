package dev.neuralnexus.taterlib.v1_20.bukkit.adapters;

import com.mojang.brigadier.CommandDispatcher;

// import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.Advancement;
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
// import org.bukkit.advancement.Advancement;
import org.bukkit.block.Block;
// import org.bukkit.craftbukkit.v1_20_R3.CraftServer;
// import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
// import org.bukkit.craftbukkit.v1_20_R3.advancement.CraftAdvancement;
// import org.bukkit.craftbukkit.v1_20_R3.block.CraftBlock;
// import org.bukkit.craftbukkit.v1_20_R3.entity.CraftEntity;
// import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.advancement.CraftAdvancement;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;

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
     * Returns the CraftBukkit class name for the given class.
     *
     * @param className The class name.
     * @param object The object to cast.
     * @return The cast object.
     */
    public static Object reflectAndCast(String className, Object object) {
        try {
            return Class.forName(className).cast(object);
        } catch (ClassNotFoundException e) {
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
        Object craftServer = reflectAndCast(cbClass("CraftServer"), Bukkit.getServer());
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
        return (((CraftServer) Bukkit.getServer()).getServer())
                .resources
                .managers()
                .getCommands()
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
    public static Advancement getAdvancement(org.bukkit.advancement.Advancement advancement) {
        return ((CraftAdvancement) advancement).getHandle();
    }
}
