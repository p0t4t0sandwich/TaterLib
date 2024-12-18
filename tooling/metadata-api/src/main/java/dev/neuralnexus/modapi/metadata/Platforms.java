/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata;

import dev.neuralnexus.modapi.metadata.impl.platform.Bukkit;
import dev.neuralnexus.modapi.metadata.impl.platform.BungeeCord;
import dev.neuralnexus.modapi.metadata.impl.platform.Fabric;
import dev.neuralnexus.modapi.metadata.impl.platform.Forge;
import dev.neuralnexus.modapi.metadata.impl.platform.Hybrid;
import dev.neuralnexus.modapi.metadata.impl.platform.Sponge;
import dev.neuralnexus.modapi.metadata.impl.platform.Vanilla;
import dev.neuralnexus.modapi.metadata.impl.platform.Velocity;

import java.util.ArrayList;
import java.util.List;

public final class Platforms
        implements Bukkit, BungeeCord, Fabric, Forge, Hybrid, Sponge, Vanilla, Velocity {
    private static List<Platform> platforms;

    /**
     * Returns all platforms that are available.
     *
     * @return An array of all available platforms
     */
    public static List<Platform> get() {
        if (platforms == null) {
            platforms = detectPlatforms();
        }
        return platforms;
    }

    /**
     * Detects all platforms that are available.
     *
     * @return An array of all detected platforms
     */
    static ArrayList<Platform> detectPlatforms() {
        ArrayList<Platform> platforms = new ArrayList<>();

        // Bukkit
        if (PURPUR.detect()) {
            platforms.addAll(List.of(PURPUR, PUFFERFISH, PAPER, SPIGOT, BUKKIT));
        } else if (PUFFERFISH.detect()) {
            platforms.addAll(List.of(PUFFERFISH, PAPER, SPIGOT, BUKKIT));
        } else if (PAPER.detect()) {
            platforms.addAll(List.of(PAPER, SPIGOT, BUKKIT));
        } else if (SPIGOT.detect()) {
            platforms.addAll(List.of(SPIGOT, BUKKIT));
        } else if (POSEIDON.detect()) {
            platforms.addAll(List.of(POSEIDON, BUKKIT));
        } else if (BUKKIT.detect()) {
            platforms.add(BUKKIT);
        }
        if (FOLIA.detect()) {
            platforms.add(FOLIA);
        }

        // BungeeCord
        if (TRAVERTINE.detect()) {
            platforms.addAll(List.of(TRAVERTINE, WATERFALL, BUNGEECORD));
        } else if (LIGHTFALL.detect()) {
            platforms.addAll(List.of(LIGHTFALL, WATERFALL, BUNGEECORD));
        } else if (WATERFALL.detect()) {
            platforms.addAll(List.of(WATERFALL, BUNGEECORD));
        } else if (BUNGEECORD.detect()) {
            platforms.add(BUNGEECORD);
        }

        // Fabric
        if (QUILT.detect()) {
            platforms.addAll(List.of(QUILT, FABRIC));
            // Fabric Hybrids
        } else if (CARDBOARD.detect()) {
            platforms.addAll(List.of(CARDBOARD, FABRIC));
        } else if (BANNER.detect()) {
            platforms.addAll(List.of(BANNER, FABRIC));
        } else if (FABRIC.detect()) {
            platforms.add(FABRIC);
        }

        // Forge
        if (GOLDENFORGE.detect()) {
            platforms.addAll(List.of(GOLDENFORGE, FORGE));
            // Forge Hybrids
        } else if (MCPCPLUSPLUS.detect()) {
            platforms.addAll(List.of(MCPCPLUSPLUS, FORGE));
        } else if (CAULDRON.detect()) {
            platforms.addAll(List.of(CAULDRON, FORGE));
        } else if (KCAULDRON.detect()) {
            platforms.addAll(List.of(KCAULDRON, FORGE));
        } else if (THERMOS.detect()) {
            platforms.addAll(List.of(THERMOS, FORGE));
        } else if (CRUCIBLE.detect()) {
            platforms.addAll(List.of(CRUCIBLE, FORGE));
        } else if (MAGMA.detect()) {
            platforms.addAll(List.of(MAGMA, FORGE));
        } else if (KETTING.detect()) {
            platforms.addAll(List.of(KETTING, FORGE));
        } else if (FORGE.detect()) {
            platforms.add(FORGE);
        }

        if (NEOFORGE.detect()) {
            platforms.add(NEOFORGE);
        }

        // Hybrid
        if (MOHIST.detect()) {
            platforms.add(MOHIST);
        } else if (ARCLIGHT.detect()) {
            platforms.add(ARCLIGHT);
        }

        // Sponge
        if (SPONGE.detect()) {
            platforms.add(SPONGE);
        }

        // Velocity
        if (VELOCITY.detect()) {
            platforms.add(VELOCITY);
        }

        return platforms;
    }

    public static boolean isBukkit() {
        return get().contains(BUKKIT);
    }

    public static boolean isSpigot() {
        return get().contains(SPIGOT);
    }

    public static boolean isPaper() {
        return get().contains(PAPER);
    }

    public static boolean isBungeeCord() {
        return get().contains(BUNGEECORD);
    }

    public static boolean isWaterfall() {
        return get().contains(WATERFALL);
    }

    public static boolean isLightFall() {
        return get().contains(LIGHTFALL);
    }

    public static boolean isTravertine() {
        return get().contains(TRAVERTINE);
    }

    public static boolean isFabric() {
        return get().contains(FABRIC);
    }

    public static boolean isQuilt() {
        return get().contains(QUILT);
    }

    public static boolean isFabricHybrid() {
        return get().contains(BUKKIT) || get().contains(FABRIC);
    }

    public static boolean isForge() {
        return get().contains(FORGE);
    }

    public static boolean isForgeHybrid() {
        return get().contains(BUKKIT) || get().contains(FORGE);
    }

    public static boolean isNeoForge() {
        return get().contains(NEOFORGE);
    }

    public static boolean isHybrid() {
        return get().contains(MOHIST)
                || get().contains(ARCLIGHT)
                || isForgeHybrid()
                || isFabricHybrid();
    }

    public static boolean isSponge() {
        return get().contains(SPONGE);
    }

    public static boolean isSpongeForge() {
        return get().contains(SPONGE) && get().contains(FORGE);
    }

    public static boolean isSpongeFabric() {
        return get().contains(SPONGE) && get().contains(FABRIC);
    }

    public static boolean isVelocity() {
        return get().contains(VELOCITY);
    }

    public static boolean isProxy() {
        return isBungeeCord() || isVelocity();
    }

    public static boolean isMixedForgeFabric() {
        return get().contains(FORGE) && get().contains(FABRIC);
    }
}
