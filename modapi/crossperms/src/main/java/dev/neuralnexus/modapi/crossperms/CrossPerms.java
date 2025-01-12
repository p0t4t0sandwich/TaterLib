/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;
import dev.neuralnexus.modapi.reflecto.MappingEntry;
import dev.neuralnexus.modapi.reflecto.Reflecto;

import org.jetbrains.annotations.ApiStatus;

@SuppressWarnings("UnstableApiUsage")
public class CrossPerms {
    private static final Logger logger = Logger.create("CrossPerms");
    private static final CrossPerms INSTANCE = new CrossPerms();
    private static Reflecto.MappingStore store;
    public static Object MINECRAFT_SERVER;
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

    public void setup(Object server) {
        if (store != null) {
            return;
        }
        MINECRAFT_SERVER = server;
        store = Reflecto.instance().getStore(this);

        // spotless:off

        // MinecraftServer
        var mcString = "net.minecraft.server.MinecraftServer";
        var minecraftServer =
                MappingEntry.builder("MinecraftServer")
                        .official(mcString)
                        .mojang(mcString)
                        .searge(mcString)
                        .searge(
                                "net.minecraft.src.C_4977_",
                                MinecraftVersions.V17,
                                MinecraftVersions.V19_4)
                        .legacySearge(mcString)
                        .mcp(mcString)
                        .yarnIntermediary(mcString)
                        .legacyIntermediary(mcString);

        store.registerClass(minecraftServer);

        // MinecraftServer#getPlayerList() -> PlayerList
        var minecraftServer_getPlayerList =
                MappingEntry.builder("getPlayerList")
                        .parentEntry("MinecraftServer")
                        .mojang("getPlayerList")
                        .searge("m_6846_")
                        .legacySearge(
                                "func_184103_al", MinecraftVersions.V9, MinecraftVersions.V16_5)
                        .mcp("getPlayerList", MinecraftVersions.V9, MinecraftVersions.V16_5)
                        .legacySearge("func_71203_ab", MinecraftVersions.V7, MinecraftVersions.V8_9)
                        .mcp(
                                "getConfigurationManager",
                                MinecraftVersions.V7,
                                MinecraftVersions.V8_9)
                        .yarnIntermediary("method_3760")
                        .legacyIntermediary("method_3004");

        store.registerMethod(minecraftServer_getPlayerList);

        // PlayerList
        var playerList =
                MappingEntry.builder("PlayerList")
                        .mojang("net.minecraft.server.players.PlayerList")
                        .searge("net.minecraft.src.C_102_")
                        .legacySearge("net.minecraft.server.management.PlayerList")
                        .mcp("net.minecraft.server.management.PlayerList")
                        .legacySearge(
                                "net.minecraft.server.management.ServerConfigurationManager",
                                MinecraftVersions.V7,
                                MinecraftVersions.V8_9)
                        .mcp(
                                "net.minecraft.server.management.ServerConfigurationManager",
                                MinecraftVersions.V7,
                                MinecraftVersions.V8_9)
                        .yarnIntermediary("net.minecraft.class_3324")
                        .legacyIntermediary("net.minecraft.class_743");

        // PlayerList#getPlayers() -> List<ServerPlayer>
        var playerList_getPlayers =
                MappingEntry.builder("getPlayers")
                        .parentEntry("PlayerList")
                        .versionRange(MinecraftVersions.V8, MinecraftVersions.UNKNOWN)
                        .mojang("getPlayers")
                        .searge("m_11314_")
                        .legacySearge("func_181057_v")
                        .mcp("getPlayers")
                        .mcp("getPlayerList", MinecraftVersions.V8, MinecraftVersions.V9_4)
                        .yarnIntermediary("method_14571")
                        .legacyIntermediary("method_10783");

        // PlayerList#playerEntityList -> List<Player>
        var playerList_playerEntityList =
                MappingEntry.builder("players")
                        .parentEntry("PlayerList")
                        .versionRange(MinecraftVersions.V7, MinecraftVersions.V7_10)
                        .legacySearge("field_72404_b")
                        .mcp("playerEntityList")
                        .legacyIntermediary("field_2708");

        // PlayerList#isOp(GameProfile) -> boolean
        var playerList_isOp =
                MappingEntry.builder("isOp")
                        .parentEntry("PlayerList")
                        .mojang("isOp")
                        .searge("m_11303_")
                        .legacySearge("func_152596_g")
                        .mcp("canSendCommands")
                        .yarnIntermediary("method_14569")
                        .legacyIntermediary("method_8232");

        store.registerClass(playerList)
                .registerMethod(playerList_getPlayers)
                .registerField(playerList_playerEntityList)
                .registerMethod(playerList_isOp, GameProfile.class);

        // --------------------------------- end

        // ServerPlayer
        var serverPlayer =
                MappingEntry.builder("ServerPlayer").yarnIntermediary("net.minecraft.class_798");

        store.registerClass(serverPlayer);

        // Entity
        var entity = MappingEntry.builder("Entity").yarnIntermediary("net.minecraft.class_1297");

        // Entity#hasPermissions(int) -> boolean
        var entity_hasPermissions =
                MappingEntry.builder("hasPermissions")
                        .parentEntry("Entity")
                        .yarnIntermediary("method_5687");

        store.registerClass(entity).registerMethod(entity_hasPermissions);

        // SharedSuggestionProvider
        var sharedSuggestionProvider =
                MappingEntry.builder("SharedSuggestionProvider")
                        .yarnIntermediary("net.minecraft.class_2172");

        // SharedSuggestionProvider#hasPermissions(int) -> boolean
        var sharedSuggestionProvider_hasPermissions =
                MappingEntry.builder("hasPermissions")
                        .parentEntry("SharedSuggestionProvider")
                        .yarnIntermediary("method_9259");

        store.registerClass(sharedSuggestionProvider)
                .registerMethod(sharedSuggestionProvider_hasPermissions);

        // CommandSender stuff -- Really old
        // -----------------------------------------------------------------------------------------------
        var commandSender =
                MappingEntry.builder("CommandSender")
                        .legacySearge("net.minecraft.command.ICommandSender")
                        .mcp("net.minecraft.command.ICommandSender")
                        .yarnIntermediary("net.minecraft.class_2172");

        // CommandSender#getCommandSenderEntity() -> Entity
        var commandSender_getCommandSenderEntity =
                MappingEntry.builder("getCommandSenderEntity")
                        .parentEntry("CommandSender")
                        .legacySearge("func_174793_f")
                        .yarnIntermediary("method_9259");

        store.registerClass(commandSender).registerMethod(commandSender_getCommandSenderEntity);

        // spotless:on

        SERVER_PLAYER = store.getClass("ServerPlayer");
        ENTITY = store.getClass("Entity");
        SHARED_SUGGESTION_PROVIDER = store.getClass("SharedSuggestionProvider");
    }
}
