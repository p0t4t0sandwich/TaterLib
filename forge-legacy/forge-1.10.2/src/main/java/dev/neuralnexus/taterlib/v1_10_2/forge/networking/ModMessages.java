package dev.neuralnexus.taterlib.v1_10_2.forge.networking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class ModMessages {
  private static final String PROTOCOL_VERSION = "1";
  private static final Set<String> channelQueue = new HashSet<>();
  private static final HashMap<String, SimpleNetworkWrapper> channels = new HashMap<>();
  private static int packetId = 0;

  private static int id() {
    return packetId++;
  }

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
    //        channels.get(channel).sendTo(message, player.connection.netManager,
    // NetworkDirection.PLAY_TO_CLIENT);
  }
}
