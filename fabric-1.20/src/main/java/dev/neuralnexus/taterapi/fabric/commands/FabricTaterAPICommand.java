package dev.neuralnexus.taterapi.fabric.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import dev.neuralnexus.taterapi.common.commands.TaterAPICommand;
import dev.neuralnexus.taterapi.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterapi.fabric.player.FabricPlayer;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.concurrent.atomic.AtomicInteger;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class FabricTaterAPICommand implements TaterAPICommand {
    /**
     * Registers the command.
     * @param dispatcher The command dispatcher.
     * @param registryAccess The command registry access.
     * @param environment The command registration environment.
     */
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        int permissionLevel;
        String commandName = TaterAPICommand.getCommandName();
        if (environment.name().equals("DEDICATED")) {
            // Check if LuckPerms is hooked
            permissionLevel = LuckPermsHook.isHooked() ? 0 : 4;
        } else {
            permissionLevel = 0;
        }

        dispatcher.register(literal(commandName)
                .requires(source -> source.hasPermissionLevel(permissionLevel))
                .then(argument("command", StringArgumentType.greedyString())
                        .executes(context -> {
                            AtomicInteger result = new AtomicInteger(1);
                            runTaskAsync(() -> {
                                try {
                                    String[] args = context.getArgument("command", String.class).split(" ");

                                    // Check if sender is a player
                                    boolean isPlayer = context.getSource().getEntity() instanceof ServerPlayerEntity;
                                    FabricPlayer player = isPlayer ? new FabricPlayer((ServerPlayerEntity) context.getSource().getEntity()) : null;

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
