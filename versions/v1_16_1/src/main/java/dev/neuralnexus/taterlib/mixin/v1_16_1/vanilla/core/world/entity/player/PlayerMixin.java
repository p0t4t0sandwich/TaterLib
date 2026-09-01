/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_16_1.vanilla.core.world.entity.player;

import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.network.chat.Component;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.player.PlayerBridge;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;

@AConstraint(
        mappings = Mappings.MOJANG,
        version = @Versions(min = MinecraftVersion.V16_1, max = MinecraftVersion.V18_2))
@Mixin(Player.class)
public abstract class PlayerMixin extends Entity implements PlayerBridge {
    public PlayerMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void bridge$sendMessage(String message) {
        this.sendMessage(Component.literal(message), Notifiable.NIL_UUID);
    }
}
