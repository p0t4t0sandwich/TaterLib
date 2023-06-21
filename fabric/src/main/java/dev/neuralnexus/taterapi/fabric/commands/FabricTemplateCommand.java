package dev.neuralnexus.taterapi.fabric.commands;

import dev.neuralnexus.taterapi.fabric.FabricMain;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager.RegistrationEnvironment;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static dev.neuralnexus.taterapi.common.Utils.ansiiParser;
import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;


public final class FabricTemplateCommand {
    private static final FabricMain mod = FabricMain.getInstance();

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, RegistrationEnvironment environment) {
        dispatcher.register(literal("pronouns")
            .requires(source -> source.hasPermissionLevel(0))
            .then(argument("pronouns", StringArgumentType.greedyString())
            .executes(context -> {
                runTaskAsync(() -> {
                    try {
                        String[] args = new String[] {context.getArgument("pronouns", String.class)};

                        // Send message to player or console
                        Entity entity = context.getSource().getEntity();
                        if (entity instanceof ServerPlayerEntity) {
                            String text = "";
                            entity.sendMessage(Text.literal(text));
                        } else {
                            mod.logger.info(ansiiParser("§cYou must be a player to use this command."));
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
