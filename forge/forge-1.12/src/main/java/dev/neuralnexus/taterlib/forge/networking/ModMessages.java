package dev.neuralnexus.taterlib.forge.networking;

import dev.neuralnexus.taterlib.forge.networking.packet.ForgeMessagePacket;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

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
    private static final HashMap<String, SimpleNetworkWrapper> channels = new HashMap<>();

    public static void addChannels(Set<String> channel) {
        channelQueue.addAll(channel);
    }

    public static void register() {
        SimpleNetworkWrapper wrapper = NetworkRegistry.INSTANCE.newSimpleChannel("MYCHANNEL");

        for (String c : channelQueue) {
            String[] channelParts = c.split(":");
//            channels.put(c, NetworkRegistry.INSTANCE.newSimpleChannel(c)
//                    .networkProtocolVersion(() -> PROTOCOL_VERSION)
//                    .clientAcceptedVersions(s -> true)
//                    .serverAcceptedVersions(s -> true)
//                    .simpleChannel());
//            channels.get(c).registerMessage(id(),
//                    ForgeMessagePacket.class,
//                    ForgeMessagePacket::encode,
//                    ForgeMessagePacket::decode,
//                    Side.SERVER
//            );
        }
    }

    public static void clearQueue() {
        channelQueue.clear();
    }

    public static <MSG> void sendPluginMessage(MSG message, String channel, EntityPlayerMP player) {
//        channels.get(channel).sendTo(message, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
    }
}
