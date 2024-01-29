package dev.neuralnexus.taterlib.sponge.adapters;

import com.mojang.brigadier.CommandDispatcher;

import dev.neuralnexus.taterlib.vanilla.server.VanillaServer;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.api.Sponge;
import org.spongepowered.common.command.manager.SpongeCommandManager;

/** Adapts Sponge classes to NMS classes. */
public class SpongeAdapters {
    /**
     * Returns a Vanilla player from a Sponge player.
     *
     * @param player The Bukkit player.
     * @return The Vanilla player.
     */
    public static Player getPlayer(org.spongepowered.api.entity.living.player.Player player) {
        return (Player) player;
    }

    /**
     * Returns a CommandDispatcher.
     *
     * @return The CommandDispatcher.
     */
    public static CommandDispatcher<CommandSourceStack> getCommandDispatcher() {
        return VanillaServer.getServer().isDedicatedServer()
                ? (VanillaServer.getServer()).resources.managers().getCommands().getDispatcher()
                : ((SpongeCommandManager) Sponge.server().commandManager()).getDispatcher();
    }

    /**
     * Returns Commands.CommandSelection
     *
     * @return The Commands.CommandSelection
     */
    public static Commands.CommandSelection getCommandSelection() {
        return VanillaServer.getServer().isDedicatedServer()
                ? Commands.CommandSelection.DEDICATED
                : Commands.CommandSelection.INTEGRATED;
    }
}
