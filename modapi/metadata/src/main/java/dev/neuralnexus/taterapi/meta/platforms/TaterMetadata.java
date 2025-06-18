/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.platforms;

import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platform;
import dev.neuralnexus.taterapi.meta.Platforms;

public class TaterMetadata {
    private TaterMetadata() {}

    private static void init(Platform platform) {
        MetaAPI api = MetaAPI.instance();
        try {
            api.primaryPlatform();
        } catch (MetaAPI.NoPrimaryPlatformException e) {
            api.setPrimaryPlatform(platform);
        }
    }

    public static void initBukkit() {
        init(Platforms.BUKKIT);
    }

    public static void initBungeeCord() {
        init(Platforms.BUNGEECORD);
    }

    public static void initFabric() {
        init(Platforms.FABRIC);
    }

    public static void initForge() {
        init(Platforms.FORGE);
    }

    public static void initNeoForge() {
        init(Platforms.NEOFORGE);
    }

    public static void initSponge() {
        init(Platforms.SPONGE);
    }

    public static void initVelocity() {
        init(Platforms.VELOCITY);
    }
}
