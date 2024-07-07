/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.mixin.plugin;

import dev.neuralnexus.conditionalmixins.ConditionalMixins;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.*;

/** A mixin plugin for TaterLib. */
public class TaterLibMixinPlugin implements IMixinConfigPlugin {
    static {
        TaterAPIProvider.register();
    }

    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return ConditionalMixins.shouldApplyMixin(mixinClassName, true);
//        // NeoForge is picking up on Forge mixins
//        if (TaterAPIProvider.platform().is(Platform.NEOFORGE)
//                && mixinClassName.contains(".forge.mixin")) {
//            return false;
//        }
//        // Forge is picking up on Vanilla mixins
//        if (TaterAPIProvider.platform().isForgeBased()
//                // Forge 1.20.6 is Mojmapped, so it's alright
//                && !TaterAPIProvider.minecraftVersion().isAtLeast(MinecraftVersion.V1_20_5)
//                && mixinClassName.contains(".vanilla.mixin")) {
//            return false;
//        }
//
//        // Check if the mixin should be applied
//        boolean result = TaterLibConfigLoader.config().checkMixin(mixinClassName);
//        System.out.println(
//                Utils.ansiParser(
//                        "§6[TaterLib]: "
//                                + (result ? "§2Applying" : "§4Skipping")
//                                + " mixin §9"
//                                + mixinClassName));
//        return result;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(
            String targetClassName,
            ClassNode targetClass,
            String mixinClassName,
            IMixinInfo mixinInfo) {}

    @Override
    public void postApply(
            String targetClassName,
            ClassNode targetClass,
            String mixinClassName,
            IMixinInfo mixinInfo) {}
}
