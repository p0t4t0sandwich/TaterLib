package dev.neuralnexus.taterlib.sponge.commands;

import dev.neuralnexus.taterlib.common.command.TaterLibCommand;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;
import net.kyori.adventure.text.Component;
import org.spongepowered.api.command.Command;
import org.spongepowered.api.command.CommandExecutor;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.exception.CommandException;
import org.spongepowered.api.command.parameter.CommandContext;
import org.spongepowered.api.command.parameter.Parameter;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.lifecycle.RegisterCommandEvent;
import org.spongepowered.plugin.PluginContainer;

public class SpongeTaterLibCommand implements CommandExecutor {
    Parameter.Value<String> commandArgs = Parameter.string().key("command").build();

    /**
     * Register the command
     * @param container The plugin container
     * @param event The event
     */
    public void onRegisterCommands(PluginContainer container, final RegisterCommandEvent<Command.Parameterized> event) {
        // Register commands
        event.register(container, buildCommand(), TaterLibCommand.getCommandName(), "tl", "tlib");
    }

    /**
     * Build the command
     * @return The command
     */
    public Command.Parameterized buildCommand(){
        return Command
                .builder()
                .executor(new SpongeTaterLibCommand())
                .permission("taterlib.command")
                .shortDescription(Component.text("TaterLib command"))
                .addParameter(commandArgs)
                .build();
    }

    /**
     * @inheritDoc
     */
    @Override
    public CommandResult execute(CommandContext context) throws CommandException {
        try {
            String[] args = context.requireOne(commandArgs).split(" ");

            // Check if sender is a player
            boolean isPlayer = context.cause().root() instanceof Player;
            SpongePlayer player = isPlayer ? new SpongePlayer((Player) context.cause().root()) : null;

            // Execute command
            TaterLibCommand.executeCommand(player, isPlayer, args);
        } catch (Exception e) {
            e.printStackTrace();
            return CommandResult.builder()
                    .result(0).error(Component.text(e.getMessage())).build();
        }
        return CommandResult.builder()
                .result(1).build();
    }
}
