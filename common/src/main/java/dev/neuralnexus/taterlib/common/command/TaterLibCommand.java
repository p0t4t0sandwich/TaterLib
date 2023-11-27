package dev.neuralnexus.taterlib.common.command;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.Utils;
import dev.neuralnexus.taterlib.common.player.Player;

public class TaterLibCommand implements Command {
    private String name = "taterlib";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return "TaterLib command.";
    }

    @Override
    public String getUsage() {
        return "&cUsage: /taterlib <reload | version>";
    }

    @Override
    public String getPermission() {
        return "taterlib.command";
    }

    @Override
    public String execute(String[] args) {
        String text;
        if (args.length == 0) {
            return getUsage();
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
                text = getUsage();
                break;
        }
        return text;
    }

    @Override
    public boolean execute(Sender sender, String label, String[] args) {
        if (sender instanceof Player) {
            if (!sender.hasPermission(getPermission())) {
                sender.sendMessage(
                        Utils.substituteSectionSign(
                                "&cYou do not have permission to use this command."));
            } else {
                sender.sendMessage(Utils.substituteSectionSign(execute(args)));
            }
        } else {
            sender.sendMessage(Utils.ansiParser(Utils.substituteSectionSign(execute(args))));
        }
        return true;
    }
}
