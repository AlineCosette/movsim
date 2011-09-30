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
package org.movsim.input.model.impl;

import org.jdom.Element;
import org.movsim.input.XmlElementNames;
import org.movsim.input.model.OutputInput;
import org.movsim.input.model.output.FloatingCarInput;
import org.movsim.input.model.output.SpatioTemporalInput;
import org.movsim.input.model.output.TrajectoriesInput;
import org.movsim.input.model.output.TravelTimesInput;
import org.movsim.input.model.output.impl.FloatingCarInputImpl;
import org.movsim.input.model.output.impl.SpatioTemporalInputImpl;
import org.movsim.input.model.output.impl.TrajectoriesInputImpl;
import org.movsim.input.model.output.impl.TravelTimesInputImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class OutputInputImpl.
 */
public class OutputInputImpl implements OutputInput {

    /** The floating car input. */
    private FloatingCarInput floatingCarInput;

    /** The macro input. */
    private SpatioTemporalInput spatioTemporalInput;

    /** The trajectories input. */
    private TrajectoriesInput trajectoriesInput;
    
    
    private TravelTimesInput travelTimesInput;

    /**
     * Instantiates a new output input impl.
     * 
     * @param elem
     *            the elem
     */
    public OutputInputImpl(Element elem) {
        parseElement(elem);
    }

    /**
     * Parses the element.
     * 
     * @param elem
     *            the elem
     */
    private void parseElement(Element elem) {

        floatingCarInput = new FloatingCarInputImpl(elem.getChild(XmlElementNames.OutputFloatingCarData));

        spatioTemporalInput = new SpatioTemporalInputImpl(elem.getChild(XmlElementNames.OutputSpatioTemporal));

        trajectoriesInput = new TrajectoriesInputImpl(elem.getChild(XmlElementNames.OutputTrajectories));

        
        // TODO treat all elements similarly
        if(elem.getChild(XmlElementNames.OutputTravelTimes)!=null){
            travelTimesInput = new TravelTimesInputImpl(elem.getChild(XmlElementNames.OutputTravelTimes));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.movsim.input.model.impl.OutputInput#getFloatingCarInput()
     */
    @Override
    public FloatingCarInput getFloatingCarInput() {
        return floatingCarInput;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.movsim.input.model.impl.OutputInput#getMacroInput()
     */
    @Override
    public SpatioTemporalInput getSpatioTemporalInput() {
        return spatioTemporalInput;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.movsim.input.model.OutputInput#getTrajectoriesInput()
     */
    @Override
    public TrajectoriesInput getTrajectoriesInput() {
        return trajectoriesInput;
    }

    @Override
    public TravelTimesInput getTravelTimesInput() {
        return travelTimesInput;
    }

}
