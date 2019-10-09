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

import de.unistuttgart.informatik.fius.icge.log.Logger;
import de.unistuttgart.informatik.fius.icge.simulation.Playfield;
import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.exception.EntityNotOnFieldException;


/**
 * Program used in task X 2
 */
public class LogicPhoneBoothChecker extends PhoneBoothProgram {
    
    private final Playfield field;
    
    private boolean A;
    private boolean B;
    private boolean C;
    private boolean D;
    private boolean result;
    
    /**
     * Create a new Standard LogicPhoneBoothChecker.
     */
    public LogicPhoneBoothChecker(Playfield field) {
        super();
        this.field = field;
    }
    
    @Override
    public void run(PhoneBooth booth) {
        booth.setRequirementsNotFulfilled();
        booth.sleep(1);
        // check variables
        Position p = booth.getPosition();
        this.A = this.field.getEntitiesOfTypeAt(new Position(1, p.getY()), Coin.class, true).size() > 0;
        this.B = this.field.getEntitiesOfTypeAt(new Position(2, p.getY()), Coin.class, true).size() > 0;
        this.C = this.field.getEntitiesOfTypeAt(new Position(3, p.getY()), Coin.class, true).size() > 0;
        this.D = this.field.getEntitiesOfTypeAt(new Position(4, p.getY()), Coin.class, true).size() > 0;
        // code logic function here:
        this.result = (this.A && this.B) || ((!this.D) && this.C && this.B);
        
        while (!checkIfNeoOnBooth(booth)) {
            boolean requirementsMet = this.checkRequirements(booth);
            if (requirementsMet) {
                booth.setRequirementsAsFulfilled();
            } else {
                booth.setRequirementsNotFulfilled();
            }
            booth.sleep(1);
        }
    }
    
    private boolean checkIfNeoOnBooth(PhoneBooth booth) {
        try {
            Position p = booth.getPosition();
            return (this.field.getEntitiesOfTypeAt(p, Neo.class, true).size() > 0);
        } catch (EntityNotOnFieldException e) {
            e.printStackTrace(Logger.simerror);
            return false;
        }
    }
    
    private boolean checkRequirements(PhoneBooth booth) {
        try {
            Position p = booth.getPosition();
            boolean neoResult = this.field.getEntitiesOfTypeAt(new Position(6, p.getY()), Coin.class, true).size() > 0;
            return this.result == neoResult;
        } catch (EntityNotOnFieldException e) {
            return false;
        }
    }
}
