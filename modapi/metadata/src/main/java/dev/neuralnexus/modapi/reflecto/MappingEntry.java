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
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

@ApiStatus.Experimental
public record MappingEntry(
        String name,
        Optional<String> parentEntry,
        MinecraftVersion version,
        MinecraftVersion minVersion,
        MinecraftVersion maxVersion,
        String mappings) {
    private static final Mappings ENVIRONMENT = MetaAPI.instance().mappings();

    public static Builder builder(@NotNull String name) {
        return new Builder(name);
    }

    public static final class Builder {
        private final String name;
        private String parentEntry;
        private MinecraftVersion version = MinecraftVersions.UNKNOWN;
        private MinecraftVersion minVersion = null;
        private MinecraftVersion maxVersion = null;
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

        private Builder(@NotNull String name) {
            this.name = Objects.requireNonNull(name, "name cannot be null");
        }

        public Builder parentEntry(@NotNull String parent) {
            this.parentEntry = Objects.requireNonNull(parent, "parentEntry cannot be null");
            return this;
        }

        public Builder version(@NotNull MinecraftVersion version) {
            this.version = Objects.requireNonNull(version, "version cannot be null");
            return this;
        }

        public Builder versionRange(
                @NotNull MinecraftVersion minVersion, @NotNull MinecraftVersion maxVersion) {
            this.minVersion =
                    Objects.requireNonNull(
                            minVersion,
                            "minVersion cannot be null, use MinecraftVersions.UNKNOWN to indicate no minimum version");
            ;
            this.maxVersion =
                    Objects.requireNonNull(
                            maxVersion,
                            "maxVersion cannot be null, use MinecraftVersions.UNKNOWN to indicate no maximum version");
            ;
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

        public MappingEntry build() throws NullPointerException {
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
            Objects.requireNonNull(
                    mappings,
                    "mappings are null for MappingEntry " + this.name + " in " + ENVIRONMENT);
            return new MappingEntry(
                    this.name,
                    Optional.ofNullable(this.parentEntry),
                    this.version,
                    this.minVersion,
                    this.maxVersion,
                    mappings);
        }
    }
}
