/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla.core.server;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla.accessors.server.players.GameProfileCacheAccessor;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.server.players.GameProfileCacheBridge;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.players.GameProfileCache;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@ReqMappings(Mappings.MOJANG)
@ReqMCVersion(min = MinecraftVersion.V14)
@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin implements GameProfileCacheBridge {
    @Shadow
    public abstract GameProfileCache shadow$getProfileCache();

    public Map<String, GameProfile> bridge$getProfilesbyName() {
        Map<String, GameProfile> result = new HashMap<>();
        Map<String, ?> info =
                ((GameProfileCacheAccessor) this.shadow$getProfileCache())
                        .accessor$getProfilesbyName();
        for (Object i : info.values()) {
            // Reflect to get GameProfileCache$GameProfileInfo#getProfile() method (func_152668_a)
            try {
                Method method = i.getClass().getMethod("func_152668_a");
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
