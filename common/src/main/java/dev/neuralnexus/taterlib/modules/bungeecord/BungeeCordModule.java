/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.modules.bungeecord;

import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.loader.plugin.PluginModule;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterlib.TaterLib;

/** TaterLib's BungeeCord module. */
public class BungeeCordModule implements PluginModule {
    @Override
    public String id() {
        return "BungeeCord";
    }

    @Override
    public void onEnable() {
        if (!TaterLib.hasReloaded()) {
            String channel =
                    MetaAPI.instance().isPlatformPresent(Platforms.VELOCITY)
                            ? "bungeecord:main"
                            : "BungeeCord";
            NetworkEvents.REGISTER_CHANNELS.register(e -> e.register(channel));
        }
    }
}
