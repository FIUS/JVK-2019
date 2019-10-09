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

import de.unistuttgart.informatik.fius.icge.simulation.Direction;
import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.SimulationUtilities;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.PhoneBooth;


/**
 * Exercise 3 Task 2
 *
 * @author Sebastian Paule, Lion Wagner
 */
public abstract class Task3_2 extends TaskWithHelperFunctions {

    /**
     * are the booths destroyed?
     */
    protected Boolean boothsDestroyed = false;

    /**
     * The walking neo
     */
    protected MyNeo myNeo;

    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);

        this.myNeo = new MyNeo();
        this.spawnEntity(myNeo, 1, 1);
        this.changeToD();
        SimulationUtilities.createRectangleWall(sim, 1, 15, 0, 0);
        if (!this.boothsDestroyed) {
            sim.getPlayfield().addEntity(new Position(14, 1), new PhoneBooth());
        }
    }
    
    /**
     * Let neo do a left turn.
     */
    protected abstract void turnLeft();


    /**
     * method needed for d
     */
    protected abstract void changeToD();

    @Override
    public abstract void solve();

    @Override
    public boolean verify() {
        //NEO Spawns with looking direction EAST
        turnLeft();
        if(this.myNeo.getLookingDirection() != Direction.NORTH) return false;
        turnLeft();
        if(this.myNeo.getLookingDirection() != Direction.WEST) return false;
        
        
        if(this.myNeo.getPosition().equals(new Position(15,1)) && this.boothsDestroyed) {
            return true;
        }
        return myNeo.isOnPhoneBooth();
    }
}
