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
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Wall;


/**
 * An example task
 * 
 * @author Tim-Julian Ehret
 */
public abstract class Task2_2 extends TaskWithHelperFunctions {
    
    /**
     * The spinning neo
     */
    protected Neo neo;
    
    private boolean flag1;
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        
        this.neo = new Neo();
        
        sim.getPlayfield().addEntity(new Position(0, 0), this.neo);
        
    }
    
    @Override
    public final void solve() {
        this.flag1 = true;
        /*
         * check if there is a field without a coin
         */
        for (int i = this.neo.getPosition().getX(); i >= this.neo.getPosition().getX() - 4; i--) {
            if (this.sim.getPlayfield().getEntitiesOfTypeAt(new Position(i, this.neo.getPosition().getY()), Coin.class, true).isEmpty()) {
                this.flag1 = false;
            }
        }
    }
    
    /**
     * turns Neo to the left
     */
    public abstract void turnLeft();
    
    /**
     * turns Neo around
     */
    public abstract void turnAround();
    
    @Override
    public boolean verify() {
        return this.flag1;
    }
    
}
