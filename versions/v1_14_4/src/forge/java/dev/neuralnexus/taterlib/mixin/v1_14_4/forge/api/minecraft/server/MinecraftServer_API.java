/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.forge.api.minecraft.server;

import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.world.VanillaServerWorld;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.PlayerList;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AConstraint(
        mappings = Mappings.SEARGE,
        version = @Versions(min = MinecraftVersion.V14, max = MinecraftVersion.V16_5))
@Mixin(MinecraftServer.class)
@Implements(@Interface(iface = Server.class, prefix = "server$", remap = Remap.NONE))
public abstract class MinecraftServer_API {
    @Shadow
    public abstract String shadow$getServerModName();

    @Shadow
    public abstract PlayerList shadow$getPlayerList();

    @Shadow
    public abstract Iterable<ServerLevel> shadow$getAllLevels();

    public String server$brand() {
        return this.shadow$getServerModName();
    }

    public List<User> server$players() {
        return this.shadow$getPlayerList().getPlayers().stream()
                .map(User.class::cast)
                .collect(Collectors.toList());
    }

    public List<ServerWorld> server$worlds() {
        List<ServerWorld> worlds = new ArrayList<>();
        this.shadow$getAllLevels().forEach(world -> worlds.add(new VanillaServerWorld(world)));
        return worlds;
    }
}
