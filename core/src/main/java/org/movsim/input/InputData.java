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
package org.movsim.input;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.movsim.input.model.SimulationInput;
import org.movsim.input.model.VehiclesInput;
import org.movsim.input.model.vehicle.VehicleInput;
import org.movsim.input.model.vehicle.consumption.FuelConsumptionInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputData {

    /** The Constant logger. */
    final static Logger logger = LoggerFactory.getLogger(InputData.class);

    private VehiclesInput vehiclesInput;
    private SimulationInput simulationInput;
    private FuelConsumptionInput fuelConsumptionInput;

    /**
     * Constructor.
     */
    public InputData() {
    }

    public static Map<String, VehicleInput> createVehicleInputDataMap(List<VehicleInput> vehicleInputData) {
        final HashMap<String, VehicleInput> map = new HashMap<String, VehicleInput>();
        for (final VehicleInput vehInput : vehicleInputData) {
            final String keyName = vehInput.getLabel();
            map.put(keyName, vehInput);
        }
        return map;
    }

    public SimulationInput getSimulationInput() {
        return simulationInput;
    }

    public void setSimulationInput(SimulationInput simulationInput) {
        this.simulationInput = simulationInput;
    }

    public void setFuelConsumptionInput(FuelConsumptionInput fuelConsumptionInput) {
        this.fuelConsumptionInput = fuelConsumptionInput;
    }

    public FuelConsumptionInput getFuelConsumptionInput() {
        return fuelConsumptionInput;
    }

    
    public VehiclesInput getVehiclesInput(){
        return vehiclesInput;
    }
    
    public void setVehiclesInput(VehiclesInput vehiclesInput) {
        this.vehiclesInput = vehiclesInput;
    }
    
}
