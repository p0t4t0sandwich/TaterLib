package dev.neuralnexus.taterlib.v1_9.sponge.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.v1_9.sponge.player.SpongePlayer;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;

/** Wraps a command callback into a Sponge Command. */
public class SpongeCommandWrapper implements CommandExecutor {
    private final Command.Callback callback;
    private final String commandName;

    public SpongeCommandWrapper(Command.Callback callback, String commandName) {
        this.callback = callback;
        this.commandName = commandName;
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext commandArgs)
            throws CommandException {
        try {
            String[] args = commandArgs.<String>getOne("args").get().split(" ");
            if (src instanceof Player) {
                callback.execute(new SpongePlayer((Player) src), commandName, args);
            }
            callback.execute(new SpongeCommandSender(src), commandName, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommandResult.builder().successCount(1).build();
    }
}
