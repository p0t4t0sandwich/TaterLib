package dev.neuralnexus.taterlib.entity;

/** Represents an entity that has health and can take damage. */
public interface Damageable extends Entity {
    /**
     * Deals the given amount of damage to this entity.
     *
     * @param amount Amount of damage to deal
     */
    void damage(double amount);

    /**
     * Deals the given amount of damage to this entity.
     *
     * @param amount Amount of damage to deal
     * @param source Entity that caused the damage
     */
    void damage(double amount, Entity source);

    /**
     * Gets the entity's current health.
     *
     * @return The current health of the entity
     */
    double health();

    /**
     * Sets the entity's health.
     *
     * @param health New health of the entity
     */
    void setHealth(double health);

    /**
     * Gets the entity's absorption amount.
     *
     * @return The absorption amount of the entity
     */
    double absorptionAmount();

    /**
     * Sets the entity's absorption amount.
     *
     * @param amount New absorption amount of the entity
     */
    void setAbsorptionAmount(double amount);

    /**
     * Gets the maximum health this entity has.
     *
     * @return The maximum health of this entity
     */
    double maxHealth();

    /**
     * Sets the maximum health this entity has.
     *
     * @param health New maximum health of this entity
     */
    void setMaxHealth(double health);
}
