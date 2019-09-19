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

import de.unistuttgart.informatik.fius.icge.simulation.Playfield;
import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.entity.*;
import de.unistuttgart.informatik.fius.icge.ui.Drawable;
import de.unistuttgart.informatik.fius.jvk2019.Texture;


/**
 * A block to leave the Matrix
 * 
 * @author paulesn
 */
public class PhoneBooth extends BasicEntity implements SolidEntity {
    
    @Override
    public boolean isCurrentlySolid() {        
        return false;
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
