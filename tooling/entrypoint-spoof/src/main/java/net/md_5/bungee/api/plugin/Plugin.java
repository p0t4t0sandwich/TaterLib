package net.md_5.bungee.api.plugin;

import java.util.logging.Logger;

/** Fake Bungee Plugin class to simplify the creation of entrypoints. */
public class Plugin {
    public Logger getLogger() {
        return null;
    }

    public void onLoad() {}

    public void onEnable() {}

    public void onDisable() {}

    final void init(PluginDescription description) {}

    public PluginDescription getDescription() {
        return new PluginDescription();
    }
}
