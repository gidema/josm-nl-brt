package org.openstreetmap.josm.plugins.nl_brt.data;

import java.math.BigDecimal;
import java.util.List;

import org.openstreetmap.josm.shared.nl_ogc.data.GeometryHandler;

import nl.pdok.ogc.brt.model.MultipolygonGeoJSON;
import nl.pdok.ogc.brt.model.PolygonGeoJSON;

public class BrtGeometryHandler implements GeometryHandler {

    @Override
    public List<List<List<BigDecimal>>> getPolygonCoordinates(Object polygon) {
        if (polygon instanceof PolygonGeoJSON) {
            return ((PolygonGeoJSON)polygon).getCoordinates();
        }
        return null;
    }

    @Override
    public List<List<List<List<BigDecimal>>>> getMultipolygonCoordinates(Object multipolygon) {
        if (multipolygon instanceof MultipolygonGeoJSON) {
            return ((MultipolygonGeoJSON)multipolygon).getCoordinates();
        }
        return null;
    }
}
