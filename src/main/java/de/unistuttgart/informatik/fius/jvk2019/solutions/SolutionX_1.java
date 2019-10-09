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

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;
import de.unistuttgart.informatik.fius.jvk2019.tasks.TaskX_1;


/**
 * The solution for TaskX_1
 *
 * @author Fabian Bühler
 */
public class SolutionX_1 extends TaskX_1 {
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        this.player = new MyNeo();
        this.spawnEntity(this.player, 0, 0);
    }
    
    @Override
    public void solve() {
        // Help neo escape the matrix. First neo needs to collect the pill.
        // If the pill is blue he has to sort the coin stacks from lowest (left)
        // to highest (right).
        // If he found a red pill he needs to reverse the order of the coin stacks.
        // When he finishes this task the phone will ring and he can escape the 
        // matrix by using it (beeing on the same tile).
    }
    
}
