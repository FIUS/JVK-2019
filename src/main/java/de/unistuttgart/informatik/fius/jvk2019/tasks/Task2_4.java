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

import java.util.Iterator;
import java.util.List;

import de.unistuttgart.informatik.fius.icge.simulation.Direction;
import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.jvk2019.provided.Coin;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Wall;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.myNeo;


/**
 * An example task
 * 
 * @author Tim-Julian Ehret
 */
public abstract class Task2_4 implements Task {
    
    /**
     * the simulation
     */
    protected Simulation sim;
    
    /**
     * The spinning neo
     */
    protected Neo neo;
    
    private boolean flag1;
    private boolean flag2;
    private boolean flag3;
    
    @Override
    public void prepare(Simulation sim) {
        this.sim = sim;
        
        this.neo = new Neo();
        
        sim.getPlayfield().addEntity(new Position(0, 0), this.neo);
        
    }
    
    @Override
    public final void solve() {
        this.flag1 = false;
        this.flag2 = false;
        this.flag3 = false;
        List<myNeo> neos = sim.getPlayfield().getAllEntitiesOfType(myNeo.class, false);
        if (neos.size() >= 2) {
            this.flag1 = true;
            //TODO check log for 360-turn
        }
        
        for (Iterator<myNeo> iterator = neos.iterator(); iterator.hasNext();) {
            myNeo myNeo = iterator.next();
            if (Helper.getCoinCount(myNeo) == 998) {
                this.flag2 = true;
            }
            if (Helper.getCoinCount(myNeo) == 2) {
                this.flag3 = true;
            }
            // maybe check log for the transfer of coins between the neos...
            
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
