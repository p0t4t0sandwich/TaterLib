package dev.neuralnexus.taterlib.v1_20.bukkit.adapters;

import com.mojang.brigadier.CommandDispatcher;

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
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.advancement.CraftAdvancement;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/** Adapts Bukkit classes to NMS classes. */
public class BukkitAdapter {
    private static final BukkitAdapter INSTANCE = new BukkitAdapter();

    public static BukkitAdapter get() {
        return INSTANCE;
    }

    /**
     * Returns the Minecraft server.
     *
     * @return The Minecraft server.
     */
    public MinecraftServer server() {
        return ((CraftServer) Bukkit.getServer()).getServer();
    }

    /**
     * Returns a CommandDispatcher.
     *
     * @return The CommandDispatcher.
     */
    public CommandDispatcher<CommandSourceStack> commandDispatcher() {
        // TODO: Abstract to common
        // ((CraftServer)
        // Bukkit.getServer()).getServer().resources.managers().getCommands().getDispatcher();
        return server().resources.managers().getCommands().getDispatcher();
    }

    /**
     * Returns Commands.CommandSelection
     *
     * @return The Commands.CommandSelection
     */
    public Commands.CommandSelection commandSelection() {
        // TODO: Abstract to common
        return Commands.CommandSelection.DEDICATED;
    }

    /**
     * Returns a Vanilla block position from a Bukkit block.
     *
     * @param block The Bukkit block.
     * @return The Vanilla block.
     */
    public BlockPos blockPos(Block block) {
        return ((CraftBlock) block).getPosition();
    }

    /**
     * Returns a Vanilla block state from a Bukkit block.
     *
     * @param block The Bukkit block.
     * @return The Vanilla block state.
     */
    public BlockState blockState(Block block) {
        return ((CraftBlock) block).getNMS();
    }

    /**
     * Returns a Vanilla player from a Bukkit player.
     *
     * @param player The Bukkit player.
     * @return The Vanilla player.
     */
    public ServerPlayer player(Player player) {
        return ((CraftPlayer) player).getHandle();
    }

    /**
     * Returns a Vanilla level from a Bukkit world.
     *
     * @param world The Bukkit world.
     * @return The Vanilla level.
     */
    public Level level(World world) {
        return ((CraftWorld) world).getHandle();
    }

    /**
     * Returns a Vanilla entity from a Bukkit entity.
     *
     * @param entity The Bukkit entity.
     * @return The Vanilla entity.
     */
    public Entity entity(org.bukkit.entity.Entity entity) {
        return ((CraftEntity) entity).getHandle();
    }

    /**
     * Returns a Vanilla damage source from a Bukkit player's last damage source.
     *
     * @param entity The Bukkit entity.
     * @return The Vanilla damage source.
     */
    public DamageSource lastDamageSource(org.bukkit.entity.Entity entity) {
        Entity nmsEntity = ((CraftEntity) entity).getHandle();
        if (nmsEntity instanceof LivingEntity) {
            return ((LivingEntity) nmsEntity).getLastDamageSource();
        }
        return null;
    }

    /**
     * Returns a Vanilla advancement object from a Bukkit advancement.
     *
     * @param advancement The Bukkit advancement.
     * @return The Vanilla advancement object.
     */
    public Advancement advancement(org.bukkit.advancement.Advancement advancement) {
        return ((CraftAdvancement) advancement).getHandle();
    }
}
