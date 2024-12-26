package dev.neuralnexus.modapi.metadata.enums;

import dev.neuralnexus.modapi.metadata.Platforms;

/**
 * Enum wrapper for use with annotations
 */
public enum Platform {
    UNKNOWN(Platforms.UNKNOWN),

    BUKKIT(Platforms.BUKKIT),
    POSEIDON(Platforms.POSEIDON),
    SPIGOT(Platforms.SPIGOT),
    PAPER(Platforms.PAPER),
    FOLIA(Platforms.FOLIA),
    PUFFERFISH(Platforms.PUFFERFISH),
    PURPUR(Platforms.PURPUR),

    BUNGEECORD(Platforms.BUNGEECORD),
    WATERFALL(Platforms.WATERFALL),
    TRAVERTINE(Platforms.TRAVERTINE),
    LIGHTFALL(Platforms.LIGHTFALL),

    FABRIC(Platforms.FABRIC),
    QUILT(Platforms.QUILT),
    CARDBOARD(Platforms.CARDBOARD),
    BANNER(Platforms.BANNER),

    FORGE(Platforms.FORGE),
    GOLDENFORGE(Platforms.GOLDENFORGE),
    NEOFORGE(Platforms.NEOFORGE),
    MCPCPLUSPLUS(Platforms.MCPCPLUSPLUS),
    CAULDRON(Platforms.CAULDRON),
    KCAULDRON(Platforms.KCAULDRON),
    THERMOS(Platforms.THERMOS),
    CRUCIBLE(Platforms.CRUCIBLE),
    MAGMA(Platforms.MAGMA),
    KETTING(Platforms.KETTING),

    MOHIST(Platforms.MOHIST),
    ARCLIGHT(Platforms.ARCLIGHT),

    SPONGE(Platforms.SPONGE),

    VANILLA(Platforms.VANILLA),

    VELOCITY(Platforms.VELOCITY);

    private final dev.neuralnexus.modapi.metadata.Platform ref;

    Platform(dev.neuralnexus.modapi.metadata.Platform ref) {
        this.ref = ref;
    }

    /**
     * Get the reference to the platform
     *
     * @return The platform reference
     */
    public dev.neuralnexus.modapi.metadata.Platform ref() {
        return this.ref;
    }
}
