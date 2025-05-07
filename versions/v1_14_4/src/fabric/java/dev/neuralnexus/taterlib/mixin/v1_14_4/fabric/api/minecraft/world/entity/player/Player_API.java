/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.fabric.api.minecraft.world.entity.player;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.taterapi.entity.HumanEntity;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.entity.InventoryHolder;
import dev.neuralnexus.taterapi.entity.Nameable;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.item.inventory.PlayerInventory;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.player.PlayerBridge;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.item.inventory.VanillaPlayerInventory;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.GameType;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.YARN_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V14)
@Mixin(net.minecraft.world.entity.player.Player.class)
@Implements({
    @Interface(iface = HumanEntity.class, prefix = "humanEntity$", remap = Remap.NONE),
    @Interface(iface = Identifiable.class, prefix = "identifiable$", remap = Remap.NONE),
    @Interface(iface = InventoryHolder.class, prefix = "invHolder$", remap = Remap.NONE),
    @Interface(iface = Nameable.class, prefix = "nameable$", remap = Remap.NONE),
    @Interface(iface = Notifiable.class, prefix = "notifiable$", remap = Remap.NONE),
    @Interface(iface = Player.class, prefix = "player$", remap = Remap.NONE),
    @Interface(iface = User.class, prefix = "user$", remap = Remap.NONE)
})
public abstract class Player_API implements PlayerBridge {
    // @spotless:off
    @Shadow @Final public Inventory inventory;
    @Shadow @Final public Abilities abilities;
    @Shadow public abstract GameProfile shadow$getGameProfile();
    @Shadow public abstract Component shadow$getDisplayName();
    // @spotless:on

    public String nameable$name() {
        return this.shadow$getGameProfile().getName();
    }

    public String nameable$displayName() {
        return this.shadow$getDisplayName().getString();
    }

    public void notifiable$sendMessage(String message) {
        this.bridge$sendMessage(message);
    }

    public PlayerInventory player$inventory() {
        return new VanillaPlayerInventory(this.inventory);
    }

    @SuppressWarnings("DataFlowIssue")
    public GameMode humanEntity$gameMode() {
        return GameMode.fromName(
                ((ServerPlayer) (Object) this).gameMode.getGameModeForPlayer().getName());
    }

    @SuppressWarnings("DataFlowIssue")
    public void humanEntity$setGameMode(GameMode gameMode) {
        ((ServerPlayer) (Object) this).setGameMode(GameType.byId(gameMode.id()));
    }

    public void player$allowFlight(boolean allow) {
        this.abilities.mayfly = allow;
    }

    public boolean player$canFly() {
        return this.abilities.mayfly;
    }

    public boolean player$isFlying() {
        return this.abilities.flying;
    }

    public void player$setFlying(boolean flying) {
        this.abilities.flying = flying;
    }
}
