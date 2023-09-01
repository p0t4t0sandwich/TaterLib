package dev.neuralnexus.taterlib.sponge.commands;

import dev.neuralnexus.taterlib.common.commands.TaterLibCommand;
import dev.neuralnexus.taterlib.sponge.abstractions.player.SpongePlayer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;

public class SpongeTaterLibCommand implements CommandExecutor {
    /**
     * Register the command
     * @param container The plugin container
     */
    public void onRegisterCommands(PluginContainer container) {
        // Register commands
        Sponge.getCommandManager().register(container, buildCommand(), TaterLibCommand.getCommandName(), "tl", "tlib");
    }

    /**
     * Build the command
     * @return The command
     */
    public CommandSpec buildCommand(){
        return CommandSpec
                .builder()
                .executor(new SpongeTaterLibCommand())
                .permission("taterlib.command")
                .description(Text.of("TaterLib command"))
                .arguments(GenericArguments.remainingJoinedStrings(Text.of("command")))
                .build();
    }

    /**
     * @inheritDoc
     */
    @Override
    public CommandResult execute(CommandSource src, CommandContext commandArgs) throws CommandException {
        try {
            String[] args = commandArgs.<String>getOne("command").get().split(" ");

            // Check if sender is a player
            boolean isPlayer = src instanceof Player;
            SpongePlayer player = isPlayer ? new SpongePlayer((Player) src) : null;

            // Execute command
            TaterLibCommand.executeCommand(player, isPlayer, args);
        } catch (Exception e) {
            e.printStackTrace();
            return CommandResult.builder()
                    .successCount(1).build();
        }
        return CommandResult.builder()
                .successCount(1).build();
    }
}
