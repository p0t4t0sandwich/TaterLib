package dev.neuralnexus.taterapi.forge.commands;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.commands.TaterAPICommand;
import com.mojang.brigadier.arguments.StringArgumentType;
import dev.neuralnexus.taterapi.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterapi.forge.player.ForgeTaterPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

import java.util.concurrent.atomic.AtomicInteger;

import static dev.neuralnexus.taterapi.common.Utils.ansiiParser;
import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;
import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;


public final class ForgeTaterAPICommand implements TaterAPICommand {
    @SubscribeEvent
    public void registerCommand(FMLServerStartingEvent event) {
        // Check if LuckPerms is hooked
        int permissionLevel = LuckPermsHook.isHooked() ? 0 : 4;

        // Register command
        event.getCommandDispatcher().register(literal(getCommandName())
            .requires(source -> source.hasPermissionLevel(permissionLevel))
            .then(argument("subcommand", StringArgumentType.greedyString())
                .executes(context -> {
                    AtomicInteger result = new AtomicInteger(1);
                    runTaskAsync(() -> {
                        try {
                            String[] args = new String[] {context.getArgument("subcommand", String.class)};

                            // Check if sender is a player
                            Entity entity = context.getSource().getEntity();
                            if (entity instanceof PlayerEntity) {
                                // Execute command as player
                                ForgeTaterPlayer player = new ForgeTaterPlayer((PlayerEntity) entity);
                                player.sendMessage(executeCommand(player, args));
                            } else {
                                // Execute command as console
                                TaterAPI.useLogger(ansiiParser(executeCommand(args)));
                            }
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
