/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_19_4.fabric.patch.event.entity;

import dev.neuralnexus.taterapi.event.entity.EntityDamageEvent;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_19.vanilla.event.entity.VanillaEntityDamageEvent;

import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

/** Vanilla implementation of {@link EntityDamageEvent}. */
@ReqMappings(Mappings.YARN_INTERMEDIARY)
@ReqMCVersion(MinecraftVersion.V19_4)
@Mixin(DamageSource.class)
public abstract class DamageSourceMixin {

    @Shadow public abstract String getMsgId();

    public String bridege$cause() {
        DamageSource source = (DamageSource) (Object) this;
        return source.type().msgId();
        return this.getMsgId();
    }
}