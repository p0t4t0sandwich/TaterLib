/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.testmod.commands;

import dev.neuralnexus.modapi.metadata.impl.util.TextUtil;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterlib.testmod.api.TestModAPIProvider;

/** Example Command. */
public class TestModCommand implements Command {
    private String name = "example";

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
        return "Example command";
    }

    @Override
    public String usage() {
        return "/example";
    }

    @Override
    public String permission() {
        return "example.command.example";
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!sender.hasPermission(permission())) {
            sender.sendMessage(
                    TextUtil.substituteSectionSign(
                            "&cYou do not have permission to execute this command!"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(
                    TextUtil.substituteSectionSign(
                            "&cThis command can only be executed by a player!"));
            return true;
        }

        sender.sendMessage(TextUtil.substituteSectionSign(TestModAPIProvider.get().getSomeData()));
        return true;
    }
}
