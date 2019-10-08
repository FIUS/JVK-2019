/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.solutions;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;
import de.unistuttgart.informatik.fius.jvk2019.tasks.Task2_1;


/**
 * The solution for 2.1 Nothing needs to be done here by students.
 * 
 * @author Lion Wagner
 */
public class Solution2_1 extends Task2_1 {
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        
        this.myNeo = new MyNeo();
        //adding myNeo to the playing field
        sim.getPlayfield().addEntity(new Position(0, 0), this.myNeo);
    }
    
    /**
     * An example that uses all the commands/operations that are to be implemented in Task 2.1. (except for
     * 'gainCoins').
     * 
     * Feel free to move myNeo as you like.
     */
    @Override
    public void solve() {
        this.myNeo.moveTwice();
        this.myNeo.turnAround();
        this.myNeo.moveIfPossible();
        this.myNeo.turnCounterClockwise();
        this.myNeo.move();
        this.myNeo.collectCoin();
    }
}
