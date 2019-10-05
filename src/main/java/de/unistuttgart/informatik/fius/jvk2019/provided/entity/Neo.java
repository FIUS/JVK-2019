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

import de.unistuttgart.informatik.fius.icge.simulation.entity.GreedyEntity;
import de.unistuttgart.informatik.fius.jvk2019.Texture;
import de.unistuttgart.informatik.fius.jvk2019.provided.exceptions.NeoIsBrokeException;
import de.unistuttgart.informatik.fius.jvk2019.provided.exceptions.NoCoinException;


/**
 * The Neo entity
 * 
 * @author Tim Neumann
 */
public class Neo extends GreedyEntity {
    
    @Override
    protected String getTextureHandle() {
        return Texture.NEO.getHandle(this.getLookingDirection());
    }
    
    @Override
    protected int getZPosition() {
        return 10;
    }
    
    /**
     * 
     * @return whether there is a pill to collect
     */
    protected boolean canCollectPill() {
        return false;
    }
    
    /**
     * collects a pill from the actual field
     */
    protected void collectPill() {
        
    }
    
    /**
     * collects a coin from the actual field
     * 
     * @throws NoCoinException
     *     when there is no coin
     */
    protected void collectCoin() {
    }
    
    /**
     * drops a coin from Neo's inventory to the actual field
     * 
     * @throws NeoIsBrokeException
     *     when Neo is broken
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
     * 
     * @param coins
     *     the amount of coins to set
     */
    protected void setCoins(int coins) {
        
    }
    
    /**
     * checks if neo is currently standing on a field that also contains a phone booth
     * 
     * @return true if neo stands on a field with a phone booth
     */
    public Boolean isOnPhoneBooth() {
        if (this.getPlayfield().getEntitiesOfTypeAt(this.getPosition(), PhoneBooth.class, true).size() > 0) {
            return true;
        }
        return false;
    }    
      
    /**
     * Turns Neo counter clockwise.
     * Operation is to be implmented in MyNeo in Task 2.1.a)
     */
    @SuppressWarnings("static-method")
    public void turnCounterClockwise()
    {
        throw new UnsupportedOperationException("This Method is to be implemented in the MyNeo class.");
    }
    
    /**
     * Turns Neo around.
     * Operation is to be implmented in MyNeo in Task 2.1.b).
     */
    @SuppressWarnings("static-method")
    public void turnAround()
    {
        throw new UnsupportedOperationException("This Method is to be implemented in the MyNeo class.");
    }
    
    /**
     * Gets the Balance of the current Neo. * Operation is to be implmented in MyNeo in Task 2.1.c) .
     * 
     * @return the balance
     */
    @SuppressWarnings("static-method")
    public int getBalance()
    {
        throw new UnsupportedOperationException("This Method is to be implemented in the MyNeo class.");
    }
    
    /**
     * Adds an amount of coins to the wallet of Neo. Operation is to be implmented in MyNeo in Task 2.1.d) .
     * 
     * @param amountOfCoins
     *     The amount to gain
     */
    @SuppressWarnings("static-method")
    public void gainCoins(int amountOfCoins)
    {
        throw new UnsupportedOperationException("This Method is to be implemented in the MyNeo class.");
    }
    
    
    /**
     * Helper Method that sets a fixed amount of coins for easier verification.
     * Used in Verification of Task 2.1
     * @param amountOfCoins the new amount of coins
     */
    public void setCoinsInWallet(int amountOfCoins) {
        this.setCoins(amountOfCoins);//calling real method for good measure.
        this.currentCoinCount = amountOfCoins;
    }
    
    /**
     * @return the number of coins in neos wallet
     */
    public int getCoinsInWallet() {
        return this.currentCoinCount;
    }
    
    /**
     * Helper field to hold the current amount of coins set by setCoinsForVerify
     */
    public int currentCoinCount;
}
