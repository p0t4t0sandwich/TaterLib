/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform;

import dev.neuralnexus.taterapi.meta.Platform;

public interface BungeeCord {
    Platform BUNGEECORD = new PlatformImpl("BungeeCord", "net.md_5.bungee.api.ProxyServer");
    Platform WATERFALL =
            new PlatformImpl(
                    "Waterfall", "io.github.waterfallmc.waterfall.conf.WaterfallConfiguration");
    Platform TRAVERTINE =
            new PlatformImpl(
                    "Travertine",
                    "io.github.waterfallmc.travertine.protocol.MultiVersionPacketV17");
    Platform LIGHTFALL =
            new PlatformImpl("LightFall", "io.izzel.lightfall.forge.ModernForgeClientHandler");
}
