package dev.neuralnexus.taterlib.neoforge.networking;

import dev.neuralnexus.taterlib.neoforge.networking.packet.NeoForgeMessagePacket;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.NetworkRegistry;
import net.neoforged.neoforge.network.PlayNetworkDirection;
import net.neoforged.neoforge.network.simple.SimpleChannel;

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
            channels.put(
                    c,
                    NetworkRegistry.ChannelBuilder.named(
                                    new ResourceLocation(channelParts[0], channelParts[1]))
                            .networkProtocolVersion(() -> PROTOCOL_VERSION)
                            .clientAcceptedVersions(s -> true)
                            .serverAcceptedVersions(s -> true)
                            .simpleChannel());
            channels.get(c)
                    .messageBuilder(
                            NeoForgeMessagePacket.class, id(), PlayNetworkDirection.PLAY_TO_CLIENT)
                    .encoder(NeoForgeMessagePacket::encode)
                    .decoder(NeoForgeMessagePacket::decode)
                    .add();
        }
    }

    public static void clearQueue() {
        channelQueue.clear();
    }

    public static <MSG> void sendPluginMessage(MSG message, String channel, ServerPlayer player) {
        channels.get(channel)
                .sendTo(message, player.connection.connection, PlayNetworkDirection.PLAY_TO_CLIENT);
    }
}
