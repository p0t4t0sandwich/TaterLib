/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.server.players;

import com.mojang.authlib.GameProfile;

public interface UserWhitelistEntryBridge {
    GameProfile bridge$getUser();
}
