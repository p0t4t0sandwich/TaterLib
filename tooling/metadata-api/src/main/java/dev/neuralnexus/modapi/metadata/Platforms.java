/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata;

import static dev.neuralnexus.modapi.metadata.impl.util.ReflectionUtil.checkForClass;

import dev.neuralnexus.modapi.metadata.impl.platform.Bukkit;
import dev.neuralnexus.modapi.metadata.impl.platform.BungeeCord;
import dev.neuralnexus.modapi.metadata.impl.platform.Fabric;
import dev.neuralnexus.modapi.metadata.impl.platform.Forge;
import dev.neuralnexus.modapi.metadata.impl.platform.Hybrid;
import dev.neuralnexus.modapi.metadata.impl.platform.Sponge;
import dev.neuralnexus.modapi.metadata.impl.platform.Vanilla;
import dev.neuralnexus.modapi.metadata.impl.platform.Velocity;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.BungeeCordMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.FabricMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.NeoForgeMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.VanillaMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.VelocityMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.bukkit.BukkitMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.forge.ForgeData;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.sponge.SpongeData;

import java.util.List;
import java.util.Optional;

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
            platforms = detectPlatforms(true);
        }
        return platforms;
    }

    /**
     * Detects all platforms that are available. Doesn't need to be called manually, unless somehow
     * it was called before all platforms were loaded.
     *
     * @return A list of detected platforms
     */
    public static List<Platform> detectPlatforms(boolean force) {
        if (!force && platforms != null && !platforms.isEmpty()) {
            return platforms;
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

    public static boolean isFabric() {
        return get().contains(FABRIC);
    }

    public static boolean isQuilt() {
        return get().contains(QUILT);
    }

    public static boolean isFabricHybrid() {
        return get().contains(BUKKIT) && get().contains(FABRIC);
    }

    public static boolean isForge() {
        return get().contains(FORGE);
    }

    public static boolean isForgeHybrid() {
        return get().contains(BUKKIT) && get().contains(FORGE);
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

    static final class Meta {
        /**
         * Get the metadata for the specified platform
         *
         * @param platform The Platform
         * @return The Platform's metadata
         */
        public static Optional<Platform.Meta> lookup(Platform platform) {
            if (platform.isNeoForge()) {
                return Optional.of(new NeoForgeMeta());
            } else if (platform.isForge()) {
                return Optional.ofNullable(ForgeData.create());
            } else if (platform.isFabric()) {
                return Optional.of(new FabricMeta());
            } else if (platform.isSponge()) {
                return Optional.ofNullable(SpongeData.create());
            } else if (platform.isBukkit()) {
                return Optional.of(new BukkitMeta());
            } else if (platform.isBungeeCord()) {
                return Optional.of(new BungeeCordMeta());
            } else if (platform.isVelocity()) {
                return Optional.of(new VelocityMeta());
            } else if (checkForClass("org.spongepowered.asm.service.MixinService")) {
                return Optional.of(new VanillaMeta());
            }
            return Optional.empty();
        }

        /**
         * Get the metadata for the primary platform
         *
         * @return The Platform's metadata
         */
        public static List<Platform.Meta> lookupAll() {
            return get().stream()
                    .map(Meta::lookup)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
        }
    }
}
