package dev.neuralnexus.taterapi.forge.commands;

import dev.neuralnexus.taterapi.common.commands.TaterAPICommand;
import com.mojang.brigadier.arguments.StringArgumentType;
import dev.neuralnexus.taterapi.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterapi.forge.player.ForgePlayer;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.concurrent.atomic.AtomicInteger;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;


public final class ForgeTaterAPICommand {
    @SubscribeEvent
    public void registerCommand(RegisterCommandsEvent event) {
        int permissionLevel;
        String commandName = TaterAPICommand.getCommandName();
        if (event.getCommandSelection() == Commands.CommandSelection.DEDICATED) {
            // Check if LuckPerms is hooked
            permissionLevel = LuckPermsHook.isHooked() ? 0 : 4;
        } else {
            permissionLevel = 0;
        }

        // Register command
        event.getDispatcher().register(literal(commandName)
            .requires(source -> source.hasPermission(permissionLevel))
            .then(argument("subcommand", StringArgumentType.greedyString())
                .executes(context -> {
                    AtomicInteger result = new AtomicInteger(1);
                    runTaskAsync(() -> {
                        try {
                            String[] args = context.getArgument("command", String.class).split(" ");

                            // Check if sender is a player
                            boolean isPlayer = context.getSource().getEntity() instanceof Player;
                            ForgePlayer player = isPlayer ? new ForgePlayer((Player) context.getSource().getEntity()) : null;

                            // Execute command
                            TaterAPICommand.executeCommand(player, isPlayer, args);
                        } catch (Exception e) {
                            result.set(0);
                            System.err.println(e);
                            e.printStackTrace();
                        }
                    });
                    return result.get();
                })
            ));
    }
}
