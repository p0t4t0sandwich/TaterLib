/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.server.players;

import com.mojang.authlib.GameProfile;

import java.util.Map;

// TODO: Rewrite to add a dummy interface for GameProfileInfo
public interface GameProfileCacheBridge {
    Map<String, GameProfile> bridge$getProfilesbyName();
}
