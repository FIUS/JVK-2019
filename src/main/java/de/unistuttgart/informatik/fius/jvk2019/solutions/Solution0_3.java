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
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk2019.provided.PickupAllCollectables;
import de.unistuttgart.informatik.fius.jvk2019.provided.WalkUntilWall;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk2019.tasks.Task0;


/**
 * The example solution for Task0
 * 
 * @author Fabian Bühler
 */
public class Solution0_3 extends Task0 {
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        this.player = new Neo();
        this.sim.getPlayfield().addEntity(new Position(0, 0), this.player);
        
        this.sim.getPlayfield().addEntity(new Position(1, 0), new Coin());
        this.sim.getPlayfield().addEntity(new Position(1, 0), new Coin());
        this.sim.getPlayfield().addEntity(new Position(1, 0), new Coin());
        this.sim.getPlayfield().addEntity(new Position(2, 0), new Coin());
        this.sim.getPlayfield().addEntity(new Position(3, 0), new Coin());
    }
    
    @Override
    public void solve() {
        // solve method using entity programs
        String collectAllProgram = this.registerProgram("CollectAll", PickupAllCollectables.class);
        this.bindProgramToEntity(collectAllProgram, this.player);
        this.waitForEntitesToFinishProgram(this.player);
    }
    
}
