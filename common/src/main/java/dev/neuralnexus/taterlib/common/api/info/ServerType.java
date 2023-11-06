package dev.neuralnexus.taterlib.common.api.info;

/**
 * Represents the type of server the server is running.
 */
public enum ServerType {
    // Bukkit fork
    BUKKIT("Bukkit"),
    CRAFTBUKKIT("CraftBukkit"),
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
     * Get the name of the server type
     * @return The name of the server type
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the name of the server type
     * @return The name of the server type
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Get the current server type
     * @return The current server type
     */
    public static ServerType getServerType() {
        // Time for some satan spaghetti. Hybrids are checked first, forks in descending order, and then the rest.

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

        // Forge
        else if (isNeoForge()) {
            return NEOFORGE;
        } else if (isGoldenForge()) {
            return GOLDENFORGE;
        } else if (isForge()) {
            return FORGE;
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
     * Check if the server is running Bukkit.
     */
    public static boolean isBukkit() {
        try {
            Class.forName("org.bukkit.Bukkit");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running CraftBukkit.
     */
    public static boolean isCraftBukkit() {
        try {
            Class.forName("org.bukkit.craftbukkit.CraftServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Spigot.
     */
    public static boolean isSpigot() {
        try {
            Class.forName("org.spigotmc.CustomTimingsHandler");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Poseidon.
     */
    public static boolean isPoseidon() {
        try {
            Class.forName("com.legacyminecraft.poseidon.PoseidonConfig");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Paper.
     */
    public static boolean isPaper() {
        try {
            Class.forName("com.destroystokyo.paper.PaperConfig");
            return true;
        } catch (ClassNotFoundException ignored) {}
        try {
            Class.forName("io.papermc.paperclip.Paperclip");
            return true;
        } catch (ClassNotFoundException ignored) {}
        return false;
    }

    /**
     * Check if the server is running Folia.
     * @return True if the server is running Folia, false otherwise.
     */
    public static boolean isFolia() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Purpur.
     */
    public static boolean isPurpur() {
        try {
            Class.forName("org.purpurmc.purpur.PurpurWorldConfig");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Pufferfish.
     */
    public static boolean isPufferfish() {
        try {
            // TODO: Find a Pufferfish class
            Class.forName("not.defined");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Cauldron.
     */
    public static boolean isCauldron() {
        try {
            Class.forName("net.minecraftforge.cauldron.CauldronConfig");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running KCauldron.
     */
    public static boolean isKCauldron() {
        try {
            Class.forName("net.minecraftforge.kcauldron.KCauldronConfig");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Thermos.
     */
    public static boolean isThermos() {
        try {
            // TODO: find a Thermos class
            Class.forName("not.defined");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Crucible.
     */
    public static boolean isCrucible() {
        try {
            Class.forName("io.github.crucible.CrucibleConfig");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running MCPC++.
     */
    public static boolean isMCPCPlusPlus() {
        try {
            // TODO: Find a MCPC++ class
            Class.forName("not.defined");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Mohist.
     */
    public static boolean isMohist() {
        try {
            Class.forName("com.mohistmc.MohistMCStart");
            return true;
        } catch (ClassNotFoundException ignored) {}
        try {
            Class.forName("com.mohistmc.MohistMC");
            return true;
        } catch (ClassNotFoundException ignored) {}
        return false;
    }

    /**
     * Check if the server is running Magma.
     */
    public static boolean isMagma() {
        try {
            Class.forName("org.magmafoundation.magma.Magma");
            return true;
        } catch (ClassNotFoundException ignored) {}
        try {
            Class.forName("org.magmafoundation.magma.MagmaStart");
            return true;
        } catch (ClassNotFoundException ignored) {}
        return false;
    }

    /**
     * Check if the server is running Arclight.
     */
    public static boolean isArclight() {
        try {
            Class.forName("io.izzel.arclight.common.ArclightMain");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Cardboard.
     */
    public static boolean isCardboard() {
        try {
            Class.forName("org.cardboardpowered.CardboardConfig");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Banner.
     */
    public static boolean isBanner() {
        try {
            Class.forName("com.mohistmc.banner.BannerMCStart");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running BungeeCord.
     */
    public static boolean isBungeeCord() {
        try {
            Class.forName("net.md_5.bungee.api.ProxyServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Waterfall.
     */
    public static boolean isWaterfall() {
        try {
            Class.forName("io.github.waterfallmc.waterfall.conf.WaterfallConfiguration");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Travertine.
     */
    public static boolean isTravertine() {
        try {
            Class.forName("io.github.waterfallmc.travertine.protocol.MultiVersionPacketV17");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Hexacord.
     */
    public static boolean isHexacord() {
        try {
            // TODO: Find a Hexacord class
            Class.forName("not.defined");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Fabric.
     */
    public static boolean isFabric() {
        try {
            Class.forName("net.fabricmc.loader.api.FabricLoader");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Quilt.
     */
    public static boolean isQuilt() {
        try {
            Class.forName("net.quiltservertools.quilt.api.QuiltServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Sponge.
     */
    public static boolean isSponge() {
        try {
            Class.forName("org.spongepowered.api.Sponge");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Forge.
     */
    public static boolean isForge() {
        try {
            Class.forName("net.minecraftforge.common.ForgeVersion");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running GoldenForge.
     */
    public static boolean isGoldenForge() {
        try {
            // TODO: Find a GoldenForge class
            Class.forName("not.defined");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running NeoForge.
     */
    public static boolean isNeoForge() {
        try {
            Class.forName("net.neoforged.neoforge.common.NeoForge");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Velocity.
     */
    public static boolean isVelocity() {
        try {
            Class.forName("com.velocitypowered.api.proxy.ProxyServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Vanilla.
     */
    public static boolean isVanilla() {
        try {
            Class.forName("net.minecraft.server.MinecraftServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
