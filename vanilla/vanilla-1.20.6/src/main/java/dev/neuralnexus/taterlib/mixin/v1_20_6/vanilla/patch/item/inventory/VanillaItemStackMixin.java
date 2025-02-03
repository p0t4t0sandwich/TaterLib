/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_6.vanilla.patch.item.inventory;

import dev.neuralnexus.modapi.metadata.enums.MinecraftVersion;
import dev.neuralnexus.modapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterlib.v1_20.vanilla.item.inventory.VanillaItemStack;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/** Patch mixin for VanillaItemStack 1.20.6 */
@ReqMCVersion(min = MinecraftVersion.V20_5, max = MinecraftVersion.V20_6)
@Mixin(value = VanillaItemStack.class, remap = false)
public class VanillaItemStackMixin {
    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.20.6
     */
    @Overwrite
    public boolean hasDisplayName() {
        return ((VanillaItemStack) (Object) this).unwrap().get(DataComponents.CUSTOM_NAME) != null;
    }

    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.20.6
     */
    @Overwrite
    public void setDisplayName(String name) {
        ((VanillaItemStack) (Object) this)
                .unwrap()
                .set(DataComponents.CUSTOM_NAME, Component.nullToEmpty(name));
    }

    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.20.6
     */
    @Overwrite
    public boolean unbreakable() {
        return ((VanillaItemStack) (Object) this).unwrap().has(DataComponents.UNBREAKABLE);
    }

    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.20.6
     */
    @Overwrite
    @SuppressWarnings("DataFlowIssue")
    public void setUnbreakable(boolean unbreakable) {
        if (((VanillaItemStack) (Object) this).unwrap().has(DataComponents.UNBREAKABLE)) {
            ((VanillaItemStack) (Object) this).unwrap().remove(DataComponents.UNBREAKABLE);
        }
    }
}
