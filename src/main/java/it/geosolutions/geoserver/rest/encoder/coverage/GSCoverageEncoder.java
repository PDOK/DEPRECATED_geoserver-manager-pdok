/*
 *  GeoServer-Manager - Simple Manager Library for GeoServer
 *
 *  Copyright (C) 2007,2011 GeoSolutions S.A.S.
 *  http://www.geo-solutions.it
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package it.geosolutions.geoserver.rest.encoder.coverage;

import org.jdom.Element;

import it.geosolutions.geoserver.rest.encoder.GSResourceEncoder;
import it.geosolutions.geoserver.rest.encoder.dimensions.GSCoverageDimensionEncoder;
import it.geosolutions.geoserver.rest.encoder.metadata.GSDimensionInfoEncoder;
import it.geosolutions.geoserver.rest.encoder.utils.ElementUtils;

/**
 * Creates an XML
 * 
 * @author ETj (etj at geo-solutions.it)
 * @author Carlo Cancellieri - carlo.cancellieri@geo-solutions.it
 * 
 */
public class GSCoverageEncoder extends GSResourceEncoder {

    public final static String DIMENSIONS = "dimensions";
    public final static String STRING = "string";
    
    final private Element dimensionsEncoder = new Element(DIMENSIONS);
    
    public GSCoverageEncoder() {
        super("coverage");
    }

    /**
     * @param key
     * @param dimensionInfo
     * @deprecated Use {@link GSResourceEncoder#addMetadataDimension(String, GSDimensionInfoEncoder)} this method will be removed soon
     */
    protected void addMetadata(String key, GSDimensionInfoEncoder dimensionInfo) {
        super.addMetadata(key, dimensionInfo);
    }

    /**
     * @deprecated Use {@link GSResourceEncoder#setMetadataDimension(String, GSDimensionInfoEncoder)} this method will be removed soon
     * @param key
     * @param dimensionInfo
     */
    public void setMetadata(String key, GSDimensionInfoEncoder dimensionInfo) {
        super.setMetadata(key, dimensionInfo);
    }

    /**
     * Adds a CoverageDimensionInfo to the GeoServer Resource
     * 
     * @param coverageDimensionInfo
     * 
     */
    public void addCoverageDimensionInfo(GSCoverageDimensionEncoder coverageDimensionInfo) {
        if (ElementUtils.contains(getRoot(), DIMENSIONS) == null)
            addContent(dimensionsEncoder);
        dimensionsEncoder.addContent(coverageDimensionInfo.getRoot());
    }

    /**
     * Adds quickly a CoverageDimensionInfo to the GeoServer Resource
     * 
     * @param name
     * @param description
     * @param rangeMin
     * @param rangeMax
     * @param unit
     * @param dimensionType
     */
    public void addCoverageDimensionInfo(String name, String description, String rangeMin,
            String rangeMax, String unit, String dimensionType) {
        final GSCoverageDimensionEncoder coverageDimensionEncoder = new GSCoverageDimensionEncoder(
                name, description, rangeMin, rangeMax, unit, dimensionType);
        addCoverageDimensionInfo(coverageDimensionEncoder);
    }

    /**
     * Deletes a CoverageDimensionInfo from the list using the CoverageDimension Name (CoverageDimensionInfo content)
     * 
     * @param coverageDimensionName
     * @return true if something is removed, false otherwise
     */
    public boolean delCoverageDimensionInfo(final String coverageDimensionName) {
        return (dimensionsEncoder.removeContent(GSCoverageDimensionEncoder
                .getFilterByContent(coverageDimensionName))).size() == 0 ? false : true;
    }
    
    public final static String SUPPORTEDFORMATS = "supportedFormats";
    final private Element supportedFormats = new Element(SUPPORTEDFORMATS);
    
    public void addsupportedFormats(String[] supportedformats) {       
      
        for (String format : supportedformats) {        	
        	supportedFormats.addContent(new Element(STRING).setText(format));
        }
        
        addContent(supportedFormats);    	
    }   
    
	private final static String NATIVEFORMAT = "nativeFormat";

	protected void addNativeFormat(final String nativeformat) {
		add(NATIVEFORMAT, nativeformat);
	}

	public void setNativeFormat(final String nativeformat) {
		set(NATIVEFORMAT, nativeformat);
	}
	
	public final static String REQUESTSRS = "requestSRS";
	final private Element requestsrs = new Element(REQUESTSRS);
	
    public void addRequestSRS(String[] requestsrslist) {       
        
        for (String request : requestsrslist) {        	
        	requestsrs.addContent(new Element(STRING).setText(request));
        }
        
        addContent(requestsrs);    	
    }   

	public final static String RESPONSESRS = "responseSRS";
	final private Element responsesrs = new Element(RESPONSESRS);

    public void addResponseSRS(String[] responsesrslist) {       
        
        for (String response : responsesrslist) {        	
        	responsesrs.addContent(new Element(STRING).setText(response));
        }
        
        addContent(responsesrs);    	
    }   
}
