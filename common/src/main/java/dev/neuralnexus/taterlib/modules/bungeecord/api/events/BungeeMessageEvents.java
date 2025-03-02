/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.modules.bungeecord.api.events;

import dev.neuralnexus.taterapi.event.api.EventManager;

/** BungeeCord message events. */
public class BungeeMessageEvents {
    public static final EventManager<IPRespEvent> IP = new EventManager<>(IPRespEvent.class);
    public static final EventManager<IPOtherRespEvent> IP_OTHER =
            new EventManager<>(IPOtherRespEvent.class);
    public static final EventManager<PlayerCountRespEvent> PLAYER_COUNT =
            new EventManager<>(PlayerCountRespEvent.class);
    public static final EventManager<PlayerListRespEvent> PLAYER_LIST =
            new EventManager<>(PlayerListRespEvent.class);
    public static final EventManager<GetPlayerServerRespEvent> GET_PLAYER_SERVER =
            new EventManager<>(GetPlayerServerRespEvent.class);
    public static final EventManager<GetServersRespEvent> GET_SERVERS =
            new EventManager<>(GetServersRespEvent.class);
    public static final EventManager<GetServerRespEvent> GET_SERVER =
            new EventManager<>(GetServerRespEvent.class);
    public static final EventManager<ForwardRespEvent> FORWARD =
            new EventManager<>(ForwardRespEvent.class);
    public static final EventManager<ForwardToPlayerRespEvent> FORWARD_TO_PLAYER =
            new EventManager<>(ForwardToPlayerRespEvent.class);
    public static final EventManager<UUIDRespEvent> UUID = new EventManager<>(UUIDRespEvent.class);
    public static final EventManager<UUIDOtherRespEvent> UUID_OTHER =
            new EventManager<>(UUIDOtherRespEvent.class);
    public static final EventManager<ServerIPRespEvent> SERVER_IP =
            new EventManager<>(ServerIPRespEvent.class);
}
