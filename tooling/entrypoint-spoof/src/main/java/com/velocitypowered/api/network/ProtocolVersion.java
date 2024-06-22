package com.velocitypowered.api.network;

import com.velocitypowered.api.util.Ordered;

public enum ProtocolVersion implements Ordered<ProtocolVersion> {
    UNKNOWN(-1, new String[] {"Unknown"}) {
        public boolean isUnknown() {
            return true;
        }

        public boolean isSupported() {
            return false;
        }
    },
    LEGACY(-2, new String[] {"Legacy"}) {
        public boolean isLegacy() {
            return true;
        }

        public boolean isSupported() {
            return false;
        }
    };

    public static final ProtocolVersion MAXIMUM_VERSION = values()[values().length - 1];
    private final int protocol;
    private final int snapshotProtocol;
    private final String[] names;

    private ProtocolVersion(int protocol, String... names) {
        this(protocol, -1, names);
    }

    private ProtocolVersion(int protocol, int snapshotProtocol, String... names) {
        if (snapshotProtocol != -1) {
            this.snapshotProtocol = 1073741824 | snapshotProtocol;
        } else {
            this.snapshotProtocol = -1;
        }

        this.protocol = protocol;
        this.names = names;
    }
}
