/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.modules.core.command;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.config.dump.DumpInfo;
import dev.neuralnexus.taterlib.config.dump.FullDumpInfo;
import dev.neuralnexus.taterlib.loader.impl.LoaderImpl;
import dev.neuralnexus.taterlib.modules.mclogs.api.MCLogsAPI;
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
        return "&cUsage: /taterlib <reload | version | dump | fulldump>";
    }

    @Override
    public String permission() {
        return "taterlib.command";
    }

    @Override
    public String execute(String[] args) {
        if (args.length == 0) {
            return usage();
        }
        String text;
        switch (args[0].toLowerCase()) {
            case "reload":
                try {
                    TaterLib.reload();
                    text = "&aReloaded " + LoaderImpl.PROJECT_NAME + "!";
                } catch (Exception e) {
                    text = "&cAn error occurred while reloading the plugin.";
                    e.printStackTrace();
                }
                break;
            case "version":
                text = "&a" + LoaderImpl.PROJECT_NAME + " v" + LoaderImpl.PROJECT_VERSION;
                break;
            case "dump":
                DumpInfo dumpInfo = new DumpInfo();
                dumpInfo.saveDump();
                text =
                        "&6ServerType: &a"
                                + dumpInfo.platform.toString()
                                + "\n&6MinecraftVersion: &a"
                                + dumpInfo.minecraftVersion.toString()
                                + "\n&6TaterLibVersion: &a"
                                + dumpInfo.taterlibVersion
                                + "\n&6IsForgeHybrid: "
                                + (dumpInfo.isForgeHybrid ? "&a" : "&c")
                                + dumpInfo.isForgeHybrid
                                + "\n&6IsFabricHybrid: "
                                + (dumpInfo.isFabricHybrid ? "&a" : "&c")
                                + dumpInfo.isFabricHybrid
                                + "\n&6IsSpongeForge: "
                                + (dumpInfo.isSpongeForge ? "&a" : "&c")
                                + dumpInfo.isSpongeForge
                                + "\n&6IsSinytraConnector: "
                                + (dumpInfo.isSinytraConnector ? "&a" : "&c")
                                + dumpInfo.isSinytraConnector
                                + "\n&6IsKilt: "
                                + (dumpInfo.isKilt ? "&a" : "&c")
                                + dumpInfo.isKilt;
                break;
            case "fulldump":
                FullDumpInfo fullDumpInfo = new FullDumpInfo();
                fullDumpInfo.saveDump();
                text = "&aFull dump saved to logs/taterlib-fulldump.json";
                text +=
                        MCLogsAPI.uploadLogString("logs/taterlib-fulldump.json")
                                .map(u -> "\n&aFull dump uploaded to MCLogs: " + u.getUrl())
                                .orElse("\n&cFailed to upload full dump to MCLogs.");
                break;
            default:
                text = usage();
                break;
        }
        return text;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player) {
            if (!sender.hasPermission(permission())) {
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
