/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.modapi.crossperms.api.PermsAPI;
import dev.neuralnexus.modapi.crossperms.api.impl.providers.BukkitPermissionsProvider;
import dev.neuralnexus.modapi.crossperms.api.impl.providers.BungeeCordPermissionsProvider;
import dev.neuralnexus.modapi.crossperms.api.impl.providers.FabricPermissionsProvider;
import dev.neuralnexus.modapi.crossperms.api.impl.providers.ForgePermissionsProvider;
import dev.neuralnexus.modapi.crossperms.api.impl.providers.ForgePermissionsProvider_18_2;
import dev.neuralnexus.modapi.crossperms.api.impl.providers.LegacyFabricPermissionsProvider;
import dev.neuralnexus.modapi.crossperms.api.impl.providers.SpongePermissionsProvider;
import dev.neuralnexus.modapi.crossperms.api.impl.providers.VanillaPermissionsProvider;
import dev.neuralnexus.modapi.crossperms.api.impl.providers.VelocityPermissionsProvider;
import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.modapi.reflecto.MappingEntry;
import dev.neuralnexus.modapi.reflecto.Reflecto;

import org.jetbrains.annotations.ApiStatus;

import java.util.UUID;

@SuppressWarnings("UnstableApiUsage")
public class CrossPerms {
    private static final Logger logger = Logger.create("CrossPerms");
    private static final CrossPerms INSTANCE = new CrossPerms();
    private static Reflecto.MappingStore store;
    public static Object MINECRAFT_SERVER;

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

    /**
     * Initialize CrossPerms
     *
     * @param minecraftServer The Minecraft server instance
     */
    public void init(Object minecraftServer) {
        if (null != store) {
            return;
        }
        register(minecraftServer);

        MetaAPI meta = MetaAPI.instance();
        PermsAPI api = PermsAPI.instance();
        if (meta.isProxy()) {
            if (meta.isPlatformPresent(Platforms.BUNGEECORD)) {
                 api.registerProvider(new BungeeCordPermissionsProvider());
            } else if (meta.isPlatformPresent(Platforms.VELOCITY)) {
                 api.registerProvider(new VelocityPermissionsProvider());
            }
            return;
        }
        api.registerProvider(new VanillaPermissionsProvider());
        if (meta.isPlatformPresent(Platforms.BUKKIT)) {
            api.registerProvider(new BukkitPermissionsProvider());
        }
        if (meta.isPlatformPresent(Platforms.FABRIC)) {
            if (meta.version().isAtLeast(MinecraftVersions.V14)) {
                api.registerProvider(new FabricPermissionsProvider());
            } else {
                api.registerProvider(new LegacyFabricPermissionsProvider());
            }
        }
        if (meta.isPlatformPresent(Platforms.FORGE)) {
            if (meta.version().isAtLeast(MinecraftVersions.V18_2)) {
                api.registerProvider(new ForgePermissionsProvider_18_2());
            } else {
                api.registerProvider(new ForgePermissionsProvider());
            }
        }
        if (meta.isPlatformPresent(Platforms.SPONGE)) {
            api.registerProvider(new SpongePermissionsProvider());
        }
    }

    /**
     * Register mappings
     *
     * @param minecraftServer The Minecraft server instance
     */
    private void register(Object minecraftServer) {
        logger.info("Initializing CrossPerms mappings");

        MINECRAFT_SERVER = minecraftServer;
        store = Reflecto.instance().getStore(this);

        // MinecraftServer
        var mcString = "net.minecraft.server.MinecraftServer";
        var mcServer =
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

        // MinecraftServer#getPlayerList() -> PlayerList
        var minecraftServer_getPlayerList =
                MappingEntry.builder("getPlayerList")
                        .parentEntry(mcServer)
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

        store.registerClass(mcServer).registerMethod(minecraftServer_getPlayerList);
        logger.info("Registered MinecraftServer");
        logger.info("|-> getPlayerList");

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
                        .parentEntry(playerList)
                        .versionRange(MinecraftVersions.V8, MinecraftVersions.UNKNOWN)
                        .mojang("getPlayers")
                        .searge("m_11314_")
                        .legacySearge("func_181057_v")
                        .mcp("getPlayers")
                        .mcp("getPlayerList", MinecraftVersions.V8, MinecraftVersions.V9_4)
                        .yarnIntermediary("method_14571")
                        .legacyIntermediary("method_10783");

        // PlayerList#playerEntityList -> List<ServerPlayer>
        var playerList_playerEntityList =
                MappingEntry.builder("players")
                        .parentEntry(playerList)
                        .versionRange(MinecraftVersions.V7, MinecraftVersions.V7_10)
                        .legacySearge("field_72404_b")
                        .mcp("playerEntityList")
                        .legacyIntermediary("field_2708");

        // PlayerList#getPlayer(UUID) -> ServerPlayer
        var playerList_getPlayerByUUID =
                MappingEntry.builder("getPlayerByUUID")
                        .versionRange(MinecraftVersions.V8, MinecraftVersions.UNKNOWN)
                        .parentEntry(playerList)
                        .mojang("getPlayer")
                        .searge("m_11259_")
                        .legacySearge("func_177451_a")
                        .mcp("getPlayerByUUID")
                        .yarnIntermediary("method_14602")
                        .legacyIntermediary("method_10779");

        // PlayerList#getPlayerByName(String) -> ServerPlayer
        var playerList_getPlayerByName =
                MappingEntry.builder("getPlayerByName")
                        .parentEntry(playerList)
                        .mojang("getPlayerByName")
                        .searge("m_11255_")
                        .legacySearge("func_152612_a")
                        .mcp("getPlayerByUsername")
                        .yarnIntermediary("method_14566")
                        .legacyIntermediary("method_2010");

        // PlayerList#isOp(GameProfile) -> boolean
        var playerList_isOp =
                MappingEntry.builder("isOp")
                        .parentEntry(playerList)
                        .mojang("isOp")
                        .searge("m_11303_")
                        .legacySearge("func_152596_g")
                        .mcp("canSendCommands")
                        .yarnIntermediary("method_14569")
                        .legacyIntermediary("method_8232");

        // PlayerList#getOps() -> List<ServerOpListEntry>
        var playerList_getOps =
                MappingEntry.builder("getOps")
                        .parentEntry(playerList)
                        .mojang("getOps")
                        .searge("m_11307_")
                        .legacySearge("func_152603_m")
                        .mcp("getOppedPlayers")
                        .yarnIntermediary("method_14603")
                        .legacyIntermediary("method_8236");

        store.registerClass(playerList)
                .registerMethod(playerList_getPlayers)
                .registerField(playerList_playerEntityList)
                .registerMethod(playerList_getPlayerByUUID, UUID.class)
                .registerMethod(playerList_getPlayerByName, String.class)
                .registerMethod(playerList_isOp, GameProfile.class)
                .registerMethod(playerList_getOps);
        logger.info("Registered PlayerList");
        logger.info("|-> getPlayers");
        logger.info("|-> players");
        logger.info("|-> getPlayerByUUID");
        logger.info("|-> getPlayerByName");
        logger.info("|-> isOp");
        logger.info("|-> getOps");

        // ServerOpListEntry (StoredUserEntry<GameProfile>)
        var serverOpListEntry =
                MappingEntry.builder("ServerOpListEntry")
                        .mojang("net.minecraft.server.players.ServerOpListEntry")
                        .searge("net.minecraft.src.C_105_")
                        .legacySearge("net.minecraft.server.management.OpEntry")
                        .mcp("net.minecraft.server.management.OpEntry")
                        .yarnIntermediary("net.minecraft.class_3327")
                        .legacyIntermediary("net.minecraft.class_2132");

        // ServerOpListEntry#getLevel -> int
        var serverOpListEntry_getLevel =
                MappingEntry.builder("getLevel")
                        .parentEntry(serverOpListEntry)
                        .mojang("getLevel")
                        .searge("m_11363_")
                        .legacySearge("func_152644_a")
                        .mcp("getPermissionLevel")
                        .mcp("func_152644_a", MinecraftVersions.V7, MinecraftVersions.V7_10)
                        .yarnIntermediary("method_14623")
                        .legacyIntermediary("method_8240");

        // StoredUserEntry#getUser -> GameProfile
        var storedUserEntry_getUser =
                MappingEntry.builder("getUser")
                        .parentEntry(serverOpListEntry)
                        .mojang("getUser")
                        .searge("m_11373_")
                        .legacySearge("func_152640_f")
                        .mcp("getValue")
                        .yarnIntermediary("method_14626")
                        .legacyIntermediary("method_8243");

        store.registerClass(serverOpListEntry)
                .registerMethod(serverOpListEntry_getLevel)
                .registerMethod(storedUserEntry_getUser);
        logger.info("Registered ServerOpListEntry");
        logger.info("|-> getLevel");
        logger.info("|-> getUser");

        // Entity
        var entity =
                MappingEntry.builder("Entity")
                        .mojang("net.minecraft.world.entity.Entity")
                        .searge("net.minecraft.src.C_507_")
                        .legacySearge("net.minecraft.entity.Entity")
                        .mcp("net.minecraft.entity.Entity")
                        .yarnIntermediary("net.minecraft.class_1297")
                        .legacyIntermediary("net.minecraft.class_864");

        // Player
        var player =
                MappingEntry.builder("Player")
                        .mojang("net.minecraft.world.entity.player.Player")
                        .searge("net.minecraft.src.C_1141_")
                        .legacySearge("net.minecraft.entity.player.PlayerEntity")
                        .mcp("net.minecraft.entity.player.PlayerEntity")
                        .yarnIntermediary("net.minecraft.class_1657")
                        .legacyIntermediary("net.minecraft.class_988");

        // ServerPlayer
        var serverPlayer =
                MappingEntry.builder("ServerPlayer")
                        .mojang("net.minecraft.server.level.ServerPlayer")
                        .searge("net.minecraft.src.C_13_")
                        .legacySearge("net.minecraft.entity.player.ServerPlayerEntity")
                        .mcp("net.minecraft.entity.player.ServerPlayerEntity")
                        .legacySearge(
                                "net.minecraft.entity.player.EntityPlayerMP",
                                MinecraftVersions.V7,
                                MinecraftVersions.V13_2)
                        .mcp(
                                "net.minecraft.entity.player.EntityPlayerMP",
                                MinecraftVersions.V7,
                                MinecraftVersions.V13_2)
                        .yarnIntermediary("net.minecraft.class_3222")
                        .legacyIntermediary("net.minecraft.class_798");

        // Entity#hasPermissions(int) -> boolean
        var entity_hasPermissions =
                MappingEntry.builder("hasPermissions")
                        .versionRange(MinecraftVersions.V13, MinecraftVersions.UNKNOWN)
                        .parentEntry(serverPlayer)
                        .mojang("hasPermissions")
                        .searge("m_20310_")
                        .searge("m_352356_", MinecraftVersions.V21_1, MinecraftVersions.UNKNOWN)
                        .legacySearge("func_211513_k")
                        .mcp("hasPermissionLevel")
                        .yarnIntermediary("method_5687")
                        .yarnIntermediary(
                                "method_64475", MinecraftVersions.V21_1, MinecraftVersions.UNKNOWN)
                        .legacyIntermediary("method_15592");

        // Player#getGameProfile() -> GameProfile
        var player_getGameProfile =
                MappingEntry.builder("getGameProfile")
                        .parentEntry(serverPlayer)
                        .mojang("getGameProfile")
                        .searge("m_36316_")
                        .legacySearge("func_146103_bH")
                        .mcp("getGameProfile")
                        .yarnIntermediary("method_7334")
                        .legacyIntermediary("method_8429");

        store.registerClass(serverPlayer)
                .registerMethod(player_getGameProfile) // Inherited from Player
                .registerMethod(entity_hasPermissions); // Inherited from Entity (Only until 1.21.1)
        logger.info("Registered ServerPlayer");
        logger.info("|-> getGameProfile");
        logger.info("|-> hasPermissions");

        // SharedSuggestionProvider
        var commandSource =
                MappingEntry.builder("CommandSource")
                        .versionRange(MinecraftVersions.V13, MinecraftVersions.UNKNOWN)
                        .mojang("net.minecraft.commands.SharedSuggestionProvider")
                        .searge("net.minecraft.src.C_3063_")
                        .legacySearge("net.minecraft.command.ISuggestionProvider")
                        .mcp("net.minecraft.command.ISuggestionProvider")
                        .yarnIntermediary("net.minecraft.class_2172")
                        .legacyIntermediary("net.minecraft.class_3965")
                        .yarn("net.minecraft.command.CommandSource");

        // CommandSourceStack
        var commandSender =
                MappingEntry.builder("CommandSender")
                        .mojang(
                                "net.minecraft.commands.CommandSourceStack",
                                MinecraftVersions.V13,
                                MinecraftVersions.UNKNOWN)
                        .searge(
                                "net.minecraft.src.C_2969_",
                                MinecraftVersions.V13,
                                MinecraftVersions.UNKNOWN)
                        .legacySearge(
                                "net.minecraft.command.CommandSource",
                                MinecraftVersions.V13,
                                MinecraftVersions.UNKNOWN)
                        .mcp(
                                "net.minecraft.command.CommandSource",
                                MinecraftVersions.V13,
                                MinecraftVersions.UNKNOWN)
                        .yarnIntermediary(
                                "net.minecraft.class_2168",
                                MinecraftVersions.V13,
                                MinecraftVersions.UNKNOWN)
                        .legacyIntermediary(
                                "net.minecraft.class_3915",
                                MinecraftVersions.V13,
                                MinecraftVersions.UNKNOWN)
                        // Old CommandSender implementations
                        .legacySearge(
                                "net.minecraft.command.ICommandSender",
                                MinecraftVersions.V7,
                                MinecraftVersions.V12_2)
                        .mcp(
                                "net.minecraft.command.ICommandSender",
                                MinecraftVersions.V7,
                                MinecraftVersions.V12_2)
                        .yarnIntermediary(
                                "net.minecraft.class_2172",
                                MinecraftVersions.V7,
                                MinecraftVersions.V12_2);

        // SharedSuggestionProvider#hasPermissions(int) -> boolean
        var commandSource_hasPermissions =
                MappingEntry.builder("hasPermissions")
                        .versionRange(MinecraftVersions.V13, MinecraftVersions.UNKNOWN)
                        .parentEntry(commandSender)
                        .mojang("hasPermission")
                        .searge("m_6761_")
                        .legacySearge("func_197034_c")
                        .mcp("hasPermissionLevel")
                        .yarnIntermediary("method_9259")
                        .legacyIntermediary("method_17575");

        // CommandSender#getCommandSenderEntity() -> Entity
        var commandSender_getEntity =
                MappingEntry.builder("getEntity")
                        .parentEntry(commandSender)
                        .mojang("getEntity")
                        .searge("m_81373_")
                        .legacySearge("func_197022_f")
                        .mcp("getEntity")
                        .yarnIntermediary("method_9228")
                        .legacySearge(
                                "func_174793_f", MinecraftVersions.V7, MinecraftVersions.V12_2)
                        .legacyIntermediary(
                                "method_10788", MinecraftVersions.V7, MinecraftVersions.V12_2);

        store.registerClass(commandSender)
                .registerMethod(commandSource_hasPermissions)
                .registerMethod(commandSender_getEntity);
        logger.info("Registered CommandSourceStack");
        logger.info("|-> hasPermissions"); // Inherited from SharedSuggestionProvider
        logger.info("|-> getCommandSenderEntity");
    }
}
