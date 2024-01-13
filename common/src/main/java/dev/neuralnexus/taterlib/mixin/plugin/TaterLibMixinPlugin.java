package dev.neuralnexus.taterlib.mixin.plugin;

import com.google.common.collect.ImmutableMap;

import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.api.info.ServerType;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
    private static final Supplier<Boolean> TRUE = () -> true;
    private static final String mixinPath =
            "dev.neuralnexus.taterlib.vanilla."
                    + TaterAPIProvider.serverType().name().toLowerCase()
                    + ".mixin.";
    private static final Map<String, Supplier<Boolean>> CONDITIONS =
            ImmutableMap.<String, Supplier<Boolean>>builder()
                    // 1.20 Listener Mixins
                    .put(
                            mixinPath + "listeners.player.PlayerAdvancementFinishedMixin_1_20",
                            IS_20_TO_20_2)
                    .put(
                            mixinPath + "listeners.player.PlayerAdvancementProgressMixin_1_20",
                            IS_20_TO_20_2)
                    .put(mixinPath + "listeners.player.PlayerLoginMixin_1_20", IS_20_TO_20_2)
                    .put(mixinPath + "listeners.player.PlayerLogoutMixin_1_20", IS_20_TO_20_2)
                    .put(
                            mixinPath + "listeners.pluginmessages.PluginMessagesMixin_1_20",
                            IS_20_TO_20_2)
                    // 1.20.2 Listener Mixins
                    .put(
                            mixinPath + "listeners.player.PlayerAdvancementFinishedMixin_1_20_2",
                            IS_20_2)
                    .put(
                            mixinPath + "listeners.player.PlayerAdvancementProgressMixin_1_20_2",
                            IS_20_2)
                    .put(mixinPath + "listeners.player.PlayerLoginMixin_1_20_2", IS_20_2)
                    .put(mixinPath + "listeners.player.PlayerLogoutMixin_1_20_2", IS_20_2)
                    .put(mixinPath + "listeners.pluginmessages.PluginMessagesMixin_1_20_2", IS_20_2)
                    // 1.20.2 Patch Mixins
                    .put(mixinPath + "patch.player.VanillaPlayerMixin_1_20_2", IS_20_2)
                    .build();

    static {
        TaterAPIProvider.register(getMCVersion());
    }

    static String getMCVersion() {
        ServerType serverType = TaterAPIProvider.serverType();
        if (serverType.isFabricBased()) {
            return getFabricMCVersion();
        } else if (serverType.is(ServerType.NEOFORGE)) {
            return getNeoForgeMCVersion();
        } else if (serverType.isForgeBased()) {
            return getForgeMCVersion();
        }
        return "Unknown";
    }

    static String getFabricMCVersion() {
        try {
            Class<?> fabricLoaderClass = Class.forName("net.fabricmc.loader.api.FabricLoader");
            Object fabricLoaderInstance = fabricLoaderClass.getMethod("getInstance").invoke(null);
            Object minecraftModContainer =
                    fabricLoaderClass
                            .getMethod("getModContainer", String.class)
                            .invoke(fabricLoaderInstance, "minecraft");
            Object minecraftModContainerInstance =
                    minecraftModContainer.getClass().getMethod("get").invoke(minecraftModContainer);
            Object minecraftModContainerMetadata =
                    minecraftModContainerInstance
                            .getClass()
                            .getMethod("getMetadata")
                            .invoke(minecraftModContainerInstance);
            Method minecraftModContainerVersionMethod =
                    minecraftModContainerMetadata.getClass().getMethod("getVersion");
            minecraftModContainerVersionMethod.setAccessible(true);
            Object minecraftModContainerVersion =
                    minecraftModContainerVersionMethod.invoke(minecraftModContainerMetadata);
            Object minecraftModContainerVersionFriendlyString =
                    minecraftModContainerVersion
                            .getClass()
                            .getMethod("getFriendlyString")
                            .invoke(minecraftModContainerVersion);
            return (String) minecraftModContainerVersionFriendlyString;
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return "Unknown";
    }

    static String getForgeMCVersion() {
        try {
            Class<?> fmlLoaderClass = Class.forName("net.minecraftforge.fml.loading.FMLLoader");
            try {
                // Reflect to get FMLLoader.versionInfo().mcVersion()
                Object versionInfoObject = fmlLoaderClass.getMethod("versionInfo").invoke(null);
                Object mcVersionObject =
                        versionInfoObject
                                .getClass()
                                .getMethod("mcVersion")
                                .invoke(versionInfoObject);
                return (String) mcVersionObject;
            } catch (ReflectiveOperationException e) {
                // Reflect to get private FMLLoader.mcVersion
                Field mcVersionField = fmlLoaderClass.getDeclaredField("mcVersion");
                mcVersionField.setAccessible(true);
                return (String) mcVersionField.get(null);
            }
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return "Unknown";
    }

    static String getNeoForgeMCVersion() {
        // Reflect to get FMLLoader.versionInfo().mcVersion()
        try {
            Class<?> fmlLoaderClass = Class.forName("net.neoforged.fml.loading.FMLLoader");
            Object versionInfoObject = fmlLoaderClass.getMethod("versionInfo").invoke(null);
            Object mcVersionObject =
                    versionInfoObject.getClass().getMethod("mcVersion").invoke(versionInfoObject);
            return (String) mcVersionObject;
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return "Unknown";
    }

    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        // NeoForge is picking up on Forge mixins, so we need to skip them
        if (TaterAPIProvider.serverType().is(ServerType.NEOFORGE)
                && mixinClassName.contains(".forge")) {
            return false;
        }

        // Check if the mixin should be applied
        boolean result = CONDITIONS.getOrDefault(mixinClassName, TRUE).get();
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
