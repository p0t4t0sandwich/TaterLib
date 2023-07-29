package dev.neuralnexus.taterapi.common.commands;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.placeholder.PlaceholderParser;
import dev.neuralnexus.taterapi.common.player.AbstractPlayer;

import static dev.neuralnexus.taterapi.common.Utils.ansiiParser;


public interface TaterAPICommand {
    static String getCommandName() {
        return "tatapi";
    }

    static String getCommandDescription() {
        return "TaterAPI command.";
    }

    static String permissionBuilder(String[] args) {
        if (args.length == 0) {
            return "tatapi.command";
        } else if (args.length == 1) {
            return "tatapi.command." + args[0].toLowerCase();
        } else if (args.length == 2) {
            return "tatapi.command." + args[0].toLowerCase() + "." + args[1].toLowerCase();
        } else {
            return "tatapi.command." + args[0].toLowerCase() + "." + args[1].toLowerCase() + "." + args[2].toLowerCase();
        }
    }

    static String executeCommand(String[] args) {
        String text;
        switch (args[0].toLowerCase()) {
            case "reload":
                try {
                    // Try to reload the plugin
                    TaterAPI.stop();
                    TaterAPI.start();
                    text = "&aReloaded TaterAPI.";
                } catch (Exception e) {
                    // If an error occurs, print the error and return an error message
                    text = "&cAn error occurred while reloading the plugin.";
                    System.err.println(e);
                    e.printStackTrace();
                }
                break;
            case "version":
                text = "&aTaterAPI v" + TaterAPI.getVersion();
                break;
            default:
                text = "&cUsage: /tatapi <reload|version>";
                break;
        }
        return PlaceholderParser.substituteSectionSign(text);
    }

    static void executeCommand(AbstractPlayer player, boolean isPlayer, String[] args) {
        if (isPlayer) {
            if (!player.hasPermission(permissionBuilder(args))) {
                player.sendMessage("§cYou do not have permission to use this command.");
            } else {
                player.sendMessage(executeCommand(args));
            }
        } else {
            TaterAPI.useLogger(ansiiParser(executeCommand(args)));
        }
    }
}
