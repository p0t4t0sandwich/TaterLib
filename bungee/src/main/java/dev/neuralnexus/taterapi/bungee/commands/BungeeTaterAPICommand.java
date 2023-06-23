package dev.neuralnexus.taterapi.bungee.commands;

import dev.neuralnexus.taterapi.bungee.player.BungeeTaterPlayer;
import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.commands.TaterAPICommand;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static dev.neuralnexus.taterapi.common.Utils.ansiiParser;
import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

public class BungeeTaterAPICommand extends Command implements TaterAPICommand {
    public BungeeTaterAPICommand() {
        super(TaterAPICommand.commandName);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        runTaskAsync(() -> {
            try {
                // Check if sender is a player
                if (sender instanceof ProxiedPlayer) {
                    // Execute command as player
                    BungeeTaterPlayer player = new BungeeTaterPlayer((ProxiedPlayer) sender);
                    player.sendMessage(executeCommand(player, args));
                } else {
                    // Execute command as console
                    TaterAPI.useLogger(ansiiParser(executeCommand(args)));
                }
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
