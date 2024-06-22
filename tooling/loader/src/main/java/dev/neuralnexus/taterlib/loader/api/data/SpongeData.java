package dev.neuralnexus.taterlib.loader.api.data;

import dev.neuralnexus.taterlib.loader.api.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.api.ModInfo;
import dev.neuralnexus.taterlib.loader.api.PlatformData;

import org.spongepowered.api.Sponge;

import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the Sponge platform */
public class SpongeData implements PlatformData {
    @Override
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.from(Sponge.platform().minecraftVersion().name());
    }

    @Override
    public String modLoaderVersion() {
        return Sponge.pluginManager().plugin("sponge").get().metadata().version().toString();
    }

    @Override
    public List<ModInfo> modList() {
        return Sponge.pluginManager().plugins().stream()
                .map(
                        pluginContainer ->
                                new ModInfo(
                                        pluginContainer.metadata().id(),
                                        pluginContainer.metadata().name().orElse(""),
                                        pluginContainer.metadata().version().toString()))
                .collect(Collectors.toList());
    }
}
