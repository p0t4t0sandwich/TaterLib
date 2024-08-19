/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/Tater-Certified/Overseer/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_16_2.forge.bridge.server.players;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.mixin.v1_16_2.forge.accessors.server.players.GameProfileCacheAccessor;

import net.minecraft.server.players.GameProfileCache;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public interface GameProfileCacheBridge {
    default Map<String, GameProfile> bridge$getProfilesbyName(GameProfileCache cache) {
        Map<String, GameProfile> result = new HashMap<>();
        Map<String, ?> info = ((GameProfileCacheAccessor) cache).accessor$getProfilesbyName();
        for (Object i : info.values()) {
            // Reflect to get GameProfileCache.GameProfileInfo#getProfile() method
            try {
                GameProfile profile = (GameProfile) i.getClass().getMethod("getProfile").invoke(i);
                result.put(profile.getName(), profile);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                TaterLib.logger().error("Failed to get GameProfile from GameProfileCache", e);
            }
        }
        return result;
    }
}
