/*
 * This source file is part of the FIUS ICGE project.
 * For more information see github.com/FIUS/ICGE2
 *
 * Copyright (c) 2019 the ICGE project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.provided.entity;

import de.unistuttgart.informatik.fius.icge.simulation.entity.MovableEntity;
import de.unistuttgart.informatik.fius.jvk2019.Texture;
import de.unistuttgart.informatik.fius.jvk2019.provided.exceptions.NeoIsBrokeException;
import de.unistuttgart.informatik.fius.jvk2019.provided.exceptions.NoCoinException;

/**
 * The Neo entity
 * 
 * @author Tim Neumann
 */
public class Neo extends MovableEntity {
    
    @Override
    protected String getTextureHandle() {
        return Texture.NEO.getHandle(this.getLookingDirection());
    }
    
    @Override
    protected int getZPosition() {
        return 10;
    }
    /**
     * collects a coin from the actual field
     * @throws NoCoinException
     */
    protected void collectCoin(){
    }
    /**
     * drops a coin from Neo's inventory to the actual field
     * @throws NeoIsBrokeException
     */
    protected void dropCoin() {       
    }
    
    /**
     * 
     * @return whether Neo can drop a coin
     */
    protected boolean canDropCoin() {
        //TODO
        return false;
    }
    /**
     * 
     * @return whether there is a coin to collect
     */
    protected boolean canCollectCoin() {
        //TODO
        return false;
        
    }
    /**
     * setter for coins
     * @param coins
     */
    protected void setCoins(int coins) {
        
    }
    
    /**
     * checks if neo is currently standing on a field that also contains a phone booth
     * 
     * @return true if neo stands on a field with a phone booth
     */
    public Boolean isOnPhoneBooth() {
        if(this.getPlayfield().getEntitiesOfTypeAt(this.getPosition(), PhoneBooth.class, true).size()>0) {
            return true;
        }
        return false;
    }
    
    
}
