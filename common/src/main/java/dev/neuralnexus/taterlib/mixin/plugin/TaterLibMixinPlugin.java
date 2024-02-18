package dev.neuralnexus.taterlib.mixin.plugin;

import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.config.ConfigLoader;

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
        // NeoForge is picking up on Forge mixins
        if (TaterAPIProvider.serverType().is(ServerType.NEOFORGE)
                && mixinClassName.contains(".forge.mixin")) {
            return false;
        }
        // Forge is picking up on Vanilla mixins
        if (TaterAPIProvider.serverType().isForgeBased()
                && mixinClassName.contains(".vanilla.mixin")) {
            return false;
        }

        // Check if the mixin should be applied
        boolean result = ConfigLoader.config().checkMixin(mixinClassName);
        System.out.println(
                Utils.ansiParser(
                        "§6[TaterLib]: "
                                + (result ? "§2Applying" : "§4Skipping")
                                + " mixin §9"
                                + mixinClassName));
        return result;
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
