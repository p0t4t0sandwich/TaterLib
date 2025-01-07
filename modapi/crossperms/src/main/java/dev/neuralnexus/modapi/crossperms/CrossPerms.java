/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.reflecto.MappingEntry;
import dev.neuralnexus.modapi.reflecto.Reflecto;

import org.jetbrains.annotations.ApiStatus;

@SuppressWarnings("UnstableApiUsage")
public class CrossPerms {
    private static final Logger logger = Logger.create("CrossPerms");
    private static final CrossPerms INSTANCE = new CrossPerms();
    private static Reflecto.MappingStore store;
    public static Class<?> ENTITY;
    public static Class<?> SHARED_SUGGESTION_PROVIDER;
    public static Class<?> SERVER_PLAYER;

    private CrossPerms() {}

    public static CrossPerms instance() {
        return INSTANCE;
    }

    public Logger logger() {
        return logger;
    }

    @ApiStatus.Internal
    public Reflecto.MappingStore store() {
        return store;
    }

    public void setup() {
        if (store != null) {
            return;
        }
        // ServerPlayer
        var serverPlayer = MappingEntry.builder("ServerPlayer")
                .mojmap("")
                .searge("")
                .legacySearge("")
                .yarnIntermediary("net.minecraft.class_798");

        // Entity
        var entity = MappingEntry.builder("Entity")
                .mojmap("")
                .searge("")
                .legacySearge("")
                .yarnIntermediary("net.minecraft.class_1297");
        var entity_hasPermissions = MappingEntry.builder("hasPermissions")
                .mojmap("")
                .searge("")
                .legacySearge("")
                .yarnIntermediary("method_5687");

        // SharedSuggestionProvider
        var sharedSuggestionProvider = MappingEntry.builder("SharedSuggestionProvider")
                .mojmap("")
                .searge("")
                .legacySearge("")
                .yarnIntermediary("net.minecraft.class_2172");
        var sharedSuggestionProvider_hasPermissions = MappingEntry.builder("hasPermissions")
                .mojmap("")
                .searge("")
                .legacySearge("")
                .yarnIntermediary("method_9259");


        store = Reflecto.instance().getStore(this)
                .registerClass(serverPlayer)
                .registerClass(entity)
                .registerMethod(entity_hasPermissions, int.class)
                .registerClass(sharedSuggestionProvider)
                .registerMethod(sharedSuggestionProvider_hasPermissions, int.class);

        SERVER_PLAYER = store.getClass("ServerPlayer");
        ENTITY = store.getClass("Entity");
        SHARED_SUGGESTION_PROVIDER = store.getClass("SharedSuggestionProvider");
    }
}
