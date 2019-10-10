package de.unistuttgart.informatik.fius.jvk2019.tasks;

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.SmartNeo;

public abstract class Task4_2 extends TaskWithHelperFunctions {
    protected SmartNeo neo;

    @Override
    public void prepare(Simulation arg0) {
        super.prepare(sim);
    }

    @Override
    public boolean verify() {
        // Check if Neo is on telephone booth
        return true;
    }
}
