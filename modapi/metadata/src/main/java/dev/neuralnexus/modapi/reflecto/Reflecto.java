/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.reflecto;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ApiStatus.Experimental
public final class Reflecto {
    private static final Logger logger = Logger.create("Reflecto");
    private static final Reflecto INSTANCE = new Reflecto();
    private final Map<Class<?>, Map<String, String>> MAPPINGS = new ConcurrentHashMap<>();

    private Reflecto() {}

    public static Reflecto instance() {
        return INSTANCE;
    }

    public Reflecto register(@NotNull Class<?> owner, @NotNull ClassEntry entry)
            throws NullPointerException {
        Objects.requireNonNull(owner, "Owner cannot be null");
        Objects.requireNonNull(entry, "Entry cannot be null");
        String mapping = entry.mappings();
        if (MAPPINGS.containsKey(owner) && MAPPINGS.get(owner).containsKey(entry.name())) {
            logger.error("Failed to register mapping: " + entry.name() + " already exists");
            return this;
        }
        if (mapping == null) {
            logger.error("Failed to register mapping: " + entry.name());
            return this;
        }
        if (entry.version() != MinecraftVersions.UNKNOWN
                && !MetaAPI.instance().version().is(entry.version())) {
            logger.error("Failed to register mapping: " + entry.name() + " version mismatch");
            return this;
        }
        MAPPINGS.computeIfAbsent(owner, k -> new ConcurrentHashMap<>()).put(entry.name(), mapping);
        return this;
    }

    public Reflecto register(@NotNull Object owner, @NotNull ClassEntry entry)
            throws NullPointerException {
        Objects.requireNonNull(owner, "Owner cannot be null");
        Objects.requireNonNull(entry, "Entry cannot be null");
        return register(owner.getClass(), entry);
    }

    public String get(@NotNull Class<?> owner, @NotNull String name) throws NullPointerException {
        Objects.requireNonNull(owner, "Owner cannot be null");
        Objects.requireNonNull(name, "Name cannot be null");
        return MAPPINGS.getOrDefault(owner, new ConcurrentHashMap<>()).get(name);
    }

    public String get(@NotNull Object owner, @NotNull String name) throws NullPointerException {
        Objects.requireNonNull(owner, "Owner cannot be null");
        Objects.requireNonNull(name, "Name cannot be null");
        return get(owner.getClass(), name);
    }

    private static final Map<String, Class<?>> classCache = new ConcurrentHashMap<>();
    private static final Map<Class<?>, Map<String, Field>> fieldCache = new ConcurrentHashMap<>();
    private static final Map<Class<?>, Map<String, Method>> methodCache = new ConcurrentHashMap<>();

    public static class MappingStore {
        private Map<String, String> classMappings = new ConcurrentHashMap<>();
        private Map<String, Map<String, String>> fieldMappings = new ConcurrentHashMap<>();
        private Map<String, Map<String, Class<?>[]>> methodMappings = new ConcurrentHashMap<>();

        public MappingStore classMapping(String name, String mapping) {
            classMappings.put(name, mapping);
            return this;
        }

        public MappingStore fieldMapping(String name, String field, String mapping) {
            fieldMappings.computeIfAbsent(name, k -> new ConcurrentHashMap<>()).put(field, mapping);
            return this;
        }

        public MappingStore methodMapping(String name, String method, String mapping, Class<?>... parameters) {
            methodMappings.computeIfAbsent(name, k -> new ConcurrentHashMap<>()).put(method, parameters);
            return this;
        }
    }
}
