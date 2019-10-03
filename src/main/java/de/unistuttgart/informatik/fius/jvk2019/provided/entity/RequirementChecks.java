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

import java.util.function.Supplier;

/**
 * Helper class holding requirement checks generators for the PhoneBooth.
 *
 * @author FabianBühler
 */
public class RequirementChecks {

    /**
     * Get a new supplier that returns true iff all clause suppliers are true.
     *
     * @param clauses the clause suppliers
     * @return the and supplier
     */
    final public static Supplier<Boolean> and(final Supplier<Boolean>... clauses) {
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
     * @param clauses the clause suppliers
     * @return the or supplier
     */
    final public static Supplier<Boolean> or(final Supplier<Boolean>... clauses) {
        return () -> {
            for (Supplier<Boolean> clause : clauses) {
                if (clause.get()) {
                    return true;
                }
            }
            return false;
        };
    }
}
