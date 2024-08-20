/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_17_1.forge.bridge.server.players;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.mixin.v1_17_1.forge.accessors.server.players.GameProfileCacheAccessor;

import net.minecraft.server.players.GameProfileCache;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public interface GameProfileCacheBridge {
    default Map<String, GameProfile> bridge$getProfilesbyName(GameProfileCache cache) {
        Map<String, GameProfile> result = new HashMap<>();
        Map<String, ?> info = ((GameProfileCacheAccessor) cache).accessor$getProfilesbyName();
        for (Object i : info.values()) {
            // Reflect to get GameProfileCache$GameProfileInfo#getProfile() method (m_11028_)
            try {
                Method method = i.getClass().getMethod("m_11028_");
                method.setAccessible(true);
                GameProfile profile = (GameProfile) method.invoke(i);
                result.put(profile.getName(), profile);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                TaterLib.logger().error("Failed to get GameProfile from GameProfileCache", e);
            }
        }
        return result;
    }
}
