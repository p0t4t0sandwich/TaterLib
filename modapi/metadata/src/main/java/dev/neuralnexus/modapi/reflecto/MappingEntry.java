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
    private static final MinecraftVersion VERSION = MetaAPI.instance().version();

    public static Builder builder(@NotNull String name) {
        return new Builder(name);
    }

    public static final class Builder {
        private final String name;
        private String parentEntry;
        private String mappings;
        private MinecraftVersion version = MinecraftVersions.UNKNOWN;
        private MinecraftVersion minVersion = null;
        private MinecraftVersion maxVersion = null;

        private Builder(@NotNull String name) {
            this.name = Objects.requireNonNull(name, "name cannot be null");
        }

        /**
         * Set the parent entry of this mapping entry <br>
         * This is used to tie Method and Field mappings to their parent class
         *
         * @param parent The parent entry
         * @return The builder
         */
        public Builder parentEntry(@NotNull String parent) {
            this.parentEntry = Objects.requireNonNull(parent, "parentEntry cannot be null");
            return this;
        }

        /**
         * Set the version required for this mapping entry
         *
         * @param version The version
         * @return The builder
         */
        public Builder version(@NotNull MinecraftVersion version) {
            this.version = Objects.requireNonNull(version, "version cannot be null");
            return this;
        }

        /**
         * Set the version range required for this mapping entry
         *
         * @param minVersion The minimum version, use {@link MinecraftVersions#UNKNOWN} to indicate
         *     no minimum
         * @param maxVersion The maximum version, use {@link MinecraftVersions#UNKNOWN} to indicate
         *     no maximum
         * @return The builder
         */
        public Builder versionRange(
                @NotNull MinecraftVersion minVersion, @NotNull MinecraftVersion maxVersion) {
            this.minVersion =
                    Objects.requireNonNull(
                            minVersion,
                            "minVersion cannot be null, use MinecraftVersions.UNKNOWN to indicate no minimum version");
            this.maxVersion =
                    Objects.requireNonNull(
                            maxVersion,
                            "maxVersion cannot be null, use MinecraftVersions.UNKNOWN to indicate no maximum version");
            return this;
        }

        public Builder official(String mappings) {
            if (ENVIRONMENT == Mappings.OFFICIAL) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder official(String mappings, @NotNull MinecraftVersion version) {
            if (ENVIRONMENT == Mappings.OFFICIAL && VERSION.is(version)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder official(
                String mappings,
                @NotNull MinecraftVersion minVersion,
                @NotNull MinecraftVersion maxVersion) {
            if (ENVIRONMENT == Mappings.OFFICIAL && VERSION.isInRange(minVersion, maxVersion)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder mojang(String mappings) {
            if (ENVIRONMENT == Mappings.MOJMAP) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder mojang(String mappings, @NotNull MinecraftVersion version) {
            if (ENVIRONMENT == Mappings.MOJMAP && VERSION.is(version)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder mojang(
                String mappings,
                @NotNull MinecraftVersion minVersion,
                @NotNull MinecraftVersion maxVersion) {
            if (ENVIRONMENT == Mappings.MOJMAP && VERSION.isInRange(minVersion, maxVersion)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder spigot(String mappings) {
            if (ENVIRONMENT == Mappings.SPIGOT) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder spigot(String mappings, @NotNull MinecraftVersion version) {
            if (ENVIRONMENT == Mappings.SPIGOT && VERSION.is(version)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder spigot(
                String mappings,
                @NotNull MinecraftVersion minVersion,
                @NotNull MinecraftVersion maxVersion) {
            if (ENVIRONMENT == Mappings.SPIGOT && VERSION.isInRange(minVersion, maxVersion)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder legacySpigot(String mappings) {
            if (ENVIRONMENT == Mappings.LEGACY_SPIGOT) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder legacySpigot(String mappings, @NotNull MinecraftVersion version) {
            if (ENVIRONMENT == Mappings.LEGACY_SPIGOT && VERSION.is(version)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder legacySpigot(
                String mappings,
                @NotNull MinecraftVersion minVersion,
                @NotNull MinecraftVersion maxVersion) {
            if (ENVIRONMENT == Mappings.LEGACY_SPIGOT
                    && VERSION.isInRange(minVersion, maxVersion)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder searge(String mappings) {
            if (ENVIRONMENT == Mappings.SEARGE) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder searge(String mappings, @NotNull MinecraftVersion version) {
            if (ENVIRONMENT == Mappings.SEARGE && VERSION.is(version)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder searge(
                String mappings,
                @NotNull MinecraftVersion minVersion,
                @NotNull MinecraftVersion maxVersion) {
            if (ENVIRONMENT == Mappings.SEARGE && VERSION.isInRange(minVersion, maxVersion)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder legacySearge(String mappings) {
            if (ENVIRONMENT == Mappings.LEGACY_SEARGE) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder legacySearge(String mappings, @NotNull MinecraftVersion version) {
            if (ENVIRONMENT == Mappings.LEGACY_SEARGE && VERSION.is(version)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder legacySearge(
                String mappings,
                @NotNull MinecraftVersion minVersion,
                @NotNull MinecraftVersion maxVersion) {
            if (ENVIRONMENT == Mappings.LEGACY_SEARGE
                    && VERSION.isInRange(minVersion, maxVersion)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder mcp(String mappings) {
            if (ENVIRONMENT == Mappings.MCP) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder mcp(String mappings, @NotNull MinecraftVersion version) {
            if (ENVIRONMENT == Mappings.MCP && VERSION.is(version)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder mcp(
                String mappings,
                @NotNull MinecraftVersion minVersion,
                @NotNull MinecraftVersion maxVersion) {
            if (ENVIRONMENT == Mappings.MCP && VERSION.isInRange(minVersion, maxVersion)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder yarn(String mappings) {
            if (ENVIRONMENT == Mappings.YARN) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder yarn(String mappings, @NotNull MinecraftVersion version) {
            if (ENVIRONMENT == Mappings.YARN && VERSION.is(version)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder yarn(
                String mappings,
                @NotNull MinecraftVersion minVersion,
                @NotNull MinecraftVersion maxVersion) {
            if (ENVIRONMENT == Mappings.YARN && VERSION.isInRange(minVersion, maxVersion)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder yarnIntermediary(String mappings) {
            if (ENVIRONMENT == Mappings.YARN_INTERMEDIARY) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder yarnIntermediary(String mappings, @NotNull MinecraftVersion version) {
            if (ENVIRONMENT == Mappings.YARN_INTERMEDIARY && VERSION.is(version)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder yarnIntermediary(
                String mappings,
                @NotNull MinecraftVersion minVersion,
                @NotNull MinecraftVersion maxVersion) {
            if (ENVIRONMENT == Mappings.YARN_INTERMEDIARY
                    && VERSION.isInRange(minVersion, maxVersion)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder legacyIntermediary(String mappings) {
            if (ENVIRONMENT == Mappings.LEGACY_INTERMEDIARY) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder legacyIntermediary(String mappings, @NotNull MinecraftVersion version) {
            if (ENVIRONMENT == Mappings.LEGACY_INTERMEDIARY && VERSION.is(version)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder legacyIntermediary(
                String mappings,
                @NotNull MinecraftVersion minVersion,
                @NotNull MinecraftVersion maxVersion) {
            if (ENVIRONMENT == Mappings.LEGACY_INTERMEDIARY
                    && VERSION.isInRange(minVersion, maxVersion)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder babricIntermediary(String mappings) {
            if (ENVIRONMENT == Mappings.BABRIC_INTERMEDIARY) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder babricIntermediary(String mappings, @NotNull MinecraftVersion version) {
            if (ENVIRONMENT == Mappings.BABRIC_INTERMEDIARY && VERSION.is(version)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder babricIntermediary(
                String mappings,
                @NotNull MinecraftVersion minVersion,
                @NotNull MinecraftVersion maxVersion) {
            if (ENVIRONMENT == Mappings.BABRIC_INTERMEDIARY
                    && VERSION.isInRange(minVersion, maxVersion)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder calamus(String mappings) {
            if (ENVIRONMENT == Mappings.CALAMUS) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder calamus(String mappings, @NotNull MinecraftVersion version) {
            if (ENVIRONMENT == Mappings.CALAMUS && VERSION.is(version)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder calamus(
                String mappings,
                @NotNull MinecraftVersion minVersion,
                @NotNull MinecraftVersion maxVersion) {
            if (ENVIRONMENT == Mappings.CALAMUS && VERSION.isInRange(minVersion, maxVersion)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder hashed(String mappings) {
            if (ENVIRONMENT == Mappings.HASHED) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder hashed(String mappings, @NotNull MinecraftVersion version) {
            if (ENVIRONMENT == Mappings.HASHED && VERSION.is(version)) {
                this.mappings = mappings;
            }
            return this;
        }

        public Builder hashed(
                String mappings,
                @NotNull MinecraftVersion minVersion,
                @NotNull MinecraftVersion maxVersion) {
            if (ENVIRONMENT == Mappings.HASHED && VERSION.isInRange(minVersion, maxVersion)) {
                this.mappings = mappings;
            }
            return this;
        }

        public MappingEntry build() throws NullPointerException {
            Objects.requireNonNull(
                    mappings,
                    "mappings are null for MappingEntry " + this.name + " in " + ENVIRONMENT);
            return new MappingEntry(
                    this.name,
                    Optional.ofNullable(this.parentEntry),
                    this.version,
                    this.minVersion,
                    this.maxVersion,
                    this.mappings);
        }
    }
}
