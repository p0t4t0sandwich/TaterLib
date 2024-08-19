/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/Tater-Certified/Overseer/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20.vanilla.accessors.server.players;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.conditionalmixins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.Mappings;
import dev.neuralnexus.taterapi.MinecraftVersion;

import net.minecraft.server.players.StoredUserEntry;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@ReqMappings(Mappings.MOJMAP)
@ReqMCVersion(min = MinecraftVersion.V1_20, max = MinecraftVersion.V1_20_6)
@Mixin(StoredUserEntry.class)
public interface StoredUserEntryAccessor<T> {
    @Invoker("getUser")
    @Nullable T invoker$getUser();
}
