/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.reflecto;

import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;

import org.jetbrains.annotations.ApiStatus;

import java.util.Objects;

@ApiStatus.Experimental
public record ClassEntry(String name, MinecraftVersion version, String mappings) {
    private static final Mappings ENVIRONMENT = MetaAPI.instance().mappings();

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name = null;
        private MinecraftVersion version = MinecraftVersions.UNKNOWN;
        private String official = null;
        private String mojmap = null;
        private String spigot = null;
        private String legacySpigot = null;
        private String searge = null;
        private String legacySearge = null;
        private String mcp = null;
        private String yarn = null;
        private String yarnIntermediary = null;
        private String legacyIntermediary = null;
        private String babricIntermediary = null;
        private String calamus = null;
        private String hashed = null;

        private Builder() {}

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder version(MinecraftVersion version) {
            this.version = version;
            return this;
        }

        public Builder official(String official) {
            this.official = official;
            return this;
        }

        public Builder mojmap(String mojmap) {
            this.mojmap = mojmap;
            return this;
        }

        public Builder spigot(String spigot) {
            this.spigot = spigot;
            return this;
        }

        public Builder legacySpigot(String legacySpigot) {
            this.legacySpigot = legacySpigot;
            return this;
        }

        public Builder searge(String searge) {
            this.searge = searge;
            return this;
        }

        public Builder legacySearge(String legacySearge) {
            this.legacySearge = legacySearge;
            return this;
        }

        public Builder mcp(String mcp) {
            this.mcp = mcp;
            return this;
        }

        public Builder yarn(String yarn) {
            this.yarn = yarn;
            return this;
        }

        public Builder yarnIntermediary(String yarnIntermediary) {
            this.yarnIntermediary = yarnIntermediary;
            return this;
        }

        public Builder legacyIntermediary(String legacyIntermediary) {
            this.legacyIntermediary = legacyIntermediary;
            return this;
        }

        public Builder babricIntermediary(String babricIntermediary) {
            this.babricIntermediary = babricIntermediary;
            return this;
        }

        public Builder calamus(String calamus) {
            this.calamus = calamus;
            return this;
        }

        public Builder hashed(String hashed) {
            this.hashed = hashed;
            return this;
        }

        public ClassEntry build() {
            Objects.requireNonNull(this.name, "name");
            String mappings =
                    switch (ENVIRONMENT) {
                        case OFFICIAL -> this.official;
                        case MOJMAP -> this.mojmap;
                        case SPIGOT -> this.spigot;
                        case LEGACY_SPIGOT -> this.legacySpigot;
                        case SEARGE -> this.searge;
                        case LEGACY_SEARGE -> this.legacySearge;
                        case MCP -> this.mcp;
                        case YARN -> this.yarn;
                        case YARN_INTERMEDIARY -> this.yarnIntermediary;
                        case LEGACY_INTERMEDIARY -> this.legacyIntermediary;
                        case BABRIC_INTERMEDIARY -> this.babricIntermediary;
                        case CALAMUS -> this.calamus;
                        case HASHED -> this.hashed;
                        default -> null;
                    };
            return new ClassEntry(this.name, this.version, mappings);
        }
    }
}
