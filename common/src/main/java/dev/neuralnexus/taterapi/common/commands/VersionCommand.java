package dev.neuralnexus.taterapi.common.commands;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.placeholder.PlaceholderParser;

public interface VersionCommand extends TemplateCommand {
    String commandName = "version";
    String commandDescription = "Displays the version of the plugin.";
    String commandUsage = "&6/taterapi version";
    String commandPermission = "taterapi.command.version";

    default String executeCommand(String[] args) {
        String text;
        if (args.length != 0) {
            text = getCommandUsage();
        } else {
            text = "&aTaterAPI v" + TaterAPI.getVersion();
        }
        return PlaceholderParser.substituteSectionSign(text);
    }
}
