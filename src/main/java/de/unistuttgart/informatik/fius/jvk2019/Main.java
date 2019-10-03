/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 *
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019;

import de.unistuttgart.informatik.fius.icge.simulation.SimulationHost;
import de.unistuttgart.informatik.fius.icge.simulation.SimulationHostFactory;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.TaskRegistry;
import de.unistuttgart.informatik.fius.icge.ui.TextureRegistry;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution0_1;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution0_2;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution0_3;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution0_4;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution1;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution3_1;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution3_2;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution3_3a;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution3_3b;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution3_4;


/**
 * Main class of the project
 *
 * @author Tim Neumann
 */
public class Main {
    
    /**
     * The main entry point of the project
     *
     * @param args
     *     the command line args; not used
     */
    public static void main(String[] args) {
        SimulationHost host = SimulationHostFactory.createSimulationHost();
        prepareTextures(host.getTextureRegistry());
        prepareTasks(host.getTaskRegistry());
    }
    
    private static void prepareTextures(TextureRegistry tr) {
        // load textures
        for (Texture texture : Texture.values()) {
            texture.load(tr);
        }
    }
    
    private static void prepareTasks(TaskRegistry tr) {
        tr.registerTask("Task0 a)", new Solution0_1());
        tr.registerTask("Task0 b)", new Solution0_2());
        tr.registerTask("Task0 c)", new Solution0_3());
        tr.registerTask("Task0 d)", new Solution0_4());
        tr.registerTask("Task1", new Solution1());
        tr.registerTask("Task3_1", new Solution3_1());
        tr.registerTask("Task3_2", new Solution3_2());
        tr.registerTask("Task3_3 (a)", new Solution3_3a());
        tr.registerTask("Task3_3 (b)", new Solution3_3b());
        tr.registerTask("Task3_4", new Solution3_4());
    }
}
