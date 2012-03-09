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

import org.movsim.simulator.vehicles.longitudinalmodel.LongitudinalModelBase;
import org.movsim.simulator.vehicles.longitudinalmodel.LongitudinalModelBase.ModelName;
import org.movsim.simulator.vehicles.longitudinalmodel.acceleration.ACC;
import org.movsim.simulator.vehicles.longitudinalmodel.acceleration.CCS;
import org.movsim.simulator.vehicles.longitudinalmodel.acceleration.Gipps;
import org.movsim.simulator.vehicles.longitudinalmodel.acceleration.IDM;
import org.movsim.simulator.vehicles.longitudinalmodel.acceleration.KKW;
import org.movsim.simulator.vehicles.longitudinalmodel.acceleration.Krauss;
import org.movsim.simulator.vehicles.longitudinalmodel.acceleration.NSM;
import org.movsim.simulator.vehicles.longitudinalmodel.acceleration.Newell;
import org.movsim.simulator.vehicles.longitudinalmodel.acceleration.OVM_FVDM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EquilibriumPropertiesFactory {

    /** The Constant logger. */
    final static Logger logger = LoggerFactory.getLogger(EquilibriumPropertiesFactory.class);

    public static EquilibriumProperties create(double vehLength, LongitudinalModelBase longModel) {
        if (longModel.modelName() == ModelName.IDM) {
            return new EquilibriumIDM(vehLength, (IDM) longModel);
        } else if (longModel.modelName() == ModelName.ACC) {
            return new EquilibriumACC(vehLength, (ACC) longModel);
        } else if (longModel.modelName() == ModelName.OVM_FVDM) {
            return new EquilibriumOVM_FVDM(vehLength, (OVM_FVDM) longModel);
        } else if (longModel.modelName() == ModelName.GIPPS) {
            return new EquilibriumGipps(vehLength, (Gipps) longModel);
        } else if (longModel.modelName() == ModelName.NEWELL) {
            return new EquilibriumNewell(vehLength, (Newell) longModel);
        } else if (longModel.modelName() == ModelName.KRAUSS) {
            return new EquilibriumKrauss(vehLength, (Krauss) longModel);
        } else if (longModel.modelName() == ModelName.NSM) {
            return new EquilibriumNSM(vehLength, (NSM) longModel);
        } else if (longModel.modelName() == ModelName.KKW) {
            return new EquilibriumKKW(vehLength, (KKW) longModel);
        } else if (longModel.modelName() == ModelName.CCS) {
            return new EquilibriumCCS(vehLength, (CCS) longModel);
        } else {
            logger.error("no fundamental diagram constructed for model {}. exit.", longModel.modelName().name());
            System.exit(0);
        }
        return null; // should not be reached after exit

    }
}
