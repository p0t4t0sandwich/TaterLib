/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.storage.datastores.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import dev.neuralnexus.taterapi.player.SimplePlayer;
import dev.neuralnexus.taterapi.storage.databases.Database;
import dev.neuralnexus.taterapi.storage.databases.Filesystem;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/** Player data store for attaching metadata to players */
public class PlayerDataStore {
    private final Filesystem database;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Path fileParent;

    /**
     * Constructor for the PlayerDataStore class
     *
     * @param config The database config
     */
    public PlayerDataStore(Database.DatabaseConfig config) {
        this.database = new Filesystem(config);
        this.fileParent = Paths.get(database.connection() + File.separator + database.database());
        if (!Files.exists(fileParent)) {
            try {
                Files.createDirectories(fileParent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the path to the player data file.
     *
     * @param uuid The UUID of the player.
     * @return The path to the player data file.
     */
    private Path getDataPath(UUID uuid) throws IOException {
        Path file = Paths.get(fileParent + File.separator + uuid + ".json");
        if (!Files.exists(file)) {
            Files.createFile(file);
            Files.write(file, "{}".getBytes());
        }
        return file;
    }

    /**
     * Read a file.
     *
     * @param uuid The UUID of the player.
     * @param key The key to read.
     * @param clazz The class of the object to read.
     * @return The contents of the file.
     */
    @SuppressWarnings("unchecked")
    private <T> Optional<T> read(UUID uuid, String key, Class<T> clazz) {
        try {
            Path file = getDataPath(uuid);
            String read = new String(Files.readAllBytes(file));
            Map<String, Object> map = gson.fromJson(read, Map.class);
            if (map.containsKey(key)) {
                if (clazz != null) {
                    String value = gson.toJson(map.get(key));
                    return Optional.of(gson.fromJson(value, clazz));
                }
                return (Optional<T>) Optional.of(map.get(key));
            } else {
                return Optional.empty();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Read a file.
     *
     * @param uuid The UUID of the player.
     * @param key The key to read.
     * @param type The type of the object to read.
     * @return The contents of the file.
     */
    @SuppressWarnings("unchecked")
    private <T> Optional<T> read(UUID uuid, String key, Type type) {
        try {
            Path file = getDataPath(uuid);
            String read = new String(Files.readAllBytes(file));
            Map<String, Object> map = gson.fromJson(read, Map.class);
            if (map.containsKey(key)) {
                String value = gson.toJson(map.get(key));
                return Optional.of(gson.fromJson(value, type));
            } else {
                return Optional.empty();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Read a file.
     *
     * @param uuid The UUID of the player.
     * @param key The key to read.
     * @return The contents of the file.
     */
    private Optional<Object> read(UUID uuid, String key) {
        return read(uuid, key, null);
    }

    /**
     * Write to a file.
     *
     * @param uuid The UUID of the player.
     * @param key The key to write.
     * @param value The value to write.
     */
    private void write(UUID uuid, String key, Object value) {
        try {
            Path file = getDataPath(uuid);
            String read = new String(Files.readAllBytes(file));
            Map<String, Object> map =
                    gson.fromJson(read, new TypeToken<Map<String, Object>>() {}.getType());
            map.put(key, value);
            Files.write(file, gson.toJson(map).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a value from the player data store.
     *
     * @param uuid The UUID of the player.
     * @param key The key to delete.
     */
    private void delete(UUID uuid, String key) {
        try {
            Path file = getDataPath(uuid);
            String read = new String(Files.readAllBytes(file));
            Map<String, Object> map =
                    gson.fromJson(read, new TypeToken<Map<String, Object>>() {}.getType());
            map.remove(key);
            Files.write(file, gson.toJson(map).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a value from the player data store.
     *
     * @param key The key to get.
     * @return The value.
     */
    public Optional<Object> get(SimplePlayer player, String key) {
        return read(player.uuid(), key);
    }

    /**
     * Get a value from the player data store.
     *
     * @param key The key to get.
     * @param clazz The class of the object to get.
     * @return The value.
     */
    public <T> Optional<T> get(SimplePlayer player, String key, Class<T> clazz) {
        return read(player.uuid(), key, clazz);
    }

    /**
     * Get a value from the player data store.
     *
     * @param key The key to get.
     * @param type The type of the object to get.
     * @return The value.
     */
    public <T> Optional<T> get(SimplePlayer player, String key, Type type) {
        return read(player.uuid(), key, type);
    }

    /**
     * Set a value in the player data store.
     *
     * @param key The key to set.
     * @param value The value to set.
     */
    public void set(SimplePlayer player, String key, Object value) {
        write(player.uuid(), key, value);
    }

    /**
     * Delete a value from the player data store.
     *
     * @param key The key to delete.
     */
    public void delete(SimplePlayer player, String key) {
        delete(player.uuid(), key);
    }
}
