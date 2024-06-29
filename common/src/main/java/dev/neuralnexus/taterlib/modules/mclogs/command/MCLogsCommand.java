/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.modules.mclogs.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.modules.mclogs.api.MCLogsAPI;

/** MCLogs Command. */
public class MCLogsCommand implements Command {
    private String name = "mclogs";

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
        return "Upload logs to mclo.gs.";
    }

    @Override
    public String usage() {
        return "/mclogs <upload | list | get>";
    }

    @Override
    public String permission() {
        return "taterutils.command.alert";
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!sender.hasPermission(permission())) {
            sender.sendMessage("§cYou do not have permission to use this command.");
            return true;
        }
        MCLogsAPI api = new MCLogsAPI();

        if (args.length == 0) {
            sender.sendMessage("§cUsage: " + usage());
            return true;
        }
        switch (args[0]) {
            case "list":
                if (args.length == 1) {
                    sender.sendMessage("§cUsage: " + usage()); // TODO add as message
                    return true;
                }
            case "upload":
                if (args.length == 1) {
                    sender.sendMessage("§cUsage: " + usage()); // TODO add as message
                    return true;
                }
                break;
            case "get":
                if (args.length == 1) {
                    sender.sendMessage("§cUsage: " + usage()); // TODO add as message
                    return true;
                }
                break;
            default:
                sender.sendMessage("§cUsage: " + usage()); // TODO add as message
                return true;
        }
        return true;
    }
}
