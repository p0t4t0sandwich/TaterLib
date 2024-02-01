package dev.neuralnexus.taterlib.mixin.plugin;

import com.google.common.collect.ImmutableMap;

import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.api.info.ServerType;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.*;
import java.util.function.Supplier;

/** A mixin plugin for TaterLib. */
public class TaterLibMixinPlugin implements IMixinConfigPlugin {
    private static final Supplier<Boolean> IS_20_TO_20_2 =
            () ->
                    TaterAPIProvider.minecraftVersion()
                            .isInRange(
                                    true, MinecraftVersion.V1_20, false, MinecraftVersion.V1_20_2);
    private static final Supplier<Boolean> IS_20_2 =
            () -> TaterAPIProvider.minecraftVersion().isAtLeast(MinecraftVersion.V1_20_2);
    private static final Map<String, Supplier<Boolean>> CONDITIONS =
            ImmutableMap.<String, Supplier<Boolean>>builder()
                    // 1.20 Listener Mixins
                    .put("CustomPayloadMixin_1_20", IS_20_TO_20_2)
                    .put("PlayerAdvancementFinishedMixin_1_20", IS_20_TO_20_2)
                    .put("PlayerAdvancementProgressMixin_1_20", IS_20_TO_20_2)
                    .put("PlayerLoginMixin_1_20", IS_20_TO_20_2)
                    // 1.20.2 Listener Mixins
                    .put("CustomPayloadMixin_1_20_2", IS_20_2)
                    .put("PlayerAdvancementFinishedMixin_1_20_2", IS_20_2)
                    .put("PlayerAdvancementProgressMixin_1_20_2", IS_20_2)
                    .put("PlayerLoginMixin_1_20_2", IS_20_2)
                    // 1.20.2 Patch Mixins
                    .put("VanillaPlayerMixin_1_20_2", IS_20_2)
                    .build();

    static {
        TaterAPIProvider.register();
    }

    private static boolean checkMixin(String mixinClassName) {
        for (Map.Entry<String, Supplier<Boolean>> entry : CONDITIONS.entrySet()) {
            if (mixinClassName.endsWith(entry.getKey())) {
                return entry.getValue().get();
            }
        }
        return true;
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
        if (TaterAPIProvider.serverType().is(ServerType.FORGE, ServerType.SPONGE_FORGE)
                && mixinClassName.contains(".vanilla.mixin")) {
            return false;
        }

        // Check if the mixin should be applied
        boolean result = checkMixin(mixinClassName);
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
