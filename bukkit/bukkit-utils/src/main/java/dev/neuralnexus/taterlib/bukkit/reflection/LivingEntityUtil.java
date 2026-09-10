/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.bukkit.reflection;

import static dev.neuralnexus.taterlib.bukkit.reflection.Utils.cbClass;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.meta.Constraint;
import dev.neuralnexus.taterapi.meta.MinecraftVersions;

import org.jspecify.annotations.NonNull;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class LivingEntityUtil {
    private static MethodHandle GET_HANDLE;
    private static MethodHandle GET_ABSORPTION;
    private static MethodHandle SET_ABSORPTION;
    private static MethodHandle GET_HEALTH;
    private static MethodHandle GET_MAX_HEALTH;

    public static Supplier<Double> helper$absorption(
            final org.bukkit.entity.@NonNull LivingEntity entity) {
        // v1.6.4:
        //   (float) ((CraftLivingEntity) entity).getHandle().bn();
        // v1.7.10 - v1.13.2:
        //   (float) ((CraftLivingEntity) entity).getHandle().getAbsorptionHearts();
        return () -> {
            try {
                if (GET_HANDLE == null) {
                    final MethodHandles.Lookup lookup = MethodHandles.publicLookup();
                    final Class<?> clazz = Class.forName(cbClass("CraftLivingEntity"));
                    GET_HANDLE = lookup.unreflect(clazz.getMethod("getHandle"));
                }
                final Object handle = GET_HANDLE.invoke(entity);

                if (GET_ABSORPTION == null) {
                    final MethodHandles.Lookup lookup = MethodHandles.publicLookup();
                    final Class<?> handleClass = handle.getClass();
                    final MethodType type = MethodType.methodType(float.class);

                    final String methodName;
                    if (Constraint.noGreaterThan(MinecraftVersions.V6_4).result()) {
                        methodName = "bn";
                    } else {
                        methodName = "getAbsorptionHearts";
                    }
                    GET_ABSORPTION = lookup.findVirtual(handleClass, methodName, type);
                }
                return (double) (float) GET_ABSORPTION.invoke(handle);
            } catch (final Throwable e) {
                TaterAPI.logger().error("Could not reflect to get entity's absorption", e);
                return 0d;
            }
        };
    }

    public static Consumer<Double> helper$setAbsorption(
            final org.bukkit.entity.@NonNull LivingEntity entity) {
        // v1.6.4:
        //   ((CraftLivingEntity) entity).getHandle().m((float) amount);
        // v1.7.10 - v1.13.2:
        //   ((CraftLivingEntity) entity).getHandle().setAbsorptionHearts((float) amount);
        return v -> {
            try {
                if (GET_HANDLE == null) {
                    final MethodHandles.Lookup lookup = MethodHandles.publicLookup();
                    final Class<?> clazz = Class.forName(cbClass("CraftLivingEntity"));
                    GET_HANDLE = lookup.unreflect(clazz.getMethod("getHandle"));
                }
                final Object handle = GET_HANDLE.invoke(entity);

                if (SET_ABSORPTION == null) {
                    final MethodHandles.Lookup lookup = MethodHandles.publicLookup();
                    final Class<?> handleClass = handle.getClass();
                    final MethodType type = MethodType.methodType(void.class, float.class);

                    final String methodName;
                    if (Constraint.noGreaterThan(MinecraftVersions.V6_4).result()) {
                        methodName = "m";
                    } else {
                        methodName = "setAbsorptionHearts";
                    }
                    SET_ABSORPTION = lookup.findVirtual(handleClass, methodName, type);
                }
                SET_ABSORPTION.invoke(handle, (float) v.doubleValue());
            } catch (final Throwable e) {
                TaterAPI.logger().error("Could not reflect to set entity's absorption", e);
            }
        };
    }

    public static Supplier<Double> helper$health(
            final org.bukkit.entity.@NonNull LivingEntity entity) {
        // v1.6.4 - v1.7.10:
        //   Ambiguous type, Reflect to get (double) entity.getHealth();
        return () -> {
            try {
                if (GET_HEALTH == null) {
                    final MethodHandles.Lookup lookup = MethodHandles.publicLookup();
                    final Class<?> clazz = Class.forName(cbClass("CraftLivingEntity"));
                    final MethodType type = MethodType.methodType(double.class);
                    GET_HEALTH = lookup.findVirtual(clazz, "getHealth", type);
                }
                return (double) GET_HEALTH.invokeExact(entity);
            } catch (final Throwable e) {
                TaterAPI.logger().error("Could not reflect to get entity's health", e);
                return 0d;
            }
        };
    }

    public static Supplier<Double> helper$maxHealth(
            final org.bukkit.entity.@NonNull LivingEntity entity) {
        // v1.6.4 - v1.7.10:
        //   Ambiguous type, Reflect to get (double) entity.getMaxHealth();
        return () -> {
            try {
                if (GET_MAX_HEALTH == null) {
                    final MethodHandles.Lookup lookup = MethodHandles.publicLookup();
                    final Class<?> clazz = Class.forName(cbClass("CraftLivingEntity"));
                    final MethodType type = MethodType.methodType(double.class);
                    GET_MAX_HEALTH = lookup.findVirtual(clazz, "getMaxHealth", type);
                }
                return (double) GET_MAX_HEALTH.invokeExact(entity);
            } catch (final Throwable e) {
                TaterAPI.logger().error("Could not reflect to get entity's max health", e);
                return 0d;
            }
        };
    }
}
