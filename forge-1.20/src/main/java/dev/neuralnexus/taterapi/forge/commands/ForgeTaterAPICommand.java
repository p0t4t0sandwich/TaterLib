package dev.neuralnexus.taterapi.forge.commands;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.commands.TaterAPICommand;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static dev.neuralnexus.taterapi.common.Utils.ansiiParser;
import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;


public final class ForgeTaterAPICommand implements TaterAPICommand {
    @SubscribeEvent
    public void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(literal(getCommandName())
            .requires(source -> source.hasPermission(0))
            .then(argument("subcommand", StringArgumentType.greedyString())
                .executes(context -> {
                    runTaskAsync(() -> {
                        try {
                            String[] args = new String[] {context.getArgument("subcommand", String.class)};

                            // Send message to player or console
                            Entity entity = context.getSource().getEntity();

                            String text = executeCommand(args);
                            if (entity instanceof ServerPlayer) {
                                ((ServerPlayer) entity).displayClientMessage(Component.empty().append(text), false);
                            } else {
                                TaterAPI.useLogger(ansiiParser(text));
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
