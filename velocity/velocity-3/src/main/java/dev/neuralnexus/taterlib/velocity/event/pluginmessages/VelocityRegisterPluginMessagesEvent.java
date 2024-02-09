package dev.neuralnexus.taterlib.velocity.event.pluginmessages;

import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;

import dev.neuralnexus.taterlib.event.network.RegisterPluginMessagesEvent;
import dev.neuralnexus.taterlib.velocity.VelocityTaterLibPlugin;

import java.util.Set;

/** Velocity implementation of {@link RegisterPluginMessagesEvent}. */
public class VelocityRegisterPluginMessagesEvent implements RegisterPluginMessagesEvent {
    /** {@inheritDoc} */
    @Override
    public void registerPluginChannel(String channel) {
        VelocityTaterLibPlugin.getProxyServer()
                .getChannelRegistrar()
                .register(MinecraftChannelIdentifier.from(channel));
    }

    /** {@inheritDoc} */
    @Override
    public void registerPluginChannels(Set<String> channels) {
        channels.forEach(this::registerPluginChannel);
    }
}
