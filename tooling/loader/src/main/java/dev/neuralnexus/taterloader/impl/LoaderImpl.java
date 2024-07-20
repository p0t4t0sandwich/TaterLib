/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterloader.impl;

import dev.neuralnexus.taterapi.metadata.PlatformData;
import dev.neuralnexus.taterloader.Loader;
import dev.neuralnexus.taterloader.plugin.ModuleLoader;
import dev.neuralnexus.taterloader.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Loader entry point. */
public class LoaderImpl implements Loader {
    public static final String PROJECT_NAME = "TaterLib";
    public static final String PROJECT_ID = "taterlib";
    public static final String PROJECT_VERSION = "1.2.0-SNAPSHOT";
    public static final String PROJECT_AUTHORS = "p0t4t0sandwich";
    public static final String PROJECT_DESCRIPTION =
            "A cross API code library that allows developers to write code that works across multiple modding platforms, and across a wide range of Minecraft versions, all with one JAR file. If TaterLib runs on it, so can your plugin/mod.";
    public static final String PROJECT_URL = "https://github.com/p0t4t0sandwich/TaterLib";
    private static final PlatformData platformData = PlatformData.instance();
    private static Loader instance = null;
    private final Object plugin;
    private final Object server;
    private final Object[] other;
    private final List<Plugin> plugins = new ArrayList<>();
    private final Map<String, ModuleLoader> pluginModules = new HashMap<>();

    public LoaderImpl(Object plugin, Object server, Object... other) {
        instance = this;
        this.plugin = plugin;
        this.server = server;
        this.other = other;
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
        return server;
    }

    @Override
    public Object[] other() {
        return other;
    }

    @Override
    public List<Plugin> plugins() {
        return plugins;
    }

    @Override
    public Map<String, ModuleLoader> pluginModules() {
        return pluginModules;
    }
}
