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
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Wall;


/**
 * 
 * Task for Sheet 2, Task 1 2019
 * @author Tim-Julian Ehret, Stefan Zindl
 */
public abstract class Task2_1 extends TaskWithHelperFunctions {
    
    /**
     * The spinning neo
     */
    protected Neo turningNeo;
    
    private boolean flag1;
    private boolean flag2;
    private boolean flag3;
    private boolean flag4;
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        
        this.turningNeo = new Neo();
        
        sim.getPlayfield().addEntity(new Position(0, 0), this.turningNeo);
        
    }
    
    @Override
    public final void solve() {
        
        this.flag1 = false;
        this.flag2 = false;
        Direction last = this.turningNeo.getLookingDirection();
        turnLeft();
        this.flag1 = this.turningNeo.getLookingDirection() == last.clockWiseNext().clockWiseNext().clockWiseNext();
        turnAround();
        this.flag2 = this.turningNeo.getLookingDirection() == last.clockWiseNext();
        int actualCoins = this.getCoinCount(turningNeo);
        this.flag3 = this.getBalance() == actualCoins * 2;
        gainCoins(42);
        this.flag4 = (actualCoins + 42) == this.getCoinCount(turningNeo);
    }
    
   /**
     * a)
     * Neu turns left.
     */
    public abstract void turnLeft();
    
    /**
     * b)
     * Neo turns around.
     */
    public abstract void turnAround();
    
    /**
     * c)
     * Neo´s balance.
     * @return Neo's amount balance
     */
    public abstract int getBalance();
    
    /**
     * d)
     * Neo´s amount of coins.
     * @return Neo's balance
     */
    public abstract void gainCoins(final int amount);
    
    @Override
    public final boolean verify() {
        return this.flag1 && this.flag2 && this.flag3 && this.flag4;
    }
    
}
