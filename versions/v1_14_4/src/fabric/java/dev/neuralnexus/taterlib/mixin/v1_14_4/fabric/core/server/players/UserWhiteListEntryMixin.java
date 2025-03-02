/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.fabric.core.server.players;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla.accessors.server.players.StoredUserEntryAccessor;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.server.players.UserWhitelistEntryBridge;

import net.minecraft.server.players.UserWhiteListEntry;

import org.spongepowered.asm.mixin.Mixin;

@ReqMappings(Mappings.YARN_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V14)
@Mixin(UserWhiteListEntry.class)
public class UserWhiteListEntryMixin implements UserWhitelistEntryBridge {
    @SuppressWarnings("unchecked")
    @Override
    public GameProfile bridge$getUser() {
        return ((StoredUserEntryAccessor<GameProfile>) this).invoker$getUser();
    }
}
