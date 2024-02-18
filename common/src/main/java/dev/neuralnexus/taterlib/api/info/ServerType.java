package dev.neuralnexus.taterlib.api.info;

import static dev.neuralnexus.taterlib.Utils.reflectCheck;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/** Represents the type of server the server is running. */
public enum ServerType {
    // Bukkit fork
    @SerializedName("CraftBukkit")
    CRAFTBUKKIT("CraftBukkit", DataFolders.PLUGINS),
    @SerializedName("Bukkit")
    BUKKIT("Bukkit", DataFolders.PLUGINS),
    @SerializedName("Poseidon")
    POSEIDON("Poseidon", DataFolders.PLUGINS),
    @SerializedName("Spigot")
    SPIGOT("Spigot", DataFolders.PLUGINS),
    @SerializedName("Paper")
    PAPER("Paper", DataFolders.PLUGINS),
    @SerializedName("Folia")
    FOLIA("Folia", DataFolders.PLUGINS),
    @SerializedName("Purpur")
    PURPUR("Purpur", DataFolders.PLUGINS),
    @SerializedName("Pufferfish")
    PUFFERFISH("Pufferfish", DataFolders.PLUGINS),

    // Bukkit + NeoForge Hybrids
    NEOFORGE_HYBRID("NeoForge Hybrid", DataFolders.HYBRID),
    @SerializedName("Mohist Neo")
    MOHIST_NEO("Mohist Neo", DataFolders.HYBRID),
    @SerializedName("Arclight Neo")
    ARCLIGHT_NEO("Arclight Neo", DataFolders.HYBRID),

    // Bukkit + Forge Hybrids
    FORGE_HYBRID("Forge Hybrid", DataFolders.HYBRID),
    @SerializedName("Cauldron")
    CAULDRON("Cauldron", DataFolders.HYBRID),
    @SerializedName("KCauldron")
    KCUALDRON("KCauldron", DataFolders.HYBRID),
    @SerializedName("Thermos")
    THERMOS("Thermos", DataFolders.HYBRID),
    @SerializedName("Crucible")
    CRUCIBLE("Crucible", DataFolders.HYBRID),
    @SerializedName("MCPC++")
    MCPC_PLUS_PLUS("MCPC++", DataFolders.HYBRID),
    @SerializedName("Mohist")
    MOHIST("Mohist", DataFolders.HYBRID),
    @SerializedName("Magma")
    MAGMA("Magma", DataFolders.HYBRID),
    @SerializedName("Arclight")
    ARCLIGHT("Arclight", DataFolders.HYBRID),
    @SerializedName("Ketting")
    KETTING("Ketting", DataFolders.HYBRID),

    // Bukkit + Fabric Hybrids
    FABRIC_HYBRID("Fabric Hybrid", DataFolders.HYBRID),
    @SerializedName("Cardboard")
    CARDBOARD("Cardboard", DataFolders.HYBRID),
    @SerializedName("Banner")
    BANNER("Banner", DataFolders.HYBRID),
    @SerializedName("Arclight Fabric")
    ARCLIGHT_FABRIC("Arclight Fabric", DataFolders.HYBRID),

    // BungeeCord
    @SerializedName("BungeeCord")
    BUNGEECORD("BungeeCord", DataFolders.PLUGINS),
    @SerializedName("Waterfall")
    WATERFALL("Waterfall", DataFolders.PLUGINS),
    @SerializedName("Travertine")
    TRAVERTINE("Travertine", DataFolders.PLUGINS),
    @SerializedName("Hexacord")
    HEXACORD("Hexacord", DataFolders.PLUGINS),

    // Fabric
    @SerializedName("Fabric")
    FABRIC("Fabric", DataFolders.MODS),
    @SerializedName("Quilt")
    QUILT("Quilt", DataFolders.MODS),

    // Forge
    @SerializedName("Forge")
    FORGE("Forge", DataFolders.MODS),
    @SerializedName("GoldenForge")
    GOLDENFORGE("GoldenForge", DataFolders.MODS),
    @SerializedName("NeoForge")
    NEOFORGE("NeoForge", DataFolders.MODS),

    // Sponge
    @SerializedName("Sponge")
    SPONGE("Sponge", DataFolders.MODS),
    @SerializedName("SpongeForge")
    SPONGE_FORGE("SpongeForge", DataFolders.MODS),
    @SerializedName("SpongeVanilla")
    SPONGE_VANILLA("SpongeVanilla", DataFolders.MODS),

    // Velocity
    @SerializedName("Velocity")
    VELOCITY("Velocity", DataFolders.PLUGINS),

    // Vanilla
    @SerializedName("Vanilla")
    VANILLA("Vanilla", DataFolders.UNKNOWN),

    // Unknown
    @SerializedName("Unknown")
    UNKNOWN("Unknown", DataFolders.UNKNOWN);

    private static ServerType serverType = UNKNOWN;
    private final String name;
    private final DataFolders dataFolders;

    ServerType(String name, DataFolders dataFolders) {
        this.name = name;
        this.dataFolders = dataFolders;
    }

    /**
     * Get the current server type
     *
     * @return The current server type
     */
    public static ServerType serverType() {
        if (serverType != UNKNOWN) {
            return serverType;
        }

        // Time for some satan spaghetti. Hybrids are checked first, forks in descending order, and
        // then the rest.

        // Bukkit + Forge Hybrids
        if (isCrucible()) {
            serverType = CRUCIBLE;
        } else if (isThermos()) {
            serverType = THERMOS;
        } else if ((isKCauldron())) {
            serverType = KCUALDRON;
        } else if (isCauldron()) {
            serverType = CAULDRON;
        } else if (isMCPCPlusPlus()) {
            serverType = MCPC_PLUS_PLUS;
        } else if (isMohist()) {
            // Add NeoForge Hybrid check
            if (isNeoForge()) {
                serverType = MOHIST_NEO;
            } else {
                serverType = MOHIST;
            }
        } else if (isMagma()) {
            serverType = MAGMA;
        } else if (isArclight()) {
            // Add NeoForge Hybrid check
            if (isNeoForge()) {
                serverType = ARCLIGHT_NEO;
            } else if (isFabric()) {
                serverType = ARCLIGHT_FABRIC;
            } else {
                serverType = ARCLIGHT;
            }
        } else if (isKetting()) {
            serverType = KETTING;
        }

        // Bukkit + Fabric Hybrids
        else if (isCardboard()) {
            serverType = CARDBOARD;
        } else if (isBanner()) {
            serverType = BANNER;
        }

        // Bukkit
        else if (isPufferfish()) {
            serverType = PUFFERFISH;
        } else if (isPurpur()) {
            serverType = PURPUR;
        } else if (isFolia()) {
            serverType = FOLIA;
        } else if (isPaper()) {
            serverType = PAPER;
        } else if (isSpigot()) {
            serverType = SPIGOT;
        } else if (isPoseidon()) {
            serverType = POSEIDON;
        } else if (isCraftBukkit()) {
            serverType = CRAFTBUKKIT;
        } else if (isBukkit()) {
            serverType = BUKKIT;
        }

        // BungeeCord
        else if (isHexacord()) {
            serverType = HEXACORD;
        } else if (isTravertine()) {
            serverType = TRAVERTINE;
        } else if (isWaterfall()) {
            serverType = WATERFALL;
        } else if (isBungeeCord()) {
            serverType = BUNGEECORD;
        }

        // Forge
        // Before Fabric because of Sinytra Connector
        else if (isNeoForge()) {
            serverType = NEOFORGE;
        } else if (isGoldenForge()) {
            serverType = GOLDENFORGE;
        } else if (isSponge() && isForge()) {
            serverType = SPONGE_FORGE;
        } else if (isForge()) {
            serverType = FORGE;
        }

        // Fabric
        else if (isQuilt()) {
            serverType = QUILT;
        } else if (isFabric()) {
            serverType = FABRIC;
        }

        // Sponge
        else if (isSponge() && !isForge()) {
            serverType = SPONGE_VANILLA;
        }

        // Velocity
        else if (isVelocity()) {
            serverType = VELOCITY;
        }

        // Vanilla
        else if (isVanilla()) {
            serverType = VANILLA;
        }

        return serverType;
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
     * Get the data folders for the server type
     *
     * @return The data folders for the server type
     */
    public DataFolders dataFolders() {
        return serverType().dataFolders;
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

    public enum DataFolders {
        PLUGINS("plugins", "plugins"),
        MODS("mods", "config"),
        HYBRID("mods", "config"),
        UNKNOWN("", "");

        private final String modFolder;
        private final String configFolder;

        DataFolders(String modFolder, String configFolder) {
            this.modFolder = modFolder;
            this.configFolder = configFolder;
        }

        /**
         * Get the mod folder
         *
         * @return The mod folder
         */
        public String modFolder() {
            return modFolder;
        }

        /**
         * Get the config folder
         *
         * @return The config folder
         */
        public String configFolder() {
            return configFolder;
        }
    }
}
