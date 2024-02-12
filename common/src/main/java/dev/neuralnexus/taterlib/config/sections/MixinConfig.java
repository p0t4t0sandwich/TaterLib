package dev.neuralnexus.taterlib.config.sections;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.api.info.ServerType;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/** A class for parsing mixin configurations. */
@ConfigSerializable
public class MixinConfig {
    @Setting private String name;
    @Setting private boolean enabled;
    @Setting private final Set<String> versions = new HashSet<>();
    @Setting private final Set<String> serverTypes = new HashSet<>();
    @Setting private final Set<String> depends = new HashSet<>();

    /** Returns the name of the mixin. */
    public String name() {
        return name;
    }

    /** Returns whether the mixin is enabled. */
    public boolean enabled() {
        return enabled;
    }

    /** Returns whether the mixin is enabled for the specified version. */
    public boolean checkVersion(MinecraftVersion version, String versionString) {
        if (versionString.startsWith("!")) {
            return !version.parseRange(versionString.substring(1));
        } else {
            return version.parseRange(versionString);
        }
    }

    /** Returns whether the mixin is enabled for the specified versions. */
    public boolean checkVersions(MinecraftVersion version) {
        return versions.stream().allMatch(versionString -> checkVersion(version, versionString));
    }

    /** Returns whether the mixin is enabled for the specified server type. */
    public boolean checkServerType(ServerType serverType, String serverTypeString) {
        if (serverTypeString.startsWith("!")) {
            return !serverType.is(serverTypeString.substring(1));
        } else {
            return serverType.is(serverTypeString);
        }
    }

    /** Returns whether the mixin is enabled for the specified server types. */
    public boolean checkServerTypes(ServerType serverType) {
        return serverTypes.stream()
                .allMatch(serverTypeString -> checkServerType(serverType, serverTypeString));
    }

    /** Returns whether the mixin is enabled for the specified dependency. */
    public boolean checkDepend(Set<String> loadedPluginMods, String depend) {
        if (depend.startsWith("!")) {
            return !loadedPluginMods.contains(depend.substring(1));
        } else {
            return loadedPluginMods.contains(depend);
        }
    }

    /** Returns whether the mixin is enabled for the specified dependencies. */
    public boolean checkDepends(Set<String> loadedPluginMods) {
        return depends.stream().allMatch(depend -> checkDepend(loadedPluginMods, depend));
    }

    // TODO: Get rid of these once proper Mod/Plugin lists are available through TaterAPI
    /** Returns whether the mixin is enabled for the specified dependency. */
    public boolean checkDepend(Predicate<String> isPluginModLoaded, String depend) {
        if (depend.startsWith("!")) {
            return !isPluginModLoaded.test(depend.substring(1));
        } else {
            return isPluginModLoaded.test(depend);
        }
    }

    /** Returns whether the mixin is enabled for the specified dependencies. */
    public boolean checkDepends(Predicate<String> isPluginModLoaded) {
        return depends.stream().allMatch(depend -> checkDepend(isPluginModLoaded, depend));
    }

    /** Returns whether the mixin should be applied. */
    public boolean checkMixin() {
        return enabled()
                && checkVersions(MinecraftVersion.getMinecraftVersion())
                && checkServerTypes(ServerType.getServerType())
                && checkDepends(TaterAPIProvider.get()::isPluginModLoaded);
    }
}
