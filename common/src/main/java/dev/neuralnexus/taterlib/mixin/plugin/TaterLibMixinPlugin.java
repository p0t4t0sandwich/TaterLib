/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.plugin;

import dev.neuralnexus.taterapi.config.MixinConfig;
import dev.neuralnexus.taterapi.impl.loader.TaterLoader;
import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.muxins.Muxins;
import dev.neuralnexus.taterlib.config.TaterLibConfigLoader;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

/** A mixin plugin for TaterLib. */
public class TaterLibMixinPlugin implements IMixinConfigPlugin {
    private static final Logger logger = Logger.create(TaterLoader.MOD_ID + "-mixinplugin");

    @Override
    public void onLoad(final @NonNull String mixinPackage) {
        try {
            final MixinConfig config = TaterLibConfigLoader.config().mixin();
            Muxins.bootstrap(mixinPackage, config.verbose());
        } catch (final Exception e) {
            logger.error("Error during Muxins bootstrap:");
            e.printStackTrace();
        }
    }

    @Override
    public @Nullable String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(
            final @NonNull String targetClassName, final @NonNull String mixinClassName) {
        final MixinConfig config = TaterLibConfigLoader.config().mixin();
        return Muxins.shouldApplyMixin(mixinClassName, config.disabled(), config.verbose());
    }

    @Override
    public void acceptTargets(
            final Set<@NonNull String> myTargets, final Set<@NonNull String> otherTargets) {}

    @Override
    public @Nullable List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(
            final @NonNull String targetClassName,
            final @NonNull ClassNode targetClass,
            final @NonNull String mixinClassName,
            final @NonNull IMixinInfo mixinInfo) {}

    @Override
    public void postApply(
            final @NonNull String targetClassName,
            final @NonNull ClassNode targetClass,
            final @NonNull String mixinClassName,
            final @NonNull IMixinInfo mixinInfo) {}
}
