/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi;

import static dev.neuralnexus.taterapi.utils.ReflectionUtil.checkForClass;

import java.util.Arrays;

/** Represents the type of server the server is running. */
public enum Platform {
    // Bukkit fork
    CRAFTBUKKIT("CraftBukkit"),
    BUKKIT("Bukkit"),
    POSEIDON("Poseidon"),
    SPIGOT("Spigot"),
    PAPER("Paper"),
    FOLIA("Folia"),
    PURPUR("Purpur"),
    PUFFERFISH("Pufferfish"),

    // Bukkit + NeoForge Hybrids
    NEOFORGE_HYBRID("NeoForge Hybrid"),
    MOHIST_NEO("Mohist Neo"),
    ARCLIGHT_NEO("Arclight Neo"),

    // Bukkit + Forge Hybrids
    FORGE_HYBRID("Forge Hybrid"),
    CAULDRON("Cauldron"),
    KCUALDRON("KCauldron"),
    THERMOS("Thermos"),
    CRUCIBLE("Crucible"),
    MCPC_PLUS_PLUS("MCPC++"),
    MOHIST("Mohist"),
    MAGMA("Magma"),
    ARCLIGHT("Arclight"),
    KETTING("Ketting"),

    // Bukkit + Fabric Hybrids
    FABRIC_HYBRID("Fabric Hybrid"),
    CARDBOARD("Cardboard"),
    BANNER("Banner"),
    ARCLIGHT_FABRIC("Arclight Fabric"),

    // BungeeCord
    BUNGEECORD("BungeeCord"),
    WATERFALL("Waterfall"),
    TRAVERTINE("Travertine"),
    HEXACORD("Hexacord"),

    // Fabric
    FABRIC("Fabric"),
    QUILT("Quilt"),

    // Forge
    FORGE("Forge"),
    GOLDENFORGE("GoldenForge"),
    NEOFORGE("NeoForge"),

    // Sponge
    SPONGE("Sponge"),
    SPONGE_FORGE("SpongeForge"),
    SPONGE_VANILLA("SpongeVanilla"),

    // Velocity
    VELOCITY("Velocity"),

    // Vanilla
    VANILLA("Vanilla"),

    // Unknown
    UNKNOWN("Unknown");

    private static Platform platform = UNKNOWN;
    private final String name;

    Platform(String name) {
        this.name = name;
    }

    /**
     * Get the current server type
     *
     * @return The current server type
     */
    public static Platform get() {
        if (platform != UNKNOWN) {
            return platform;
        }

        // Time for some satan spaghetti. Hybrids are checked first, forks in descending order, and
        // then the rest.

        // Bukkit + Forge Hybrids
        if (isCrucible()) {
            platform = CRUCIBLE;
        } else if (isThermos()) {
            platform = THERMOS;
        } else if ((isKCauldron())) {
            platform = KCUALDRON;
        } else if (isCauldron()) {
            platform = CAULDRON;
        } else if (isMCPCPlusPlus()) {
            platform = MCPC_PLUS_PLUS;
        } else if (isMohist()) {
            // Add NeoForge Hybrid check
            if (isNeoForge()) {
                platform = MOHIST_NEO;
            } else {
                platform = MOHIST;
            }
        } else if (isMagma()) {
            platform = MAGMA;
        } else if (isArclight()) {
            // Add NeoForge Hybrid check
            if (isNeoForge()) {
                platform = ARCLIGHT_NEO;
            } else if (isFabric()) {
                platform = ARCLIGHT_FABRIC;
            } else {
                platform = ARCLIGHT;
            }
        } else if (isKetting()) {
            platform = KETTING;
        }

        // Bukkit + Fabric Hybrids
        else if (isCardboard()) {
            platform = CARDBOARD;
        } else if (isBanner()) {
            platform = BANNER;
        }

        // Bukkit
        else if (isPufferfish()) {
            platform = PUFFERFISH;
        } else if (isPurpur()) {
            platform = PURPUR;
        } else if (isFolia()) {
            platform = FOLIA;
        } else if (isPaper()) {
            platform = PAPER;
        } else if (isSpigot()) {
            platform = SPIGOT;
        } else if (isPoseidon()) {
            platform = POSEIDON;
        } else if (isCraftBukkit()) {
            platform = CRAFTBUKKIT;
        } else if (isBukkit()) {
            platform = BUKKIT;
        }

        // BungeeCord
        else if (isHexacord()) {
            platform = HEXACORD;
        } else if (isTravertine()) {
            platform = TRAVERTINE;
        } else if (isWaterfall()) {
            platform = WATERFALL;
        } else if (isBungeeCord()) {
            platform = BUNGEECORD;
        }

        // Forge
        // Before Fabric because of Sinytra Connector
        else if (isNeoForge()) {
            platform = NEOFORGE;
        } else if (isGoldenForge()) {
            platform = GOLDENFORGE;
        } else if (isSponge() && isForge()) {
            platform = SPONGE_FORGE;
        } else if (isForge()) {
            platform = FORGE;
        }

        // Fabric
        else if (isQuilt()) {
            platform = QUILT;
        } else if (isFabric()) {
            platform = FABRIC;
        }

        // Sponge
        else if (isSponge() && !isForge()) {
            platform = SPONGE_VANILLA;
        }

        // Velocity
        else if (isVelocity()) {
            platform = VELOCITY;
        }

        // Vanilla
        else if (isVanilla()) {
            platform = VANILLA;
        }

        return platform;
    }

    /**
     * Get the server type from a string
     *
     * @param serverType The server type
     * @return The server type
     */
    public static Platform from(String serverType) {
        return Arrays.stream(Platform.values())
                .filter(s -> s.toString().equalsIgnoreCase(serverType))
                .findFirst()
                .orElse(UNKNOWN);
    }

    /** Check if the server is running Bukkit. */
    public static boolean isBukkit() {
        return checkForClass("org.bukkit.Bukkit");
    }

    /** Check if the server is running CraftBukkit. */
    public static boolean isCraftBukkit() {
        return checkForClass("org.bukkit.craftbukkit.CraftServer", "org.bukkit.craftbukkit.Main");
    }

    /** Check if the server is running Spigot. */
    public static boolean isSpigot() {
        return checkForClass("org.spigotmc.CustomTimingsHandler");
    }

    /** Check if the server is running Poseidon. */
    public static boolean isPoseidon() {
        return checkForClass("com.legacyminecraft.poseidon.PoseidonConfig");
    }

    /** Check if the server is running Paper. */
    public static boolean isPaper() {
        return checkForClass(
                "com.destroystokyo.paper.PaperConfig", "io.papermc.paperclip.Paperclip");
    }

    /**
     * Check if the server is running Folia.
     *
     * @return True if the server is running Folia, false otherwise.
     */
    public static boolean isFolia() {
        return checkForClass("io.papermc.paper.threadedregions.RegionizedServer");
    }

    /** Check if the server is running Purpur. */
    public static boolean isPurpur() {
        return checkForClass("org.purpurmc.purpur.PurpurWorldConfig");
    }

    /** Check if the server is running Pufferfish. */
    public static boolean isPufferfish() {
        // TODO: Find a Pufferfish class
        return checkForClass("not.defined");
    }

    /** Check if the server is running Cauldron. */
    public static boolean isCauldron() {
        return checkForClass("net.minecraftforge.cauldron.CauldronConfig");
    }

    /** Check if the server is running KCauldron. */
    public static boolean isKCauldron() {
        return checkForClass("net.minecraftforge.kcauldron.KCauldronConfig");
    }

    /** Check if the server is running Thermos. */
    public static boolean isThermos() {
        // TODO: find a Thermos class
        return checkForClass("not.defined");
    }

    /** Check if the server is running Crucible. */
    public static boolean isCrucible() {
        return checkForClass("io.github.crucible.CrucibleConfig");
    }

    /** Check if the server is running MCPC++. */
    public static boolean isMCPCPlusPlus() {
        // TODO: Find a MCPC++ class
        return checkForClass("not.defined");
    }

    /** Check if the server is running Mohist. */
    public static boolean isMohist() {
        return checkForClass("com.mohistmc.MohistMC", "com.mohistmc.MohistMCStart");
    }

    /** Check if the server is running Magma. */
    public static boolean isMagma() {
        return checkForClass(
                "org.magmafoundation.magma.Magma", "org.magmafoundation.magma.MagmaStart");
    }

    /** Check if the server is running Arclight. */
    public static boolean isArclight() {
        return checkForClass(
                "io.izzel.arclight.api.Arclight", "io.izzel.arclight.common.ArclightMain");
    }

    /** Check if the server is running Ketting. */
    public static boolean isKetting() {
        return checkForClass("org.kettingpowered.ketting.core.Ketting");
    }

    /** Check if the server is running Cardboard. */
    public static boolean isCardboard() {
        return checkForClass("org.cardboardpowered.CardboardConfig");
    }

    /** Check if the server is running Banner. */
    public static boolean isBanner() {
        return checkForClass("com.mohistmc.banner.BannerMCStart");
    }

    /** Check if the server is running BungeeCord. */
    public static boolean isBungeeCord() {
        return checkForClass("net.md_5.bungee.api.ProxyServer");
    }

    /** Check if the server is running Waterfall. */
    public static boolean isWaterfall() {
        return checkForClass("io.github.waterfallmc.waterfall.conf.WaterfallConfiguration");
    }

    /** Check if the server is running Travertine. */
    public static boolean isTravertine() {
        return checkForClass("io.github.waterfallmc.travertine.protocol.MultiVersionPacketV17");
    }

    /** Check if the server is running Hexacord. */
    public static boolean isHexacord() {
        // TODO: Find a Hexacord class
        return checkForClass("not.defined");
    }

    /** Check if the server is running Fabric. */
    public static boolean isFabric() {
        return checkForClass("net.fabricmc.loader.api.FabricLoader");
    }

    /** Check if the server is running Quilt. */
    public static boolean isQuilt() {
        return checkForClass("net.quiltservertools.quilt.api.QuiltServer");
    }

    /** Check if the server is running Sponge. */
    public static boolean isSponge() {
        return checkForClass("org.spongepowered.api.Sponge");
    }

    /** Check if the server is running Forge. */
    public static boolean isForge() {
        return checkForClass(
                "net.minecraftforge.fml.loading.FMLLoader",
                "net.minecraftforge.common.ForgeVersion");
    }

    /** Check if the server is running GoldenForge. */
    public static boolean isGoldenForge() {
        return checkForClass("org.goldenforgelauncher.GoldenForgeEntryPoint");
    }

    /** Check if the server is running NeoForge. */
    public static boolean isNeoForge() {
        return checkForClass("net.neoforged.neoforge.common.NeoForge");
    }

    /** Check if the server is running Velocity. */
    public static boolean isVelocity() {
        return checkForClass("com.velocitypowered.api.proxy.ProxyServer");
    }

    /** Check if the server is running Vanilla. */
    public static boolean isVanilla() {
        return checkForClass("net.minecraft.server.MinecraftServer");
    }

    /**
     * Get the name of the server type
     *
     * @return The name of the server type
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Check if the server is running a specific server type.
     *
     * @param serverType The server type to check for.
     * @return True if the server is running the specified server type, false otherwise.
     */
    public boolean is(String... serverType) {
        return Arrays.stream(serverType).anyMatch(this.name::equalsIgnoreCase);
    }

    /**
     * Check if the server is running a specific server type.
     *
     * @param platform The server type to check for.
     * @return True if the server is running the specified server type, false otherwise.
     */
    public boolean is(Platform... platform) {
        return Arrays.asList(platform).contains(this);
    }

    /**
     * Check if the server is running a Bukkit fork.
     *
     * @return True if the server is running a Bukkit fork, false otherwise.
     */
    public boolean isBukkitBased() {
        return this.is(BUKKIT, CRAFTBUKKIT, POSEIDON) || this.isSpigotBased();
    }

    /**
     * Check if the server is running a Spigot fork.
     *
     * @return True if the server is running a Spigot fork, false otherwise.
     */
    public boolean isSpigotBased() {
        return this.is(SPIGOT) || this.isPaperBased() || this.isHybrid();
    }

    /**
     * Check if the server is running a fork of Paper.
     *
     * @return True if the server is running a fork of Paper, false otherwise.
     */
    public boolean isPaperBased() {
        return this.is(PAPER, FOLIA, PURPUR, PUFFERFISH);
    }

    /**
     * Check if the server is running a fork of BungeeCord.
     *
     * @return True if the server is running a fork of BungeeCord, false otherwise.
     */
    public boolean isBungeeCordBased() {
        return this.is(BUNGEECORD, WATERFALL, TRAVERTINE, HEXACORD);
    }

    /**
     * Check if the server is running a Forge hybrid.
     *
     * @return True if the server is running a Forge hybrid, false otherwise.
     */
    public boolean isForgeHybrid() {
        return this.is(
                CAULDRON,
                KCUALDRON,
                THERMOS,
                CRUCIBLE,
                MCPC_PLUS_PLUS,
                MOHIST,
                MAGMA,
                ARCLIGHT,
                KETTING);
    }

    /**
     * Check if the server is running a NeoForge hybrid.
     *
     * @return True if the server is running a NeoForge hybrid, false otherwise.
     */
    public boolean isNeoForgeHybrid() {
        return this.is(MOHIST_NEO, ARCLIGHT_NEO);
    }

    /**
     * Check if the server is running a fork of Forge.
     *
     * @return True if the server is running a fork of Forge, false otherwise.
     */
    public boolean isForgeBased() {
        return this.is(FORGE, SPONGE_FORGE, NEOFORGE, GOLDENFORGE) || this.isForgeHybrid();
    }

    /**
     * Check if the server is running a fork of NeoForge.
     *
     * @return True if the server is running a fork of NeoForge, false otherwise.
     */
    public boolean isNeoForgeBased() {
        return this.is(NEOFORGE) || this.isNeoForgeHybrid();
    }

    /**
     * Check if the server is running a Fabric hybrid.
     *
     * @return True if the server is running a Fabric hybrid, false otherwise.
     */
    public boolean isFabricHybrid() {
        return this.is(CARDBOARD, BANNER, ARCLIGHT_FABRIC);
    }

    /**
     * Check if the server is running a fork of Fabric.
     *
     * @return True if the server is running a fork of Fabric, false otherwise.
     */
    public boolean isFabricBased() {
        return this.is(FABRIC, QUILT) || this.isFabricHybrid();
    }

    /**
     * Check if the server is running on a hybrid.
     *
     * @return True if the server is running on a hybrid, false otherwise.
     */
    public boolean isHybrid() {
        return this.isForgeHybrid() || this.isNeoForgeHybrid() || this.isFabricHybrid();
    }

    /**
     * Check if the server is running a fork of Sponge.
     *
     * @return True if the server is running a fork of Sponge, false otherwise.
     */
    public boolean isSpongeBased() {
        return this.is(SPONGE, SPONGE_FORGE, SPONGE_VANILLA);
    }

    /**
     * Check if the server is running a fork of Velocity.
     *
     * @return True if the server is running a fork of Velocity, false otherwise.
     */
    public boolean isVelocityBased() {
        return this.is(VELOCITY);
    }

    /**
     * Check if the server is running some sort of proxy.
     *
     * @return True if the server is running some sort of proxy, false otherwise.
     */
    public boolean isProxy() {
        return this.isBungeeCordBased() || this.isVelocityBased();
    }
}
