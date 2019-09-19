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
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.jvk2019.provided.Coin;
import de.unistuttgart.informatik.fius.jvk2019.provided.Color;
import de.unistuttgart.informatik.fius.jvk2019.provided.Pill;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Wall;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.myNeo;


/**
 * The Task for sheet 3.3
 * 
 * @author Tim-Julian Ehret
 */
public abstract class Task3_3 implements Task {
    
    /**
     * the simulation
     */
    protected Simulation sim;
    
    /**
     * The spinning neo
     */
    protected Neo neo;
    
    private boolean flag1;
    
    private Position goal;
    
    @Override
    public void prepare(Simulation sim) {
        this.sim = sim;
        
        this.neo = new Neo();
        
        sim.getPlayfield().addEntity(new Position(0, 0), this.neo);
        generatePath(sim.getPlayfield());
    }
    
    /**
     * generates a random path containing red pills the path will follow a "circle", e.g. neo should turn right 5 times
     * 
     * @param playfield
     *     the playfield on which the path is created
     */
    private final void generatePath(final Playfield playfield) {
        //neo will start at (0,0)
        // coordinates for the goal are random in range of 4-14
        int randomX = 4 + 10 * (int) Math.random();
        int randomY = 4 + 10 * (int) Math.random();
        this.goal = new Position(randomX, randomY);
        // goal =(randomX,randomY);
        // place pills
        playfield.addEntity(new Position(randomX, randomY / 2), new Pill(Color.RED));
        playfield.addEntity(new Position(0, randomY / 2), new Pill(Color.RED));
        playfield.addEntity(new Position(randomX / 2, randomY / 2), new Pill(Color.RED));
        playfield.addEntity(new Position(0, randomY / 2 + 1), new Pill(Color.RED));
        playfield.addEntity(new Position(randomX / 2, 0), new Pill(Color.RED));
    }
    
    @Override
    public final void solve() {
        // hopefully equals is well implemented
        this.flag1 = this.neo.getPosition().equals(this.goal);
        
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
