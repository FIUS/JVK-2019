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
     * 
     * @param entity
     *     the entity to check the inventory of
     * @param collectableType
     *     the collectable type to check the number of
     * @param numberPredicate
     *     the predicate to check the collectable count against
     * @return the created supplier
     */
    final public static Supplier<Boolean> testInventoryCount(
            final GreedyEntity entity, Class<? extends CollectableEntity> collectableType, final Predicate<Integer> numberPredicate
    ) {
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null!");
        if (collectableType == null) throw new IllegalArgumentException("Collectable Type cannot be null!");
        if (numberPredicate == null) throw new IllegalArgumentException("NumberPredicate cannot be null!");
        return () -> {
            int collectableCount = entity.getInventory().get(collectableType, true).size();
            return numberPredicate.test(collectableCount);
        };
    }
    
    /**
     * Get a new supplier that checks the number of Collectables of type {@link Coin} in the {@code entity} against the
     * given number predicate.
     * 
     * @param entity
     *     the entity to check the inventory of
     * @param numberPredicate
     *     the predicate to check the collectable count against
     * @return the created supplier
     */
    final public static Supplier<Boolean> testCoinCount(final GreedyEntity entity, final Predicate<Integer> numberPredicate) {
        return RequirementChecks.testInventoryCount(entity, Coin.class, numberPredicate);
    }
    
    /**
     * Get a new supplier that checks if two entities are on the same position.
     * 
     * @param a
     *     entity a
     * @param b
     *     entity b
     * @return the created supplier
     */
    final public static Supplier<Boolean> testEntitiesOnSameField(final Entity a, final Entity b) {
        return () -> {
            try {
                Position posA = a.getPosition();
                Position posB = b.getPosition();
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
