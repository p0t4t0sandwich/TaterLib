/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.modules.bungeecord;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.event.api.NetworkEvents;
import dev.neuralnexus.taterlib.modules.bungeecord.api.BungeeMsgType;
import dev.neuralnexus.taterlib.plugin.PluginModule;

/** TaterLib's BungeeCord module. */
public class BungeeCordModule implements PluginModule {
    private static boolean STARTED = false;

    @Override
    public String id() {
        return "BungeeCord";
    }

    @Override
    public void onEnable() {
        if (STARTED) {
            TaterLib.logger().info("Submodule " + id() + " has already started!");
            return;
        }
        STARTED = true;

        if (!TaterLib.hasReloaded()) {
            NetworkEvents.REGISTER_PLUGIN_MESSAGES.register(
                    e -> e.registerPluginChannel("BungeeCord"));
            NetworkEvents.PLUGIN_MESSAGE.register(BungeeMsgType::Listener);
        }
    }

    @Override
    public void onDisable() {
        if (!STARTED) {
            TaterLib.logger().info("Submodule " + id() + " has already stopped!");
            return;
        }
        STARTED = false;
    }
}
