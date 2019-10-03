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

import de.unistuttgart.informatik.fius.jvk2019.provided.WalkingProgram;
import de.unistuttgart.informatik.fius.jvk2019.tasks.Task1;

/**
 * The example solution for Task1
 *
 * @author Tim Neumann
 */
public class Solution1 extends Task1 {

    @Override
    public void solve() {
        String walkingProgramName = "Walking";

        this.sim.getEntityProgramRegistry().registerEntityProgram(walkingProgramName, new WalkingProgram());

        this.sim.getEntityProgramRunner().run(walkingProgramName, this.walkingNeo);

        while (true) {
            this.spinningNeo.turnClockWise();
        }
    }

}
