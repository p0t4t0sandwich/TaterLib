/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.forge.api.minecraft.world.entity;

import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.entity.Nameable;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.EntityBridge;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.world.VanillaLocation;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.world.VanillaServerWorld;

import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;
import java.util.UUID;

@ReqMappings(Mappings.LEGACY_SEARGE)
@ReqMCVersion(min = MinecraftVersion.V14, max = MinecraftVersion.V16_5)
@Mixin(net.minecraft.world.entity.Entity.class)
@Implements({
    @Interface(iface = CommandSender.class, prefix = "cmdSender$", remap = Remap.NONE),
    @Interface(iface = Entity.class, prefix = "entity$", remap = Remap.NONE),
    @Interface(iface = Nameable.class, prefix = "nameable$", remap = Remap.NONE),
    @Interface(iface = Identifiable.class, prefix = "identifiable$", remap = Remap.NONE)
})
public abstract class Entity_API implements EntityBridge {
    @Shadow
    public abstract int shadow$getId();

    @Shadow
    public abstract EntityType<?> shadow$getType();

    @Shadow
    @Nullable public abstract MinecraftServer shadow$getServer();

    @Shadow
    public abstract void shadow$teleportTo(double x, double y, double z);

    @Shadow
    @Nullable public abstract Component shadow$getCustomName();

    @Shadow
    public abstract void shadow$setCustomName(@Nullable Component name);

    @Shadow
    public abstract UUID shadow$getUUID();

    public void cmdSender$sendMessage(String message) {
        this.bridge$sendMessage(message);
    }

    public int entity$entityId() {
        return this.shadow$getId();
    }

    public void entity$remove() {
        this.bridge$remove();
    }

    @SuppressWarnings("deprecation")
    public ResourceKey entity$type() {
        return (ResourceKey) Registry.ENTITY_TYPE.getKey(this.shadow$getType());
    }

    public Location entity$location() {
        return new VanillaLocation((net.minecraft.world.entity.Entity) (Object) this);
    }

    public ResourceKey entity$biome() {
        return (ResourceKey) this.bridge$biome();
    }

    @SuppressWarnings({"DataFlowIssue", "resource"})
    public void entity$teleport(Location location) {
        if (!location.world().dimension().equals(((Entity) this).dimension())) {
            if (this.shadow$getServer() == null) return;
            Optional<ServerLevel> serverLevel =
                    ((Server) this.shadow$getServer())
                            .world(location.world().dimension())
                            .map(VanillaServerWorld.class::cast)
                            .map(VanillaServerWorld::unwrap);
            if (!serverLevel.isPresent()) return;
            this.bridge$changeDimension(serverLevel.get());
        }
        this.shadow$teleportTo(location.x(), location.y(), location.z());
    }

    @SuppressWarnings("DataFlowIssue")
    public Optional<String> nameable$customName() {
        if (this.shadow$getCustomName() == null) return Optional.empty();
        return Optional.of(this.shadow$getCustomName().getString());
    }

    public void nameable$setCustomName(String name) {
        this.shadow$setCustomName(new TextComponent(name));
    }

    public UUID identifiable$uuid() {
        return this.shadow$getUUID();
    }
}
