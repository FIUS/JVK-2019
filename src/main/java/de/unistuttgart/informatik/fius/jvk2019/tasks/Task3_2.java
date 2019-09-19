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

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.jvk2019.provided.SimulationUtilities;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Neo;

/**
 * TODO: Description
 * @author name
 */
public abstract class Task3_2 implements Task {
    
    /**
     * the simulation
     */
    protected Simulation sim;
    
    /**
     * The walking neo
     */
    protected Neo neo;
    
    @Override
    public void prepare(Simulation sim) {
        this.sim = sim;
        SimulationUtilities.createRectangleWall(sim, 1, 15, 0, 0);
        System.out.println("all done");
        this.sim.getSimulationClock().start();
    }
    
    @Override
    public abstract void solve();
    
    @Override
    public boolean verify() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
