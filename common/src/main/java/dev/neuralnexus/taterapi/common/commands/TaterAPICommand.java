package dev.neuralnexus.taterapi.common.commands;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.placeholder.PlaceholderParser;
import dev.neuralnexus.taterapi.common.player.TaterPlayer;

public interface TaterAPICommand extends TemplateCommand {
    String commandName = "taterapi";
    String commandDescription = "TaterAPI command.";
    String commandUsage = "&6/taterapi <reload|version>";
    String commandPermission = "taterapi.command";

    /**
     * @inheritDoc
     */
    @Override
    default String executeCommand(String[] args) {
        String text;
        if (args.length == 0) {
            text = getCommandUsage();
        } else {
            switch (args[0].toLowerCase()) {
                case "reload":
                    try {
                        // Try to reload the plugin
                        TaterAPI.stop();
                        TaterAPI.start();
                        text = "&aReloaded the plugin.";
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
                    text = getCommandUsage();
                    break;
            }
        }
        return PlaceholderParser.substituteSectionSign(text);
    }

    /**
     * @inheritDoc
     */
    @Override
    default String executeCommand(TaterPlayer player, String[] args) {
        String text;
        if (args.length == 0) {
            text = getCommandUsage();
        } else if (player.hasPermission(getCommandPermission(args[0].toLowerCase()))) {
            text = executeCommand(args);
        } else {
            text = "&cYou do not have permission to use this command.";
        }
        return PlaceholderParser.substituteSectionSign(text);
    }
}
