package dev.neuralnexus.taterlib.forge.networking;

import dev.neuralnexus.taterlib.forge.networking.packet.ForgeMessagePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ModMessages {
    private static final String PROTOCOL_VERSION = "1";
    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }
    private static final Set<String> channelQueue = new HashSet<>();
    private static final HashMap<String, SimpleChannel> channels = new HashMap<>();

    public static void addChannels(Set<String> channel) {
        for (String c : channel) {
            System.out.println("-------------------------------Adding channel: " + c);
            channelQueue.add(c);
        }
    }

    public static void register() {
        for (String c : channelQueue) {
            String[] channelParts = c.split(":");
            channels.put(c, NetworkRegistry.ChannelBuilder.named(
                            new ResourceLocation(channelParts[0], channelParts[1]))
                    .networkProtocolVersion(() -> PROTOCOL_VERSION)
                    .clientAcceptedVersions(s -> true)
                    .serverAcceptedVersions(s -> true)
                    .simpleChannel());
            channels.get(c).messageBuilder(ForgeMessagePacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                    .encoder(ForgeMessagePacket::encode)
                    .decoder(ForgeMessagePacket::decode)
                    .add();
        }
    }

    public static <MSG> void sendPluginMessage(MSG message, String channel, ServerPlayer player) {
        channels.get(channel).sendTo(message, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }
}
