/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.modules.core.command;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.impl.loader.LoaderImpl;
import dev.neuralnexus.taterapi.meta.impl.util.TextUtil;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.config.dump.DumpInfo;
import dev.neuralnexus.taterlib.config.dump.FullDumpInfo;
import dev.neuralnexus.taterlib.modules.mclogs.api.MCLogsAPI;

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
                    TaterAPI.logger().error("An error occurred while reloading the plugin", e);
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
                                + dumpInfo.version
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
                                + "\n&6isMixedForgeFabric: "
                                + (dumpInfo.isMixedForgeFabric ? "&a" : "&c")
                                + dumpInfo.isMixedForgeFabric;
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
            if (!TaterAPI.hasPermission(sender, this.permission())) {
                sender.sendMessage(
                        TextUtil.substituteSectionSign(
                                "&cYou do not have permission to use this command."));
            } else {
                sender.sendMessage(TextUtil.substituteSectionSign(this.execute(args)));
            }
        } else {
            sender.sendMessage(
                    TextUtil.ansiParser(TextUtil.substituteSectionSign(this.execute(args))));
        }
        return true;
    }
}
