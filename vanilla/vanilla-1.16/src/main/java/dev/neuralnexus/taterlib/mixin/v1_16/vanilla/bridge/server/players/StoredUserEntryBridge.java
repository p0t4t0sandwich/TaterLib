/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/Tater-Certified/Overseer/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_16.vanilla.bridge.server.players;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.taterlib.mixin.v1_16.vanilla.accessors.server.players.StoredUserEntryAccessor;

import net.minecraft.server.players.UserWhiteListEntry;

@SuppressWarnings("unchecked")
public interface StoredUserEntryBridge {
    default GameProfile bridge$getUser(UserWhiteListEntry whiteListEntry) {
        return ((StoredUserEntryAccessor<GameProfile>) whiteListEntry).invoker$getUser();
    }
}
