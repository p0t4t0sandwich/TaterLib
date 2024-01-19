package dev.neuralnexus.taterlib.api.info;

import static dev.neuralnexus.taterlib.Utils.reflectCheck;

import java.util.Arrays;

/** Represents the type of server the server is running. */
public enum ServerType {
    // Bukkit fork
    CRAFTBUKKIT("CraftBukkit"),
    BUKKIT("Bukkit"),
    POSEIDON("Poseidon"),
    SPIGOT("Spigot"),
    PAPER("Paper"),
    FOLIA("Folia"),
    PURPUR("Purpur"),
    PUFFERFISH("Pufferfish"),

    // Bukkit + Forge Hybrids
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
    CARDBOARD("Cardboard"),
    BANNER("Banner"),

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

    // Velocity
    VELOCITY("Velocity"),

    // Vanilla
    VANILLA("Vanilla"),

    // Unknown
    UNKNOWN("Unknown");

    private final String name;

    ServerType(String name) {
        this.name = name;
    }

    /**
     * Get the current server type
     *
     * @return The current server type
     */
    public static ServerType getServerType() {
        // Time for some satan spaghetti. Hybrids are checked first, forks in descending order, and
        // then the rest.

        // Bukkit + Forge Hybrids
        if (isCrucible()) {
            return CRUCIBLE;
        } else if (isThermos()) {
            return THERMOS;
        } else if ((isKCauldron())) {
            return KCUALDRON;
        } else if (isCauldron()) {
            return CAULDRON;
        } else if (isMCPCPlusPlus()) {
            return MCPC_PLUS_PLUS;
        } else if (isMohist()) {
            return MOHIST;
        } else if (isMagma()) {
            return MAGMA;
        } else if (isArclight()) {
            return ARCLIGHT;
        } else if (isKetting()) {
            return KETTING;
        }

        // Bukkit + Fabric Hybrids
        else if (isCardboard()) {
            return CARDBOARD;
        } else if (isBanner()) {
            return BANNER;
        }

        // Bukkit
        else if (isPufferfish()) {
            return PUFFERFISH;
        } else if (isPurpur()) {
            return PURPUR;
        } else if (isFolia()) {
            return FOLIA;
        } else if (isPaper()) {
            return PAPER;
        } else if (isSpigot()) {
            return SPIGOT;
        } else if (isPoseidon()) {
            return POSEIDON;
        } else if (isCraftBukkit()) {
            return CRAFTBUKKIT;
        } else if (isBukkit()) {
            return BUKKIT;
        }

        // BungeeCord
        else if (isHexacord()) {
            return HEXACORD;
        } else if (isTravertine()) {
            return TRAVERTINE;
        } else if (isWaterfall()) {
            return WATERFALL;
        } else if (isBungeeCord()) {
            return BUNGEECORD;
        }

        // Forge
        // Before Fabric because of Sinytra Connector
        else if (isNeoForge()) {
            return NEOFORGE;
        } else if (isGoldenForge()) {
            return GOLDENFORGE;
        } else if (isForge()) {
            return FORGE;
        }

        // Fabric
        else if (isQuilt()) {
            return QUILT;
        } else if (isFabric()) {
            return FABRIC;
        }

        // Sponge
        else if (isSponge()) {
            return SPONGE;
        }

        // Velocity
        else if (isVelocity()) {
            return VELOCITY;
        }

        // Vanilla
        else if (isVanilla()) {
            return VANILLA;
        }
        return UNKNOWN;
    }

    /**
     * Get the server type from a string
     *
     * @param serverType The server type
     * @return The server type
     */
    public static ServerType from(String serverType) {
        return Arrays.stream(ServerType.values())
                .filter(s -> s.toString().equalsIgnoreCase(serverType))
                .findFirst()
                .orElse(UNKNOWN);
    }

    /** Check if the server is running Bukkit. */
    public static boolean isBukkit() {
        return reflectCheck("org.bukkit.Bukkit");
    }

    /** Check if the server is running CraftBukkit. */
    public static boolean isCraftBukkit() {
        return reflectCheck("org.bukkit.craftbukkit.CraftServer", "org.bukkit.craftbukkit.Main");
    }

    /** Check if the server is running Spigot. */
    public static boolean isSpigot() {
        return reflectCheck("org.spigotmc.CustomTimingsHandler");
    }

    /** Check if the server is running Poseidon. */
    public static boolean isPoseidon() {
        return reflectCheck("com.legacyminecraft.poseidon.PoseidonConfig");
    }

    /** Check if the server is running Paper. */
    public static boolean isPaper() {
        return reflectCheck(
                "com.destroystokyo.paper.PaperConfig", "io.papermc.paperclip.Paperclip");
    }

    /**
     * Check if the server is running Folia.
     *
     * @return True if the server is running Folia, false otherwise.
     */
    public static boolean isFolia() {
        return reflectCheck("io.papermc.paper.threadedregions.RegionizedServer");
    }

    /** Check if the server is running Purpur. */
    public static boolean isPurpur() {
        return reflectCheck("org.purpurmc.purpur.PurpurWorldConfig");
    }

    /** Check if the server is running Pufferfish. */
    public static boolean isPufferfish() {
        // TODO: Find a Pufferfish class
        return reflectCheck("not.defined");
    }

    /** Check if the server is running Cauldron. */
    public static boolean isCauldron() {
        return reflectCheck("net.minecraftforge.cauldron.CauldronConfig");
    }

    /** Check if the server is running KCauldron. */
    public static boolean isKCauldron() {
        return reflectCheck("net.minecraftforge.kcauldron.KCauldronConfig");
    }

    /** Check if the server is running Thermos. */
    public static boolean isThermos() {
        // TODO: find a Thermos class
        return reflectCheck("not.defined");
    }

    /** Check if the server is running Crucible. */
    public static boolean isCrucible() {
        return reflectCheck("io.github.crucible.CrucibleConfig");
    }

    /** Check if the server is running MCPC++. */
    public static boolean isMCPCPlusPlus() {
        // TODO: Find a MCPC++ class
        return reflectCheck("not.defined");
    }

    /** Check if the server is running Mohist. */
    public static boolean isMohist() {
        return reflectCheck("com.mohistmc.MohistMC", "com.mohistmc.MohistMCStart");
    }

    /** Check if the server is running Magma. */
    public static boolean isMagma() {
        return reflectCheck(
                "org.magmafoundation.magma.Magma", "org.magmafoundation.magma.MagmaStart");
    }

    /** Check if the server is running Arclight. */
    public static boolean isArclight() {
        return reflectCheck(
                "io.izzel.arclight.api.Arclight", "io.izzel.arclight.common.ArclightMain");
    }

    /** Check if the server is running Ketting. */
    public static boolean isKetting() {
        return reflectCheck("org.kettingpowered.ketting.core.Ketting");
    }

    /** Check if the server is running Cardboard. */
    public static boolean isCardboard() {
        return reflectCheck("org.cardboardpowered.CardboardConfig");
    }

    /** Check if the server is running Banner. */
    public static boolean isBanner() {
        return reflectCheck("com.mohistmc.banner.BannerMCStart");
    }

    /** Check if the server is running BungeeCord. */
    public static boolean isBungeeCord() {
        return reflectCheck("net.md_5.bungee.api.ProxyServer");
    }

    /** Check if the server is running Waterfall. */
    public static boolean isWaterfall() {
        return reflectCheck("io.github.waterfallmc.waterfall.conf.WaterfallConfiguration");
    }

    /** Check if the server is running Travertine. */
    public static boolean isTravertine() {
        return reflectCheck("io.github.waterfallmc.travertine.protocol.MultiVersionPacketV17");
    }

    /** Check if the server is running Hexacord. */
    public static boolean isHexacord() {
        // TODO: Find a Hexacord class
        return reflectCheck("not.defined");
    }

    /** Check if the server is running Fabric. */
    public static boolean isFabric() {
        return reflectCheck("net.fabricmc.loader.api.FabricLoader");
    }

    /** Check if the server is running Quilt. */
    public static boolean isQuilt() {
        return reflectCheck("net.quiltservertools.quilt.api.QuiltServer");
    }

    /** Check if the server is running Sponge. */
    public static boolean isSponge() {
        return reflectCheck("org.spongepowered.api.Sponge");
    }

    /** Check if the server is running Forge. */
    public static boolean isForge() {
        return reflectCheck(
                "net.minecraftforge.fml.loading.FMLLoader",
                "net.minecraftforge.common.ForgeVersion");
    }

    /** Check if the server is running GoldenForge. */
    public static boolean isGoldenForge() {
        // TODO: Find a GoldenForge class
        return reflectCheck("not.defined");
    }

    /** Check if the server is running NeoForge. */
    public static boolean isNeoForge() {
        return reflectCheck("net.neoforged.neoforge.common.NeoForge");
    }

    /** Check if the server is running Velocity. */
    public static boolean isVelocity() {
        return reflectCheck("com.velocitypowered.api.proxy.ProxyServer");
    }

    /** Check if the server is running Vanilla. */
    public static boolean isVanilla() {
        return reflectCheck("net.minecraft.server.MinecraftServer");
    }

    /**
     * Get the name of the server type
     *
     * @return The name of the server type
     */
    public String getName() {
        return this.name;
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
     * @param serverType The server type to check for.
     * @return True if the server is running the specified server type, false otherwise.
     */
    public boolean is(ServerType... serverType) {
        return Arrays.asList(serverType).contains(this);
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
     * Check if the server is running a fork of Forge.
     *
     * @return True if the server is running a fork of Forge, false otherwise.
     */
    public boolean isForgeBased() {
        return this.is(FORGE, NEOFORGE, GOLDENFORGE) || this.isForgeHybrid();
    }

    /**
     * Check if the server is running a Fabric hybrid.
     *
     * @return True if the server is running a Fabric hybrid, false otherwise.
     */
    public boolean isFabricHybrid() {
        return this.is(CARDBOARD, BANNER);
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
        return this.isForgeHybrid() || this.isFabricHybrid();
    }

    /**
     * Check if the server is running a fork of Sponge.
     *
     * @return True if the server is running a fork of Sponge, false otherwise.
     */
    public boolean isSpongeBased() {
        return this.is(SPONGE);
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
