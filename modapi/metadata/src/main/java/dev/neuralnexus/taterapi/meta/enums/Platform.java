/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.enums;

import dev.neuralnexus.taterapi.meta.Platforms;

/** Enum wrapper for use with annotations */
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
    MOHIST(Platforms.MOHIST),
    MAGMA(Platforms.MAGMA),
    KETTING(Platforms.KETTING),

    YOUER(Platforms.YOUER),

    ARCLIGHT(Platforms.ARCLIGHT),

    SPONGE(Platforms.SPONGE),

    VANILLA(Platforms.VANILLA),

    VELOCITY(Platforms.VELOCITY);

    private final dev.neuralnexus.taterapi.meta.Platform ref;

    Platform(dev.neuralnexus.taterapi.meta.Platform ref) {
        this.ref = ref;
    }

    /**
     * Get the reference to the platform
     *
     * @return The platform reference
     */
    public dev.neuralnexus.taterapi.meta.Platform ref() {
        return this.ref;
    }
}
