package dev.neuralnexus.taterlib.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.neuralnexus.taterlib.common.TaterLib;

/**
 * TaterLib brigadier command.
 * @param <S> The command source.
 */
public class TaterLibBrigadierCommand<S> extends BasicBrigadierCommand<S> {
    @Override
    public int run(CommandContext<S> context) throws CommandSyntaxException {
        S source = context.getSource();
        Sender sender = TaterLib.getBrigadierCommandUtils().getSender(source);

        // Return early if not dedicated and not OP
        if (!TaterLib.getBrigadierCommandUtils().isDedicated() && !sender.hasPermission(4)) {
            return Command.SINGLE_SUCCESS;
        }

        String[] args = context.getArgument("args", String.class).split(" ");
        boolean isPlayer = TaterLib.getBrigadierCommandUtils().isPlayer(source);
        if (isPlayer) {
            sender = TaterLib.getBrigadierCommandUtils().getPlayer(source);
        }
        TaterLibCommand.executeCommand(sender, isPlayer, args);
        return Command.SINGLE_SUCCESS;
    }
}
