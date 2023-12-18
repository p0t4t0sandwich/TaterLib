package dev.neuralnexus.taterlib.player;

/** Enum for the different game modes */
public enum GameMode {
    SURVIVAL(0),
    CREATIVE(1),
    ADVENTURE(2),
    SPECTATOR(3);

    private final int id;

    GameMode(final int id) {
        this.id = id;
    }

    /**
     * Get the game mode from the id
     *
     * @param id The id of the game mode
     * @return The game mode
     */
    public static GameMode fromId(final int id) {
        for (final GameMode gameMode : values()) {
            if (gameMode.getId() == id) {
                return gameMode;
            }
        }
        return null;
    }

    /**
     * Get the game mode from the name
     *
     * @param name The name of the game mode
     * @return The game mode
     */
    public static GameMode fromName(final String name) {
        for (final GameMode gameMode : values()) {
            if (gameMode.name().equalsIgnoreCase(name)) {
                return gameMode;
            }
        }
        return null;
    }

    /**
     * Get the id of the game mode
     *
     * @return The id of the game mode
     */
    public int getId() {
        return this.id;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.name();
    }
}
