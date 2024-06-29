/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.loader.impl;

import dev.neuralnexus.taterlib.api.PlatformData;
import dev.neuralnexus.taterlib.api.impl.metadata.PlatformDataImpl;
import dev.neuralnexus.taterlib.loader.Loader;
import dev.neuralnexus.taterlib.plugin.ModuleLoader;
import dev.neuralnexus.taterlib.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Loader entry point. */
public class LoaderImpl implements Loader {
    public static final String PROJECT_NAME = "TaterLib";
    public static final String PROJECT_ID = "taterlib";
    public static final String PROJECT_VERSION = "1.1.1-SNAPSHOT";
    public static final String PROJECT_AUTHORS = "p0t4t0sandwich";
    public static final String PROJECT_DESCRIPTION =
            "A cross API code library that allows developers to write code that works across multiple modding platforms, and across a wide range of Minecraft versions, all with one JAR file. If TaterLib runs on it, so can your plugin/mod.";
    public static final String PROJECT_URL = "https://github.com/p0t4t0sandwich/TaterLib";
    private static final PlatformData platformData = new PlatformDataImpl();
    private static Loader instance = null;
    private final Object plugin;
    private final Object pluginServer;
    private final Object pluginLogger;
    private final List<Plugin> plugins = new ArrayList<>();

    public LoaderImpl(Object plugin, Object pluginServer, Object pluginLogger) {
        instance = this;
        this.plugin = plugin;
        this.pluginServer = pluginServer;
        this.pluginLogger = pluginLogger;
    }

    public static Loader getInstance() {
        return instance;
    }

    @Override
    public PlatformData platformData() {
        return platformData;
    }

    @Override
    public Object plugin() {
        return plugin;
    }

    @Override
    public Object server() {
        return pluginServer;
    }

    @Override
    public Object logger() {
        return pluginLogger;
    }

    @Override
    public List<Plugin> plugins() {
        return plugins;
    }

    @Override
    public Map<String, ModuleLoader> pluginModules() {
        return new HashMap<>();
    }
}
