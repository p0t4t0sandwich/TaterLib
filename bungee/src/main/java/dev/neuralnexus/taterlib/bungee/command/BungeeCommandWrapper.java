package dev.neuralnexus.taterlib.bungee.command;

import dev.neuralnexus.taterlib.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.common.command.Command;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Wraps a command callback into a Bungee Command.
 */
public class BungeeCommandWrapper extends net.md_5.bungee.api.plugin.Command {
    private final Command.Callback callback;

    public BungeeCommandWrapper(Command.Callback callback, String commandName) {
        super(commandName);
        this.callback = callback;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            callback.execute(new BungeePlayer((ProxiedPlayer) sender), this.getName(), args);
        } else {
            callback.execute(new BungeeSender(sender), this.getName(), args);
        }
    }
}
