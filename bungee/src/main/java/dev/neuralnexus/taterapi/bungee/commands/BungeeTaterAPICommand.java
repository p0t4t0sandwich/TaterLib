package dev.neuralnexus.taterapi.bungee.commands;

import dev.neuralnexus.taterapi.bungee.player.BungeePlayer;
import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.commands.TaterAPICommand;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static dev.neuralnexus.taterapi.common.Utils.ansiiParser;
import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

public class BungeeTaterAPICommand extends Command {
    public BungeeTaterAPICommand() {
        super(TaterAPICommand.getCommandName() + "b");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        runTaskAsync(() -> {
            try {
                // Check if sender is a player
                boolean isPlayer = sender instanceof ProxiedPlayer;
                BungeePlayer player = isPlayer ? new BungeePlayer((ProxiedPlayer) sender) : null;

                // Execute command
                TaterAPICommand.executeCommand(player, isPlayer, args);
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
