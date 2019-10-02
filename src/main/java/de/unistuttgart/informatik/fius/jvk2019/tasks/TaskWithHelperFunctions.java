/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.tasks;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import de.unistuttgart.informatik.fius.icge.simulation.Playfield;
import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.entity.Entity;
import de.unistuttgart.informatik.fius.icge.simulation.entity.GreedyEntity;
import de.unistuttgart.informatik.fius.icge.simulation.entity.program.EntityProgram;
import de.unistuttgart.informatik.fius.icge.simulation.entity.program.EntityProgramRunner;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Wall;


/**
 * Task template with helper functions useful for all tasks.
 * 
 * @author Fabian Bühler
 */
public abstract class TaskWithHelperFunctions implements Task {
    
    /**
     * The simulation object for this Task.
     */
    protected Simulation sim;
    
    @Override
    public void prepare(Simulation sim) {
        this.sim = sim;
    }
    
    /**
     * Generate a solid wall around the rectangle (0,0) (width,height).
     * 
     * @param width
     *     of the inner area in cells
     * @param height
     *     of the inner area in cells
     */
    protected void generateCage(final int width, final int height) {
        if (width < 1) throw new IllegalArgumentException("Width must be >= 1!");
        if (height < 1) throw new IllegalArgumentException("Height must be >= 1!");
        
        final Playfield field = this.sim.getPlayfield();
        for (int x = -1; x <= width; x++) {
            field.addEntity(new Position(x, -1), new Wall());
            field.addEntity(new Position(x, height), new Wall());
        }
        for (int y = 0; y < height; y++) {
            field.addEntity(new Position(-1, y), new Wall());
            field.addEntity(new Position(width, y), new Wall());
        }
    }
    
    /**
     * Register a program instance in the program registry.
     * 
     * @param programName
     *     the name to register the program instance under
     * @param program
     *     the program instance
     * @return the programName for later use
     */
    protected String registerProgram(String programName, EntityProgram program) {
        if (program == null) throw new IllegalArgumentException("The provided program instance cannot be null!");
        this.sim.getEntityProgramRegistry().registerEntityProgram(programName, program);
        return programName;
    }
    
    /**
     * Register a program class with an autogenerated supplier in the program registry.
     * 
     * @param programName
     *     the name to register the program class under
     * @param programClass
     *     the program class
     * @return the programName for later use
     */
    protected String registerProgram(String programName, Class<? extends EntityProgram> programClass) {
        if (programClass == null) throw new IllegalArgumentException("The provided program class cannot be null!");
        final Supplier<EntityProgram> s = () -> {
            try {
                return programClass.getDeclaredConstructor().newInstance();
            } catch (
                    NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e
            ) {
                e.printStackTrace();
                // could not instantiate a new program
                return null;
            }
        };
        // test if supplier works
        if (s.get() == null) throw new IllegalArgumentException("The provided program class has no constructor that takes no arguments!");
        this.sim.getEntityProgramRegistry().registerManyEntityProgram(programName, s);
        return programName;
    }
    
    /**
     * Bind a program to an entity.
     * 
     * The entity must not be bound to a program already!
     * 
     * @param programName
     *     the registered name of the program or program class
     * @param entity
     *     the entity to bind the program to
     */
    protected void bindProgramToEntity(String programName, Entity entity) {
        if (
            programName == null || programName.equals("")
        ) throw new IllegalArgumentException("Program Name cannot be null or empty string!");
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null!");
        final EntityProgramRunner runner = this.sim.getEntityProgramRunner();
        runner.run(programName, entity);
    }
    
    /**
     * Wait for all entities to finish their running program.
     * 
     * @param entities
     *     to wait for
     */
    protected void waitForEntitesToFinishProgram(Entity... entities) {
        final EntityProgramRunner runner = this.sim.getEntityProgramRunner();
        Arrays.stream(entities)
                //.map(ent -> runner.getRunningProgramOfEntity(ent))
                .filter(program -> program == null).collect(Collectors.toList());
        // TODO wait for all entity programs
    }
    
    protected int getCoinCount(GreedyEntity entity) {
        // TODO implementation
        return 0;
    }
}