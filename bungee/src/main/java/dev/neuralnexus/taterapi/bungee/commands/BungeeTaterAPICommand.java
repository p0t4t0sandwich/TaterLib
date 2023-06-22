package dev.neuralnexus.taterapi.bungee.commands;

import dev.neuralnexus.taterapi.bungee.BungeeMain;
import dev.neuralnexus.taterapi.common.commands.TaterAPICommand;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

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
                if ((sender instanceof ProxiedPlayer)) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;

                    // Permission check
                    if (!player.hasPermission(getCommandPermission())) {
                        player.sendMessage(new ComponentBuilder("§cYou do not have permission to use this command.").create());
                        return;
                    }
                    player.sendMessage(new ComponentBuilder(executeCommand(args)).create());
                } else {
                    sender.sendMessage(new ComponentBuilder(executeCommand(args)).create());
                }
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
