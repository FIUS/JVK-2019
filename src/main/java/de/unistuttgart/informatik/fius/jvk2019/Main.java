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

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.SimulationFactory;
import de.unistuttgart.informatik.fius.icge.ui.TextureRegistry;
import de.unistuttgart.informatik.fius.icge.ui.UiManager;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution1;


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
        Simulation sim = SimulationFactory.createSimulation();
        prepareUiManager(sim.getUiManager());

        sim.initialize();
        
        sim.getTaskRunner().runTask(Solution1.class, sim);
    }

    private static void prepareUiManager(UiManager manager) {
        // load textures
        final TextureRegistry tr = manager.getTextureRegistry();
        for (Texture texture : Texture.values()) {
            texture.load(tr);
        }
        manager.setWindowTitle("JVK 2019");
    }
}
