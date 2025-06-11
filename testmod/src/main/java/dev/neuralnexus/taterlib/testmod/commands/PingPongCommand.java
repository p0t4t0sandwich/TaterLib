/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.testmod.commands;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.command.CommandSource;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterapi.resource.ResourceKey;
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
        player.sendPacket(ResourceKey.of("testmod", "ping"), "Ping".getBytes());
        return true;
    }
}
