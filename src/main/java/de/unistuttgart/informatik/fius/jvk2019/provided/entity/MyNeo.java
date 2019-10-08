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

/**
 * A child class of Neo. This class is to be implemented by the students.
 *
 * TODO: 2.2.b),c) add your author Tag and another one
 */
public class MyNeo extends Neo {
    
    //TODO: 2.5.a) Add the missing attributes
    
    /**
     * Default empty constructor
     */
    public MyNeo() {
        
    }
    
    /**
     * Creates a new MyNeo with a certain amount of coins in his wallet.
     * 
     * @param starterCoins
     *     coin count that this neo starts with
     */
    public MyNeo(int starterCoins) {
        setCoinsInWallet(starterCoins);
    }
    
    /**
     * Moves this Neo entity twice. Example implementation of a new operation.
     */
    public void moveTwice() {
        this.move();
        this.move();
    }
    
    /**
     * Turns Neo counter clockwise. Operation is to be implemented in MyNeo in Task 2.1.a)
     */
    public void turnCounterClockwise() {
        //TODO: Task 2.1 a) implementation here
    }
    
    /**
     * Turns Neo around. Operation is to be implemented in MyNeo in Task 2.1.b).
     */
    public void turnAround() {
        //TODO: Task 2.1 b) implementation here
    }
    
    //TODO: Task 2.1 c) javadoc here
    //TODO: Task 2.1 c) implementation here (getBalance)
    
    //TODO: Task 2.1 d) javadoc here
    //TODO: Task 2.1 d) implementation here (gainCoins)
    
    //////////////////////////////////////////////////
    
    //TODO: Task 2.5 b) add getter queries:
    
    //TODO: Task 2.5 c) add setter commands:
    
    //TODO: Task 2.5.d) Override the move command so it also counts the steps taken:
    
}
