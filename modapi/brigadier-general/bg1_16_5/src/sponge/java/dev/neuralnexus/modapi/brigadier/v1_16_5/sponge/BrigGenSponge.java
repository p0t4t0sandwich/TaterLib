/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.brigadier.v1_16_5.sponge;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.modapi.brigadier.BrigGenPlugin;
import dev.neuralnexus.modapi.brigadier.EventHelper;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.MinecraftVersions;
import dev.neuralnexus.taterapi.meta.Platform;
import dev.neuralnexus.taterapi.meta.Platforms;

import io.leangen.geantyref.TypeToken;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.EventListenerRegistration;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.event.lifecycle.RegisterCommandEvent;
import org.spongepowered.plugin.PluginContainer;

public class BrigGenSponge implements BrigGenPlugin {
    // TODO: Adapt this? Probably not considering it's only needed on SpongeVanilla
    @Override
    public Mappings mappings() {
        return Mappings.MOJANG;
    }

    @Override
    public Platform platform() {
        return Platforms.SPONGE;
    }

    @Override
    public MinecraftVersion min() {
        return MinecraftVersions.V14;
    }

    @Override
    public void onInit() {
        if (MetaAPI.instance().isPrimaryPlatform(Platforms.SPONGE)) {
            // TODO: Constant the mod_id
            @SuppressWarnings("OptionalGetWithoutIsPresent")
            PluginContainer container = Sponge.pluginManager().plugin("brigadier_general").get();
            EventManager eventManager = Sponge.eventManager();
            TypeToken<RegisterCommandEvent<LiteralArgumentBuilder<CommandSourceStack>>> typeToken =
                    new TypeToken<
                            RegisterCommandEvent<LiteralArgumentBuilder<CommandSourceStack>>>() {};
            EventListenerRegistration<
                            RegisterCommandEvent<LiteralArgumentBuilder<CommandSourceStack>>>
                    listener =
                            EventListenerRegistration.builder(typeToken)
                                    .plugin(container)
                                    .listener(
                                            event -> {
                                                // TODO: Make this a lot less bad
                                                MinecraftServer server =
                                                        (MinecraftServer) Sponge.server();
                                                EventHelper.registerCommand(
                                                        server.getCommands().getDispatcher());
                                            })
                                    .build();
            eventManager.registerListener(listener);
        }
    }
}
