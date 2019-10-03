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
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Wall;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;


/**
 * An example task
 * 
 * @author Tim-Julian Ehret
 */
public abstract class Task2_4 extends TaskWithHelperFunctions {
    
    /**
     * The spinning neo
     */
    protected Neo neo;
    
    private boolean flag1;
    private boolean flag2;
    private boolean flag3;
    
    protected List<MyNeo> neos;
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        
        this.neo = new Neo();
        
        sim.getPlayfield().addEntity(new Position(0, 0), this.neo);
        
    }
    
    @Override
    public final void solve() {
        this.flag1 = false;
        this.flag2 = false;
        this.flag3 = false;
        neos = sim.getPlayfield().getAllEntitiesOfType(MyNeo.class, false);
        if (neos.size() >= 2) {   
            this.flag1 = true;
        }
        
        for (Iterator<MyNeo> iterator = neos.iterator(); iterator.hasNext();) {
            MyNeo myNeo = iterator.next();
            if(myNeo.getLookingDirection() == Direction.EAST) {
            }
            if (this.getCoinCount(myNeo) == 998) {
                this.flag2 = true;
            }
            if (this.getCoinCount(myNeo) == 2) {
                this.flag3 = true;
            }
            // maybe check log for the transfer of coins between the neos...
            
        }
        
    }
    
    public void addNeo(MyNeo newNeo) {
        this.neos.add(newNeo);
    }
    
    /**
     * turns Neo around
     */
    public abstract void turnAround();
    
    @Override
    public boolean verify() {
        return this.flag1;
    }
    
}
