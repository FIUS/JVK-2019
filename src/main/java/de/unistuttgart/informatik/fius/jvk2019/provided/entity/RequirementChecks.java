/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 *
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.provided.entity;

import java.util.function.Predicate;
import java.util.function.Supplier;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.entity.CollectableEntity;
import de.unistuttgart.informatik.fius.icge.simulation.entity.Entity;
import de.unistuttgart.informatik.fius.icge.simulation.entity.GreedyEntity;
import de.unistuttgart.informatik.fius.icge.simulation.exception.EntityNotOnFieldException;


/**
 * Helper class holding requirement checks generators for the PhoneBooth.
 *
 * @author FabianBühler
 */
public class RequirementChecks {
    
    /**
     * Get a new supplier that returns true iff all clause suppliers are true.
     *
     * @param clauses
     *     the clause suppliers
     * @return the and supplier
     */
    final public static Supplier<Boolean> and(final Supplier<Boolean>... clauses) {
        if (clauses.length <= 0) throw new IllegalArgumentException("Cannot build AND of empty clause list!");
        return () -> {
            for (Supplier<Boolean> clause : clauses) {
                if (!clause.get()) {
                    return false;
                }
            }
            return true;
        };
    }
    
    /**
     * Get a new supplier that returns true iff at least one true clause supplier exists.
     *
     * @param clauses
     *     the clause suppliers
     * @return the or supplier
     */
    final public static Supplier<Boolean> or(final Supplier<Boolean>... clauses) {
        if (clauses.length <= 0) throw new IllegalArgumentException("Cannot build OR of empty clause list!");
        return () -> {
            for (Supplier<Boolean> clause : clauses) {
                if (clause.get()) {
                    return true;
                }
            }
            return false;
        };
    }
    
    /**
     * Get a new supplier that checks the number of Collectables of type {@code collectableType} in the {@code entity}
     * against the given number predicate.
     * <p>
     * The entity getter will be called for every evaluation of the supplier. If the entity getter returns null the
     * supplier returns false.
     * 
     * @param entityGetter
     *     the getter for the entity to check the inventory of
     * @param collectableType
     *     the collectable type to check the number of
     * @param numberPredicate
     *     the predicate to check the collectable count against
     * @return the created supplier
     */
    final public static Supplier<Boolean> testInventoryCount(
            final Supplier<GreedyEntity> entityGetter, Class<? extends CollectableEntity> collectableType,
            final Predicate<Integer> numberPredicate
    ) {
        if (entityGetter == null) throw new IllegalArgumentException("Entity cannot be null!");
        if (collectableType == null) throw new IllegalArgumentException("Collectable Type cannot be null!");
        if (numberPredicate == null) throw new IllegalArgumentException("NumberPredicate cannot be null!");
        return () -> {
            GreedyEntity entity = entityGetter.get();
            if (entity == null) return false;
            int collectableCount = entity.getInventory().get(collectableType, true).size();
            return numberPredicate.test(collectableCount);
        };
    }
    
    /**
     * Get a new supplier that checks the number of Collectables of type {@link Coin} in the {@code entity} against the
     * given number predicate.
     * <p>
     * The entity getter will be called for every evaluation of the supplier. If the entity getter returns null the
     * supplier returns false.
     * 
     * @param entityGetter
     *     the getter for the entity to check the inventory of
     * @param numberPredicate
     *     the predicate to check the collectable count against
     * @return the created supplier
     */
    final public static Supplier<Boolean> testCoinCount(
            final Supplier<GreedyEntity> entityGetter, final Predicate<Integer> numberPredicate
    ) {
        return RequirementChecks.testInventoryCount(entityGetter, Coin.class, numberPredicate);
    }
    
    /**
     * Get a new supplier that checks if two entities are on the same position.
     * <p>
     * The entity getters will be called for every evaluation of the supplier. If any entity getter returns null the
     * supplier returns false.
     * <p>
     * If any of the entities is not on a playfield the supplier returns null.
     * 
     * @param a
     *     the getter for entity a
     * @param b
     *     the getter for entity b
     * @return the created supplier
     */
    final public static Supplier<Boolean> testEntitiesOnSameField(final Supplier<Entity> a, final Supplier<Entity> b) {
        return () -> {
            try {
                Entity entA = a.get();
                Entity entB = b.get();
                if (entA == null || entB == null) return false;
                Position posA = entA.getPosition();
                Position posB = entB.getPosition();
                return posA.equals(posB);
            } catch (EntityNotOnFieldException e) {
                return false;
            }
        };
    }
    
    /**
     * Create a new greaterThan predicate.
     * 
     * @param compareValue
     *     the value to compare against
     * @return the predicate
     */
    public static Predicate<Integer> getGreaterThanPredicate(int compareValue) {
        return (number) -> number > compareValue;
    }
    
    /**
     * Create a new lesserThan predicate.
     * 
     * @param compareValue
     *     the value to compare against
     * @return the predicate
     */
    public static Predicate<Integer> getLesserThanPredicate(int compareValue) {
        return (number) -> number < compareValue;
    }
    
    /**
     * Create a new equalTo predicate.
     * 
     * @param compareValue
     *     the value to compare against
     * @return the predicate
     */
    public static Predicate<Integer> getEqualToPredicate(int compareValue) {
        return (number) -> number == compareValue;
    }
}
