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
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import de.unistuttgart.informatik.fius.icge.log.Logger;
import de.unistuttgart.informatik.fius.icge.simulation.Playfield;
import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.Color;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.PhoneBooth;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Pill;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.RequirementChecks;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.StandardPhoneBoothProgram;


/**
 * An example task
 * 
 * @author Fabian Bühler
 */
public abstract class TaskX_1 extends TaskWithHelperFunctions {
    
    /**
     * The player character
     */
    protected MyNeo player;
    
    /**
     * The phoneBooth used as the goal.
     */
    protected PhoneBooth goal;
    
    private List<Integer> startConfiguration;
    private List<Integer> toCheck;
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        
        Random rand = new Random();
        int cageSize = randInt(rand, 8, 15);
        
        this.generateCage(cageSize, 1);
        
        List<Integer> coinCounts = new ArrayList<>();
        
        for (int i = 1; i < cageSize - 1; i++) {
            int count = randInt(rand, 0, 9);
            for (int a = 0; a < count; a++) {
                this.spawnEntity(new Coin(), i, 0);
            }
            coinCounts.add(count);
        }
        
        this.startConfiguration = coinCounts.stream().collect(Collectors.toUnmodifiableList());
        Collections.reverse(coinCounts); // mutating in place
        List<Integer> reversed = coinCounts.stream().collect(Collectors.toUnmodifiableList());
        List<Integer> sorted = coinCounts.stream().sorted().collect(Collectors.toUnmodifiableList());
        
        Playfield field = this.sim.getPlayfield();
        this.goal = new PhoneBooth();
        Supplier<Boolean> canUseBooth = RequirementChecks.and(
                // used comments to trick autoformatter...
                RequirementChecks.testCoinCount(() -> this.player, RequirementChecks.getEqualToPredicate(0)), //
                RequirementChecks.testInventoryCount(() -> this.player, Pill.class, RequirementChecks.getEqualToPredicate(1)), //
                RequirementChecks.testEntytyCountOnField(field, Coin.class, RequirementChecks.getEqualToPredicate(0), 0, 0), //
                RequirementChecks.testEntytyCountOnField(field, Coin.class, RequirementChecks.getEqualToPredicate(0), cageSize - 1, 0), //
                this::verifyArray
        );
        this.goal.setRequirementsChecker(canUseBooth);
        this.spawnEntity(this.goal, cageSize - 1, 0);
        
        String goalChecker = this.registerProgram(
                "goalChecker", new StandardPhoneBoothProgram(RequirementChecks.testEntitiesOnSameField(() -> this.player, () -> this.goal))
        );
        
        if (rand.nextBoolean()) {
            // use sort to check
            this.toCheck = sorted;
            this.spawnEntity(new Pill(Color.BLUE), 0, 0);
            Logger.simout.println("-------------------------------------------------------------");
            Logger.simout.println("Sort the coin stacks to escape the Matrix!");
            Logger.simout.println("-------------------------------------------------------------");
        } else {
            this.toCheck = reversed;
            this.spawnEntity(new Pill(Color.RED), 0, 0);
            Logger.simout.println("-------------------------------------------------------------");
            Logger.simout.println("Reverse the order of the coin stacks to escape the Matrix!");
            Logger.simout.println("-------------------------------------------------------------");
        }
        
        this.bindProgramToEntity(goalChecker, this.goal);
    }
    
    private boolean verifyArray() {
        if (this.toCheck == null) {
            return false;
        }
        Playfield field = this.sim.getPlayfield();
        for (int i = 0; i < this.toCheck.size(); i++) {
            int coinsFound = field.getEntitiesOfTypeAt(new Position(i + 1, 0), Coin.class, true).size();
            if (coinsFound != this.toCheck.get(i)) return false;
        }
        return true;
    }
    
    @Override
    public boolean verify() {
        this.waitForEntitesToFinishProgram(this.goal);
        // all goal checks are encoded into phone booth!
        return true;
    }
    
    private int randInt(Random rand, int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
    
}
