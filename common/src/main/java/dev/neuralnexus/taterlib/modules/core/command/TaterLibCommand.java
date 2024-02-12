package dev.neuralnexus.taterlib.modules.core.command;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.player.Player;

public class TaterLibCommand implements Command {
    private String name = "taterlib";

    @Override
    public String name() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String description() {
        return "TaterLib command.";
    }

    @Override
    public String usage() {
        return "&cUsage: /taterlib <reload | version>";
    }

    @Override
    public String permission() {
        return "taterlib.command";
    }

    @Override
    public String execute(String[] args) {
        String text;
        if (args.length == 0) {
            return usage();
        }
        switch (args[0].toLowerCase()) {
            case "reload":
                try {
                    TaterLib.reload();
                    text = "&aReloaded " + TaterLib.Constants.PROJECT_NAME + "!";
                } catch (Exception e) {
                    text = "&cAn error occurred while reloading the plugin.";
                    e.printStackTrace();
                }
                break;
            case "version":
                text =
                        "&a"
                                + TaterLib.Constants.PROJECT_NAME
                                + " v"
                                + TaterLib.Constants.PROJECT_VERSION;
                break;
            default:
                text = usage();
                break;
        }
        return text;
    }

    @Override
    public boolean execute(CommandSender commandSender, String label, String[] args) {
        if (commandSender instanceof Player) {
            if (!commandSender.hasPermission(permission())) {
                commandSender.sendMessage(
                        Utils.substituteSectionSign(
                                "&cYou do not have permission to use this command."));
            } else {
                commandSender.sendMessage(Utils.substituteSectionSign(execute(args)));
            }
        } else {
            commandSender.sendMessage(Utils.ansiParser(Utils.substituteSectionSign(execute(args))));
        }
        return true;
    }
}
