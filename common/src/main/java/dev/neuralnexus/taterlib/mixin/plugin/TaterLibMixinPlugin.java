package dev.neuralnexus.taterlib.mixin.plugin;

import com.google.common.collect.ImmutableMap;

import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.config.ConfigLoader;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.*;
import java.util.function.Supplier;

/** A mixin plugin for TaterLib. */
public class TaterLibMixinPlugin implements IMixinConfigPlugin {
    private static final Supplier<Boolean> IS_19 =
            () -> TaterAPIProvider.minecraftVersion().is(MinecraftVersion.V1_19);
    private static final Supplier<Boolean> IS_19_1 =
            () -> TaterAPIProvider.minecraftVersion().parseRange("[1.19.1, 1.19.4]");
    private static final Supplier<Boolean> IS_19_3 =
            () -> TaterAPIProvider.minecraftVersion().parseRange("[1.19.3, 1.19.4]");
    private static final Supplier<Boolean> IS_19_TO_19_3 =
            () -> TaterAPIProvider.minecraftVersion().parseRange("[1.19, 1.19.3]");
    private static final Supplier<Boolean> IS_19_4 =
            () -> TaterAPIProvider.minecraftVersion().is(MinecraftVersion.V1_19_4);
    private static final Supplier<Boolean> IS_19_TO_19_4 =
            () -> TaterAPIProvider.minecraftVersion().parseRange("[1.19, 1.19.4]");
    private static final Supplier<Boolean> IS_20_2 =
            () -> TaterAPIProvider.minecraftVersion().parseRange("[1.20.2, 1.20.4]");
    private static final Supplier<Boolean> IS_20_TO_20_1 =
            () -> TaterAPIProvider.minecraftVersion().is(MinecraftVersion.V1_20);
    private static final Supplier<Boolean> IS_20_TO_20_4 =
            () -> TaterAPIProvider.minecraftVersion().parseRange("[1.20, 1.20.4]");
    private static final Map<String, Supplier<Boolean>> CONDITIONS =
            ImmutableMap.<String, Supplier<Boolean>>builder()
                    // 1.19-1.19.4 Listener Mixins
                    .put("PlayerBlockBreakMixin_1_19", IS_19_TO_19_4)
                    .put("EntityDamageMixin_1_19", IS_19_TO_19_4)
                    .put("EntityDeathMixin_1_19", IS_19_TO_19_4)
                    .put("EntitySpawnMixin_1_19", IS_19_TO_19_4)
                    .put("CustomPayloadMixin_1_19", IS_19_TO_19_4)
                    .put("PlayerAdvancementFinishedMixin_1_19", IS_19_TO_19_4)
                    .put("PlayerAdvancementProgressMixin_1_19", IS_19_TO_19_4)
                    .put("PlayerDeathMixin_1_19", IS_19_TO_19_4)
                    .put("PlayerLoginMixin_1_19", IS_19_TO_19_4)
                    .put("PlayerLogoutMixin_1_19", IS_19_TO_19_4)
                    .put("PlayerRespawnMixin_1_19", IS_19_TO_19_4)
                    .put("ServerStartingMixin_1_19", IS_19_TO_19_4)
                    .put("ServerStoppedMixin_1_19", IS_19_TO_19_4)
                    .put("ServerStoppingMixin_1_19", IS_19_TO_19_4)
                    // 1.19 Listener Mixins
                    .put("PlayerMessageMixin_1_19", IS_19)
                    .put("ServerStartedMixin_1_19", IS_19_TO_19_3)
                    // 1.19.1 Listener Mixins
                    .put("PlayerMessageMixin_1_19_1", IS_19_1)
                    // 1.19.1 Patch Mixins
                    .put("VanillaCommandSenderMixin_1_19_1", IS_19_1)
                    // 1.19.3 Patch Mixins
                    .put("VanillaEntityMixin_1_19_3", IS_19_3)
                    .put("VanillaPlayerMixin_1_19_3", IS_19_3)
                    // 1.19.4 Listener Mixins
                    .put("ServerStartedMixin_1_19_4", IS_19_4)
                    // 1.19.4 Patch Mixins
                    .put("VanillaEntityDamageEventMixin_1_19_4", IS_19_4)
                    // 1.20-1.20.4 Listener Mixins
                    .put("PlayerBlockBreakMixin_1_20", IS_20_TO_20_4)
                    .put("EntityDamageMixin_1_20", IS_20_TO_20_4)
                    .put("EntityDeathMixin_1_20", IS_20_TO_20_4)
                    .put("EntitySpawnMixin_1_20", IS_20_TO_20_4)
                    .put("PlayerDeathMixin_1_20", IS_20_TO_20_4)
                    .put("PlayerLogoutMixin_1_20", IS_20_TO_20_4)
                    .put("PlayerMessageMixin_1_20", IS_20_TO_20_4)
                    .put("PlayerRespawnMixin_1_20", IS_20_TO_20_4)
                    .put("ServerStartedMixin_1_20", IS_20_TO_20_4)
                    .put("ServerStartingMixin_1_20", IS_20_TO_20_4)
                    .put("ServerStoppedMixin_1_20", IS_20_TO_20_4)
                    .put("ServerStoppingMixin_1_20", IS_20_TO_20_4)
                    // 1.20 Listener Mixins
                    .put("CustomPayloadMixin_1_20", IS_20_TO_20_1)
                    .put("PlayerAdvancementFinishedMixin_1_20", IS_20_TO_20_1)
                    .put("PlayerAdvancementProgressMixin_1_20", IS_20_TO_20_1)
                    .put("PlayerLoginMixin_1_20", IS_20_TO_20_1)
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
