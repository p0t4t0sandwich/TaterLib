/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.version.meta;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.ProtocolType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/** The metadata store for versions */
public final class MetaStore {
    private static final Logger logger = Logger.create("MetaStore");
    private static final File VERSION_META_CSV = Paths.get("version_meta.csv").toFile();
    private static final Map<String, MinecraftVersion.Meta> metaCache = new ConcurrentHashMap<>();

    // readable,version,protocol_version,protocol_type,release_type,data_version,resource_pack_format,data_pack_format,notes
    /**
     * Get the metadata for a specific version
     *
     * @param version The version
     * @return The meta
     */
    public static MinecraftVersion.Meta getMeta(MinecraftVersion version) {
        String verStr = version.asString();
        if (metaCache.containsKey(verStr)) {
            return metaCache.get(verStr);
        }
        try {
            Optional<String> metaStr =
                    new BufferedReader(new FileReader(VERSION_META_CSV))
                            .lines()
                            .filter(line -> line.contains(verStr))
                            .findFirst();
            if (metaStr.isPresent()) {
                String[] parts = metaStr.get().split(",");
                int protocol = Integer.parseInt(parts[2]);
                ProtocolType protocolType = ProtocolType.valueOf(parts[3]);
                MinecraftVersion.Type type = MinecraftVersion.Type.valueOf(parts[4]);
                int dataVersion = Integer.parseInt(parts[5]);
                int resourcePackFormat = Integer.parseInt(parts[6]);
                int dataPackFormat = Integer.parseInt(parts[7]);
                MinecraftVersion.Meta meta =
                        new MinecraftVersionMetaImpl(
                                protocol,
                                protocolType,
                                type,
                                dataVersion,
                                resourcePackFormat,
                                dataPackFormat);
                metaCache.put(verStr, meta);
                return meta;
            }
        } catch (FileNotFoundException e) {
            logger.error("version_meta.csv not found", e);
        }
        return MinecraftVersion.Meta.UNKNOWN;
    }
}
