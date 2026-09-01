/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.testmod.commands;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.command.CommandSource;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.util.TextUtil;

/** Example Command. */
public class PingPongCommand implements Command {
    private String name = "ping";

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String description() {
        return "Network packet Test command";
    }

    @Override
    public String usage() {
        return "/ping";
    }

    @Override
    public String permission() {
        return "testmod.command.ping";
    }

    @Override
    public boolean execute(CommandSource sender, String label, String[] args) {
        if (!(sender instanceof ServerPlayer player)) {
            sender.sendMessage(
                    TextUtil.substituteSectionSign(
                            "&cThis command can only be executed by a player!"));
            return true;
        }
        player.sendPacket(Identifier.of("testmod", "ping"), "Ping".getBytes());
        return true;
    }
}
