package dev.neuralnexus.taterlib.config.sections;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

/** A class for parsing server configurations. */
@ConfigSerializable
public class ServerConfig {
    @Setting private String name;

    /**
     * Get the name of the server.
     *
     * @return The name of the server.
     */
    public String name() {
        return name;
    }
}
