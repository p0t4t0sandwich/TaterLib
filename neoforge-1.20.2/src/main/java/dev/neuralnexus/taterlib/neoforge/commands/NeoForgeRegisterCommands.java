package dev.neuralnexus.taterlib.neoforge.commands;

import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.neuralnexus.taterlib.common.Constants;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.command.BasicBrigadierCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

/**
 * NeoForge register commands.
 */
@Mod.EventBusSubscriber(modid = Constants.PROJECT_ID)
public final class NeoForgeRegisterCommands {
    /**
     * Registers commands.
     * @param event The register commands event.
     */
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        TaterLib.setBrigadierCommandUtils(new NeoForgeCommandUtils(event.getCommandSelection() == Commands.CommandSelection.DEDICATED));
        for (String commandName : TaterLib.getBrigadierCommands().keySet()) {
            BasicBrigadierCommand<CommandSourceStack> command = (BasicBrigadierCommand<CommandSourceStack>) TaterLib.getBrigadierCommand(commandName);
            LiteralCommandNode<CommandSourceStack> cmd = literal(commandName)
                    .executes(command)
                    .build();

            ArgumentCommandNode<CommandSourceStack, String> args = argument("args", greedyString())
//                        .suggests(command)
                    .executes(command)
                    .build();

            cmd.addChild(args);
            event.getDispatcher().getRoot().addChild(cmd);
        }
    }
}
