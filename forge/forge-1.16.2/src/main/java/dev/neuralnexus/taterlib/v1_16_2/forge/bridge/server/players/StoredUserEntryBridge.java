/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_16_2.forge.bridge.server.players;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.taterlib.mixin.v1_16_2.forge.accessors.server.players.StoredUserEntryAccessor;

import net.minecraft.server.players.UserWhiteListEntry;

@SuppressWarnings("unchecked")
public interface StoredUserEntryBridge {
    default GameProfile bridge$getUser(UserWhiteListEntry whiteListEntry) {
        return ((StoredUserEntryAccessor<GameProfile>) whiteListEntry).invoker$getUser();
    }
}
