package dev.neuralnexus.taterlib.common.commands;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.placeholder.PlaceholderParser;
import dev.neuralnexus.taterlib.common.player.Player;

import static dev.neuralnexus.taterlib.common.Utils.ansiiParser;


public interface TaterLibCommand {
    static String getCommandName() {
        return "taterlib";
    }

    static String getCommandDescription() {
        return "TaterLib command.";
    }

    static String permissionBuilder(String[] args) {
        if (args.length == 0) {
            return "taterlib.command";
        } else if (args.length == 1) {
            return "taterlib.command." + args[0].toLowerCase();
        } else if (args.length == 2) {
            return "taterlib.command." + args[0].toLowerCase() + "." + args[1].toLowerCase();
        } else {
            return "taterlib.command." + args[0].toLowerCase() + "." + args[1].toLowerCase() + "." + args[2].toLowerCase();
        }
    }

    static String executeCommand(String[] args) {
        String text;
        if (args.length == 0) {
            return "&cUsage: /taterlib <reload|version>";
        }
        switch (args[0].toLowerCase()) {
            case "reload":
                try {
                    // Try to reload the plugin
                    TaterLib.reload();
                    text = "&aReloaded TaterLib.";
                } catch (Exception e) {
                    // If an error occurs, print the error and return an error message
                    text = "&cAn error occurred while reloading the plugin.";
                    System.err.println(e);
                    e.printStackTrace();
                }
                break;
            case "version":
                text = "&aTaterLib v" + TaterLib.getVersion();
                break;
            default:
                text = "&cUsage: /taterlib <reload|version>";
                break;
        }
        return PlaceholderParser.substituteSectionSign(text);
    }

    static void executeCommand(Player player, boolean isPlayer, String[] args) {
        if (isPlayer) {
            if (!player.hasPermission(permissionBuilder(args))) {
                player.sendMessage("§cYou do not have permission to use this command.");
            } else {
                player.sendMessage(executeCommand(args));
            }
        } else {
            TaterLib.logger.info(ansiiParser(executeCommand(args)));
        }
    }
}
