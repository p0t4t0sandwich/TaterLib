/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_13_2.forge.networking;

import dev.neuralnexus.taterlib.v1_13_2.forge.networking.packet.ForgeMessagePacket;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ModMessages {
    private static final String PROTOCOL_VERSION = "1";
    private static final Set<String> channelQueue = new HashSet<>();
    private static final HashMap<String, SimpleChannel> channels = new HashMap<>();
    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public static void addChannels(Set<String> channel) {
        channelQueue.addAll(channel);
    }

    public static void register() {
        for (String c : channelQueue) {
            String[] channelParts = c.split(":");
            if (channelParts.length != 2) {
                continue;
            }
            channels.put(
                    c,
                    NetworkRegistry.ChannelBuilder.named(
                                    new ResourceLocation(channelParts[0], channelParts[1]))
                            .networkProtocolVersion(() -> PROTOCOL_VERSION)
                            .clientAcceptedVersions(s -> true)
                            .serverAcceptedVersions(s -> true)
                            .simpleChannel());
            channels.get(c)
                    .messageBuilder(ForgeMessagePacket.class, id())
                    .encoder(ForgeMessagePacket::encode)
                    .decoder(ForgeMessagePacket::decode)
                    .add();
        }
    }

    public static void clearQueue() {
        channelQueue.clear();
    }

    public static <MSG> void sendPluginMessage(MSG message, String channel, EntityPlayerMP player) {
        channels.get(channel)
                .sendTo(message, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
    }
}
