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
package org.movsim.simulator.roadnetwork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.movsim.input.model.simulation.TrafficLightData;
import org.movsim.input.model.simulation.TrafficLightsInput;
import org.movsim.simulator.vehicles.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class TrafficLights.
 */
public class TrafficLights implements Iterable<TrafficLight> {

    /** The Constant logger. */
    final static Logger logger = LoggerFactory.getLogger(TrafficLights.class);

    private final List<TrafficLight> trafficLights = new ArrayList<TrafficLight>();

    public interface RecordDataCallback {
        /**
         * Callback to allow the application to process or record the traffic light data.
         * 
         * @param simulationTime
         *            the current logical time in the simulation
         * @param iterationCount
         * @param trafficLights
         */
        public void recordData(double simulationTime, long iterationCount, Iterable<TrafficLight> trafficLights);
    }

    private RecordDataCallback recordDataCallback;

    /**
     * Constructor.
     * 
     * @param trafficLightsInput
     */
    public TrafficLights(TrafficLightsInput trafficLightsInput) {
        final List<TrafficLightData> trafficLightData = trafficLightsInput.getTrafficLightData();
        for (final TrafficLightData tlData : trafficLightData) {
            trafficLights.add(new TrafficLight(tlData));
        }
        
        Collections.sort(trafficLights, new Comparator<TrafficLight>() {
            @Override
            public int compare(TrafficLight o1, TrafficLight o2) {
                final Double pos1 = new Double(o1.position());
                final Double pos2 = new Double(o2.position());
                return pos1.compareTo(pos2); // sort with increasing x
            }
        });
    }

    /**
     * Sets the traffic light recorder.
     * 
     * @param recordDataCallback
     */
    public void setRecorder(RecordDataCallback recordDataCallback) {
        this.recordDataCallback = recordDataCallback;
    }

    /**
     * Update.
     * 
     * @param dt
     *            delta-t, simulation time interval, seconds
     * @param simulationTime
     *            current simulation time, seconds
     * @param iterationCount
     *            the number of iterations that have been executed
     * @param roadSegment
     */
    public void update(double dt, double simulationTime, long iterationCount, RoadSegment roadSegment) {

        if (!trafficLights.isEmpty()) {
            // first update traffic light status
            for (final TrafficLight trafficLight : trafficLights) {
                trafficLight.update(simulationTime);
            }
            // then update vehicle status approaching traffic lights
            final Iterator<LaneSegment> laneSegmentIterator = roadSegment.laneSegmentIterator();
            while(laneSegmentIterator.hasNext()){
                final LaneSegment laneSegment = laneSegmentIterator.next();
                for (final Vehicle vehicle : laneSegment) {
                    // quick hack criterion for selecting next downstream traffic light
                    // assume that trafficLights are sorted with increasing position 
                    for (final TrafficLight trafficLight : trafficLights) {
                        if(vehicle.getFrontPosition() < trafficLight.position() ){
                            vehicle.updateTrafficLight(simulationTime, trafficLight);
                            break;
                        }
                    }
                }
            }
        }
        if (recordDataCallback != null) {
            recordDataCallback.recordData(simulationTime, iterationCount, trafficLights);
        }
    }

    @Override
    public Iterator<TrafficLight> iterator() {
        return trafficLights.iterator();
    }
}
