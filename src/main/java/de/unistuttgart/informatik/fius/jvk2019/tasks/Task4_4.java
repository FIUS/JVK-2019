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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.unistuttgart.informatik.fius.icge.log.Logger;
import de.unistuttgart.informatik.fius.icge.simulation.Playfield;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.LogicPhoneBoothChecker;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.PhoneBooth;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Wall;


/**
 * @author Fabian Bühler
 */
public abstract class Task4_4 extends TaskWithHelperFunctions {
    
    /**
     * The player characters
     */
    protected List<MyNeo> players;
    
    /**
     * The phoneBooth used as the goal.
     */
    protected List<PhoneBooth> goals;
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        
        Random rand = new Random();
        
        this.generateCage(9, 9);
        
        for (int y = 1; y < 9; y += 2) {
            for (int x = 0; x < 9; x++) {
                this.spawnEntity(new Wall(), x, y);
            }
        }
        for (int y = 0; y < 9; y += 2) {
            // generate logic problem
            if (rand.nextBoolean()) {
                this.spawnEntity(new Coin(), 1, y); // A
            }
            if (rand.nextBoolean()) {
                this.spawnEntity(new Coin(), 2, y); // B
            }
            if (rand.nextBoolean()) {
                this.spawnEntity(new Coin(), 3, y); // C
            }
            if (rand.nextBoolean()) {
                this.spawnEntity(new Coin(), 4, y); // D
            }
        }
        
        Playfield field = this.sim.getPlayfield();
        String goalChecker = this.registerProgram("goalChecker", () -> new LogicPhoneBoothChecker(field));
        
        this.goals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PhoneBooth goal = new PhoneBooth();
            this.spawnEntity(goal, 8, i * 2);
            this.goals.add(goal);
            try {
                this.bindProgramToEntity(goalChecker, goal);
            } catch (Exception e) {
                e.printStackTrace(Logger.simerror);
            }
        }
    }
    
    @Override
    public boolean verify() {
        this.waitForEntitesToFinishProgram(this.goals);
        // all goal checks are encoded into phone booth!
        return true;
    }
    
}
