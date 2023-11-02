package dev.neuralnexus.taterlib.forge.command;

import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.neuralnexus.taterlib.common.Constants;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.command.BasicBrigadierCommand;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

/**
 * Forge register commands.
 */
@Mod.EventBusSubscriber(modid = Constants.PROJECT_ID)
public final class ForgeRegisterCommands {
    /**
     * Registers commands.
     * @param event The register commands event.
     */
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        TaterLib.setBrigadierCommandUtils(new ForgeCommandUtils(event.getEnvironment() == Commands.EnvironmentType.DEDICATED));
        for (String commandName : TaterLib.getBrigadierCommands().keySet()) {
            BasicBrigadierCommand<CommandSource> command = (BasicBrigadierCommand<CommandSource>) TaterLib.getBrigadierCommand(commandName);
            LiteralCommandNode<CommandSource> cmd = literal(commandName)
                    .executes(command)
                    .build();

            ArgumentCommandNode<CommandSource, String> args = argument("args", greedyString())
//                        .suggests(command)
                    .executes(command)
                    .build();

            cmd.addChild(args);
            event.getDispatcher().getRoot().addChild(cmd);
        }
    }
}
