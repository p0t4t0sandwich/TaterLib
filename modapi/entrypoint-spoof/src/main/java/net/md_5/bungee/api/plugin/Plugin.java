/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.md_5.bungee.api.plugin;

import java.io.File;
import java.util.logging.Logger;

/** Fake Bungee Plugin class to simplify the creation of entrypoints. */
public class Plugin {
    private File file;

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

    public File getFile() {
        return this.file;
    }
}
