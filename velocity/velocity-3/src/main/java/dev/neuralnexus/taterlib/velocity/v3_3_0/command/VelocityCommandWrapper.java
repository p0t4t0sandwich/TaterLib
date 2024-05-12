package dev.neuralnexus.taterlib.velocity.v3_3_0.command;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.RawCommand;
import com.velocitypowered.api.proxy.Player;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.velocity.v3_3_0.player.VelocityPlayer;

/** Wraps a command callback into a Velocity Command. */
public class VelocityCommandWrapper implements RawCommand {
    private final Command.Callback callback;

    public VelocityCommandWrapper(Command.Callback callback) {
        this.callback = callback;
    }

    @Override
    public void execute(final Invocation invocation) {
        CommandSource sender = invocation.source();
        if (sender instanceof Player) {
            callback.execute(
                    new VelocityPlayer((Player) sender),
                    invocation.alias(),
                    invocation.arguments().split(" "));
        } else {
            callback.execute(
                    new VelocityCommandSender(sender),
                    invocation.alias(),
                    invocation.arguments().split(" "));
        }
    }
}
