package dev.neuralnexus.taterlib.neoforge.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import dev.neuralnexus.taterlib.common.commands.TaterLibCommand;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.neoforge.NeoForgeTaterLibPlugin;
import dev.neuralnexus.taterlib.neoforge.abstractions.player.NeoForgePlayer;
import net.minecraft.Util;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.atomic.AtomicInteger;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

/**
 * NeoForge implementation of the TaterLib command.
 */
@Mod.EventBusSubscriber(modid = NeoForgeTaterLibPlugin.MOD_ID)
public final class NeoForgeTaterLibCommand {
    /**
     * Registers the TaterLib command.
     * @param event The register commands event.
     */
    @SubscribeEvent
    public void registerCommand(RegisterCommandsEvent event) {
        int permissionLevel;
        String commandName = TaterLibCommand.getCommandName();
        if (event.getCommandSelection() == Commands.CommandSelection.DEDICATED) {
            // Check if LuckPerms is hooked
            permissionLevel = LuckPermsHook.isHooked() ? 0 : 4;
        } else {
            permissionLevel = 0;
        }

        // Register command
        event.getDispatcher().register(literal(commandName)
            .requires(source -> source.hasPermission(permissionLevel))
            .then(argument("command", StringArgumentType.greedyString())
            .executes(context -> {
                try {
                    String[] args = context.getArgument("command", String.class).split(" ");

                    // Check if sender is a player
                    boolean isPlayer = context.getSource().getEntity() instanceof Player;
                    NeoForgePlayer player = isPlayer ? new NeoForgePlayer((Player) context.getSource().getEntity()) : null;

                    AtomicInteger success = new AtomicInteger(1);
                    Util.backgroundExecutor().submit(() -> {
                        try {
                            // Execute command
                            TaterLibCommand.executeCommand(player, isPlayer, args);
                        } catch (Exception e) {
                            System.out.println(e);
                            e.printStackTrace();
                            success.set(0);
                        }
                    });
                    return success.get();
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            })
        ));
    }
}
