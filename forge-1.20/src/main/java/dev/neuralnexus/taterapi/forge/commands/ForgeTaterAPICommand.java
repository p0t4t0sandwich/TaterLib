package dev.neuralnexus.taterapi.forge.commands;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.commands.TaterAPICommand;
import com.mojang.brigadier.arguments.StringArgumentType;
import dev.neuralnexus.taterapi.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterapi.forge.player.ForgeTaterPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static dev.neuralnexus.taterapi.common.Utils.ansiiParser;
import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;


public final class ForgeTaterAPICommand implements TaterAPICommand {
    @SubscribeEvent
    public void registerCommand(RegisterCommandsEvent event) {
        // Check if LuckPerms is hooked
        int permissionLevel = LuckPermsHook.isHooked() ? 0 : 4;

        // Register command
        event.getDispatcher().register(literal(getCommandName())
            .requires(source -> source.hasPermission(permissionLevel))
            .then(argument("subcommand", StringArgumentType.greedyString())
                .executes(context -> {
                    runTaskAsync(() -> {
                        try {
                            String[] args = new String[] {context.getArgument("subcommand", String.class)};

                            // Check if sender is a player
                            Entity entity = context.getSource().getEntity();
                            if (entity instanceof Player) {
                                // Execute command as player
                                ForgeTaterPlayer player = new ForgeTaterPlayer((Player) entity);
                                player.sendMessage(executeCommand(player, args));
                            } else {
                                // Execute command as console
                                TaterAPI.useLogger(ansiiParser(executeCommand(args)));
                            }
                        } catch (Exception e) {
                            System.err.println(e);
                            e.printStackTrace();
                        }
                    });
                    return 1;
                })
            ));
    }
}
