package dev.neuralnexus.taterlib.modules.bungeecord;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.event.api.NetworkEvents;
import dev.neuralnexus.taterlib.modules.bungeecord.api.BungeeMsgType;
import dev.neuralnexus.taterlib.plugin.PluginModule;

/** TaterLib's BungeeCord module. */
public class BungeeCordModule implements PluginModule {
    private static boolean STARTED = false;

    @Override
    public String name() {
        return "BungeeCord";
    }

    @Override
    public void start() {
        if (STARTED) {
            TaterLib.logger().info("Submodule " + name() + " has already started!");
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
    public void stop() {
        if (!STARTED) {
            TaterLib.logger().info("Submodule " + name() + " has already stopped!");
            return;
        }
        STARTED = false;
    }
}
