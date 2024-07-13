/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.entity.player;

/** Enum for the different game modes */
public enum GameMode {
    UNKNOWN(-1, "", ""),
    SURVIVAL(0, "survival", "s"),
    CREATIVE(1, "creative", "c"),
    ADVENTURE(2, "adventure", "a"),
    SPECTATOR(3, "spectator", "sp");

    private final int id;
    private final String name;
    private final String alias;

    GameMode(final int id, final String name, final String alias) {
        this.id = id;
        this.name = name;
        this.alias = alias;
    }

    /**
     * Gets the game mode from its ID.
     *
     * @param id The ID of the game mode.
     * @return The game mode.
     */
    public static GameMode fromId(final int id) {
        for (final GameMode gameMode : GameMode.values()) {
            if (gameMode.id() == id) {
                return gameMode;
            }
        }
        return UNKNOWN;
    }

    /**
     * Gets the game mode from its name.
     *
     * @param name The name of the game mode.
     * @return The game mode.
     */
    public static GameMode fromName(final String name) {
        for (final GameMode gameMode : GameMode.values()) {
            if (gameMode.gameModeName().equalsIgnoreCase(name)) {
                return gameMode;
            }
        }
        return UNKNOWN;
    }

    /**
     * Gets the game mode from its alias.
     *
     * @param alias The alias of the game mode.
     * @return The game mode.
     */
    public static GameMode fromAlias(final String alias) {
        for (final GameMode gameMode : GameMode.values()) {
            if (gameMode.alias().equalsIgnoreCase(alias)) {
                return gameMode;
            }
        }
        return UNKNOWN;
    }

    /**
     * Gets the game mode from an object.
     *
     * @param object The object to get the game mode from.
     * @return The game mode.
     */
    public static GameMode from(final Object object) {
        GameMode gameMode = UNKNOWN;
        if (object instanceof Integer) {
            gameMode = fromId((int) object);
        } else if (object instanceof String) {
            gameMode = fromName((String) object);
            if (gameMode == UNKNOWN) {
                gameMode = fromAlias((String) object);
            }
            if (gameMode == UNKNOWN) {
                try {
                    gameMode = fromId(Integer.parseInt((String) object));
                } catch (final NumberFormatException ignored) {
                }
            }
        }
        return gameMode;
    }

    /**
     * Gets the ID of the game mode.
     *
     * @return The ID of the game mode.
     */
    public int id() {
        return this.id;
    }

    /**
     * Gets the name of the game mode.
     *
     * @return The name of the game mode.
     */
    public String gameModeName() {
        return this.name;
    }

    /**
     * Gets the alias of the game mode.
     *
     * @return The alias of the game mode.
     */
    public String alias() {
        return this.alias;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
