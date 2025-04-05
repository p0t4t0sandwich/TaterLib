/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.server;

import com.mojang.authlib.GameProfile;

import java.util.Map;

// TODO: Rewrite to add a dummy interface for GameProfileInfo
public interface MinecraftServerBridge {
    String bridge$brand();

    Map<String, GameProfile> bridge$getProfilesbyName();
}
