/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.modules.bungeecord;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.modules.bungeecord.api.BungeeMsgType;
import dev.neuralnexus.taterloader.plugin.PluginModule;

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
                    TaterAPIProvider.platform().isVelocityBased()
                            ? "bungeecord:main"
                            : "BungeeCord";
            NetworkEvents.REGISTER_PLUGIN_MESSAGES.register(e -> e.register(channel));
            NetworkEvents.C2S_CUSTOM_PACKET.register(BungeeMsgType::Listener);
        }
    }
}
