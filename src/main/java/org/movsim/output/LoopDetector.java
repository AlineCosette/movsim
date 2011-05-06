/**
 * Copyright (C) 2010, 2011 by Arne Kesting, Martin Treiber,
 *                             Ralph Germ, Martin Budden
 *                             <info@movsim.org>
 * ----------------------------------------------------------------------
 * 
 *  This file is part of 
 *  
 *  MovSim - the multi-model open-source vehicular-traffic simulator 
 *
 *  MovSim is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  MovSim is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with MovSim.  If not, see <http://www.gnu.org/licenses/> or
 *  <http://www.movsim.org>.
 *  
 * ----------------------------------------------------------------------
 */
package org.movsim.output;

import org.movsim.simulator.vehicles.VehicleContainer;

// TODO: Auto-generated Javadoc
/**
 * The Interface LoopDetector.
 */
public interface LoopDetector {

    /**
     * Update.
     * 
     * @param time
     *            the time
     * @param vehicles
     *            the vehicles
     */
    public void update(double time, VehicleContainer vehicles);

    /**
     * Position.
     * 
     * @return the double
     */
    double position();

    /**
     * Mean speed.
     * 
     * @return the double
     */
    double meanSpeed();

    /**
     * Rho arithmetic.
     * 
     * @return the double
     */
    double rhoArithmetic();

    /**
     * Flow.
     * 
     * @return the double
     */
    double flow();

    /**
     * Occupancy.
     * 
     * @return the double
     */
    double occupancy();

    /**
     * Close files.
     */
    void closeFiles();

}