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

import java.util.ArrayList;

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.LogicProgram;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;
import de.unistuttgart.informatik.fius.jvk2019.tasks.Task4_4;


/**
 * The solution for Task4_4
 *
 * @author Fabian Bühler
 */
public class Solution4_4 extends Task4_4 {
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        this.players = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MyNeo player = new MyNeo(1);
            this.spawnEntity(player, 0, i * 2);
            this.players.add(player);
        }
    }
    
    @Override
    public void solve() {
        // Help all neos escape the Matrix.
        // Each neo has to solve the logical expression (A and B) or ((¬D) and C and B)
        // A, B, C and D correspond to the fields on with x = 1 to x = 4
        // A field with a coin is true
        // The result should be placed at x = 6
        // The implementation should e done in LogicProgram!
        String logicProgram = this.registerProgram("logicProgram", LogicProgram.class);
        this.players.forEach(player -> {
            this.bindProgramToEntity(logicProgram, player);
        });
    }
    
}
