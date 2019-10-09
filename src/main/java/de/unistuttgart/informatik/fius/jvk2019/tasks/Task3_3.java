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
import de.unistuttgart.informatik.fius.icge.simulation.Playfield;
import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk2019.provided.Color;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.PhoneBooth;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Pill;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Wall;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;


/**
 * The Task for sheet 3.3
 * 
 * @author Tim-Julian Ehret
 */
public abstract class Task3_3 extends TaskWithHelperFunctions {
    
    /**
     * The spinning neo
     */
    protected Neo neo;
    
    private boolean flag1;
    
    private Position goal;
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        
        this.neo = new Neo();
        
        sim.getPlayfield().addEntity(new Position(5, 5), this.neo);
        generatePath(sim.getPlayfield());
    }
    
    /**
     * generates a random path containing red pills the path will follow a "circle", e.g. neo should turn right 5 times
     * 
     * @param playfield
     *     the playfield on which the path is created
     */
    private final void generatePath(final Playfield playfield) {
        //first steps
        playfield.addEntity(new Position(8, 5), new Pill(Color.RED));
        //second step
        playfield.addEntity(new Position(8, 8), new Pill(Color.RED));
        //third step
        playfield.addEntity(new Position(5, 8), new Pill(Color.RED));
        playfield.addEntity(new Position(5, 7), new PhoneBooth());
        this.spawnEntity(new Wall(), 4, 7);
        this.spawnEntity(new Wall(), 4, 6);
        this.spawnEntity(new Wall(), 5, 6);
        this.spawnEntity(new Wall(), 6, 6);
        this.spawnEntity(new Wall(), 6, 7);
        
    }
    
    private int getRandom() {
        return (int) (Math.random()*10)+2;
    }
    
    private Pill peakPill() {
        java.util.List<Pill> pills= sim.getPlayfield().getEntitiesOfTypeAt(neo.getPosition(), Pill.class, true);
        if(pills.size()>0) {
            return pills.get(0);
        }
        return null;
    }
    
    public abstract void solve();
    
    @Override
    public boolean verify() {
        return this.neo.getPosition().equals(new Position(5, 7));
    }
    
}
