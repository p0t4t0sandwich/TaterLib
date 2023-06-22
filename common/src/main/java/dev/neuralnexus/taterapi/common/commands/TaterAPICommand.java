package dev.neuralnexus.taterapi.common.commands;

import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.placeholder.PlaceholderParser;

public interface TaterAPICommand extends TemplateCommand {
    String commandName = "taterapi";
    String commandDescription = "TaterAPI command.";
    String commandUsage = "&6/taterapi <reload|version>";
    String commandPermission = "taterapi.command";

    default String executeCommand(String[] args) {
        String text;
        if (args.length == 0) {
            text = getCommandUsage();
        } else {
            switch (args[0].toLowerCase()) {
                case "reload":
                    text = new ReloadCommand(){}.executeCommand(new String[]{});
                    break;
                case "version":
                    text = new VersionCommand(){}.executeCommand(new String[]{});
                    break;
                default:
                    text = getCommandUsage();
                    break;
            }
        }
        return PlaceholderParser.substituteSectionSign(text);
    }
}
