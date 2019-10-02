/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.provided.entity;

import de.unistuttgart.informatik.fius.icge.simulation.entity.*;
import de.unistuttgart.informatik.fius.jvk2019.Texture;


/**
 * A block to leave the Matrix
 * 
 * @author paulesn
 */
public class PhoneBooth extends BasicEntity implements SolidEntity {
    
    /**
     * If the phoneBooth is not reachable it is solid.
     * 
     * Set to true if all requirements for this phoneBooth are met.
     */
    private boolean isReachable = false;
    
    /**
     * Set the status of the requirements for this phoneBooth to fulfilled.
     */
    public void setRequirementsAsFulfilled() {
        this.isReachable = true;
    }
    
    /**
     * Set the status of the requirements for this phoneBooth to unfulfilled.
     */
    public void setRequirementsNotFulfilled() {
        this.isReachable = false;
    }
    
    @Override
    public boolean isCurrentlySolid() {
        return !this.isReachable;
    }
    
    @Override
    protected String getTextureHandle() {
        return Texture.PHONEBOOTH.getHandle();
    }
    
    @Override
    protected int getZPosition() {
        return 1;
    }
    
}
