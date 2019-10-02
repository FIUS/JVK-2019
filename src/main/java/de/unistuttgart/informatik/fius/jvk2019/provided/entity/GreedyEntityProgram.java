/*
 * This source file is part of the FIUS ICGE project.
 * For more information see github.com/FIUS/ICGE2
 *
 * Copyright (c) 2019 the ICGE project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.provided.entity;

import de.unistuttgart.informatik.fius.icge.simulation.entity.Entity;
import de.unistuttgart.informatik.fius.icge.simulation.entity.GreedyEntity;
import de.unistuttgart.informatik.fius.icge.simulation.entity.program.EntityProgram;


/**
 * A program for neo
 * 
 * @author Tim Neumann
 */
public abstract class GreedyEntityProgram implements EntityProgram {
    
    /**
     * Run this program on the given greedy entity.
     * 
     * @param entity
     *     The greedy entity to run this program on
     */
    public abstract void run(GreedyEntity entity);
    
    @Override
    public void run(final Entity entity) {
        if (entity instanceof GreedyEntity) {
            this.run((GreedyEntity) entity);
        } else throw new IllegalArgumentException("Cannot run on that entity.");
    }
    
    @Override
    public boolean canRunOn(final Entity entity) {
        if (entity instanceof GreedyEntity) return true;
        return false;
    }
}
