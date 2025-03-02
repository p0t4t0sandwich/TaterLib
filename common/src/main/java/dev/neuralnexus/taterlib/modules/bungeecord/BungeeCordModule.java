/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.modules.bungeecord;

import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.loader.plugin.PluginModule;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.modules.bungeecord.api.BungeeMsgType;

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
            NetworkEvents.C2S_CUSTOM_PACKET.register(BungeeMsgType::Listener);
        }
    }
}
