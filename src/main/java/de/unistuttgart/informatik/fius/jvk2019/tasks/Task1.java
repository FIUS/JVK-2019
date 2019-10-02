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

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Wall;


/**
 * An example task
 * 
 * @author Tim Neumann
 */
public abstract class Task1 extends TaskWithHelperFunctions {
    
    /**
     * The walking neo
     */
    protected Neo walkingNeo;
    
    /**
     * The spinning neo
     */
    protected Neo spinningNeo;
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        
        sim.getPlayfield().addEntity(new Position(-1, -1), new Wall());
        sim.getPlayfield().addEntity(new Position(-1, 0), new Wall());
        sim.getPlayfield().addEntity(new Position(-1, 1), new Wall());
        sim.getPlayfield().addEntity(new Position(5, -1), new Wall());
        sim.getPlayfield().addEntity(new Position(5, 0), new Wall());
        sim.getPlayfield().addEntity(new Position(5, 1), new Wall());
        
        this.walkingNeo = new Neo();
        
        this.spinningNeo = new Neo();
        
        sim.getPlayfield().addEntity(new Position(0, 0), this.walkingNeo);
        sim.getPlayfield().addEntity(new Position(2, 0), this.spinningNeo);
        
    }
    
    @Override
    public boolean verify() {
        // TODO
        return true;
    }
    
}
