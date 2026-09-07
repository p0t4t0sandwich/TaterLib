/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

import dev.neuralnexus.taterapi.logger.Logger;

public interface Commands {
    Logger LOGGER = Logger.create("Brigadier General");

    static Logger logger() {
        return LOGGER;
    }

    static LiteralArgumentBuilder<CommandSource> literal(String name) {
        return LiteralArgumentBuilder.literal(name);
    }

    static <T> RequiredArgumentBuilder<CommandSource, T> argument(
            final String name, final ArgumentType<T> type) {
        return RequiredArgumentBuilder.argument(name, type);
    }
}
