package dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;

public interface EntityBridge {
    void bridge$sendMessage(String message);

    ResourceLocation bridge$biome();

    void bridge$changeDimension(ServerLevel level);
}
