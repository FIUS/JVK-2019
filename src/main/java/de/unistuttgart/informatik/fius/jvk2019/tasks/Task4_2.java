package de.unistuttgart.informatik.fius.jvk2019.tasks;

import de.unistuttgart.informatik.fius.icge.log.Logger;
import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.PhoneBooth;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.SmartNeo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Wall;


public abstract class Task4_2 extends TaskWithHelperFunctions {
    
    /**
     * Attribute holding the instance of SmartNeo that needs to solve the labyrinth.
     */
    protected SmartNeo neo;
    
    @Override
    public void prepare(Simulation arg0) {
        super.prepare(arg0);
        
        // Spawn maze
        this.generateCage(9, 9);
        
        this.spawnEntity(new Wall(), 2, 0);
        this.spawnEntity(new Wall(), 7, 0);
        
        this.spawnEntity(new Wall(), 1, 1);
        this.spawnEntity(new Wall(), 2, 1);
        this.spawnEntity(new Wall(), 3, 1);
        this.spawnEntity(new Wall(), 5, 1);
        this.spawnEntity(new Wall(), 7, 1);
        
        this.spawnEntity(new Wall(), 3, 2);
        this.spawnEntity(new Wall(), 5, 2);
        
        this.spawnEntity(new Wall(), 1, 3);
        this.spawnEntity(new Wall(), 3, 3);
        this.spawnEntity(new Wall(), 5, 3);
        this.spawnEntity(new Wall(), 6, 3);
        this.spawnEntity(new Wall(), 7, 3);
        
        this.spawnEntity(new Wall(), 1, 4);
        this.spawnEntity(new Wall(), 3, 4);
        this.spawnEntity(new Wall(), 5, 4);
        
        this.spawnEntity(new Wall(), 0, 5);
        this.spawnEntity(new Wall(), 1, 5);
        this.spawnEntity(new Wall(), 3, 5);
        this.spawnEntity(new Wall(), 4, 5);
        this.spawnEntity(new Wall(), 5, 5);
        this.spawnEntity(new Wall(), 7, 5);
        this.spawnEntity(new Wall(), 8, 5);
        
        this.spawnEntity(new Wall(), 4, 6);
        
        this.spawnEntity(new Wall(), 1, 7);
        this.spawnEntity(new Wall(), 2, 7);
        this.spawnEntity(new Wall(), 4, 7);
        this.spawnEntity(new Wall(), 5, 7);
        this.spawnEntity(new Wall(), 6, 7);
        this.spawnEntity(new Wall(), 7, 7);
        
        this.spawnEntity(new Wall(), 1, 8);
        
        this.spawnEntity(new PhoneBooth(), 4, 4);
    }
    
    @Override
    public boolean verify() {
        // Check if Neo is on telephone booth
        if (this.neo == null) {
            Logger.simout.println("Neo was null!");
            return false;
        }
        if (!this.neo.isOnPhoneBooth()) {
            Logger.simout.println("Neo is not on the phonebooth!");
            return false;
        }
        if (!this.neo.getPosition().equals(new Position(4, 4))) {
            Logger.simout.println("Neo is not on the phonebooth!");
            return false;
        }
        return true;
    }
}
