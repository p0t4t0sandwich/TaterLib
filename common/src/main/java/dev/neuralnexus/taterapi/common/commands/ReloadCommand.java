package dev.neuralnexus.taterapi.common.commands;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.placeholder.PlaceholderParser;

public interface ReloadCommand extends TemplateCommand {
    String commandName = "reload";
    String commandDescription = "Reloads the plugin.";
    String commandUsage = "&6/taterapi reload";
    String commandPermission = "taterapi.command.reload";

    default String executeCommand(String[] args) {
        String text;
        if (args.length != 0) {
            text = getCommandUsage();
        } else {
            try {
                TaterAPI.stop();
                TaterAPI.start();
                text = "&aReloaded the plugin.";
            } catch (Exception e) {
                text = "&cAn error occurred while reloading the plugin.";
            }
        }
        return PlaceholderParser.substituteSectionSign(text);
    }
}
