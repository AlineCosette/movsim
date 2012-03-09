/*
 * Copyright (C) 2010, 2011, 2012 by Arne Kesting, Martin Treiber, Ralph Germ, Martin Budden
 *                                   <movsim.org@gmail.com>
 * -----------------------------------------------------------------------------------------
 * 
 * This file is part of
 * 
 * MovSim - the multi-model open-source vehicular-traffic simulator.
 * 
 * MovSim is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * MovSim is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with MovSim. If not, see <http://www.gnu.org/licenses/>
 * or <http://www.movsim.org>.
 * 
 * -----------------------------------------------------------------------------------------
 */
package org.movsim.simulator.vehicles.longitudinalmodel.equilibrium;

import org.movsim.utilities.Tables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class EquilibriumPropertiesImpl.
 */
public class EquilibriumProperties {

    /** The Constant logger. */
    final static Logger logger = LoggerFactory.getLogger(EquilibriumProperties.class);

    /** The Constant NRHO. */
    final static int NRHO = 51; // time critical

    /** The rho max. */
    protected final double rhoMax;

    /** The length. */
    final double length;

    /** The q max. */
    double qMax;

    /** The rho q max. */
    double rhoQMax;

    /** The v eq tab. */
    protected double[] vEqTab;

    /**
     * Constructor.
     * 
     * @param length
     *            the length
     */
    public EquilibriumProperties(double length) {
        this.length = length;
        vEqTab = new double[NRHO];
        rhoMax = 1.0 / length;
    }

    /**
     * Gets the q max.
     * 
     * @return the q max
     */
    public double getQMax() {
        return qMax;
    }

    /**
     * Gets the rho max.
     * 
     * @return the rho max
     */
    public double getRhoMax() {
        return rhoMax;
    }

    /**
     * Gets the rho q max.
     * 
     * @return the rho q max
     */
    public double getRhoQMax() {
        return rhoQMax;
    }

    /**
     * Gets the net distance.
     * 
     * @param rho
     *            the rho
     * @return the net distance
     */
    public double getNetDistance(double rho) {
        return rho != 0.0 ? (1.0 / rho - 1.0 / rhoMax) : 0.0;
    }

    // calculate Qmax, and abszissa rhoQmax from veqtab (necessary for BC)
    /**
     * Calc rho q max.
     */
    protected void calcRhoQMax() {
        int ir = 1;
        qMax = -1.;
        while (vEqTab[ir] * rhoMax * ir / vEqTab.length > qMax) {
            qMax = vEqTab[ir] * rhoMax * ir / vEqTab.length;
            ir++;
        }
        rhoQMax = rhoMax * ir / vEqTab.length;
        logger.debug("rhoQMax = {} = {}/km", rhoQMax, rhoQMax * 1000);
    }

    /**
     * Gets the v eq.
     * 
     * @param rho
     *            the rho
     * @return the v eq
     */
    public double getVEq(double rho) {
        return Tables.intp(vEqTab, Math.min(rho, rhoMax), 0, rhoMax);
    }

    /**
     * Gets the rho.
     * 
     * @param i
     *            the i
     * @return the rho
     */
    public double getRho(int i) {
        return rhoMax * i / (vEqTab.length - 1);
    }

    public double getVEq(int i) {
        return vEqTab[i];
    }

    public int getVEqCount() {
        return vEqTab.length;
    }
}
