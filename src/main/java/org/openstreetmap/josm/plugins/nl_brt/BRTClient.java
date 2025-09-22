package org.openstreetmap.josm.plugins.nl_brt;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import nl.pdok.ogc.brt.ApiClient;
import nl.pdok.ogc.brt.ApiException;
import nl.pdok.ogc.brt.api.FeaturesApi;
import nl.pdok.ogc.brt.model.FeatureCollectionGeoJSONWaterdeelVlak;

public class BRTClient {
    private URI crs;
    private URI bboxCrs;
    private final ApiClient apiClient = new ApiClient();

    public BRTClient() {
        super();
        try {
            crs = new URI("http://www.opengis.net/def/crs/OGC/1.3/CRS84");
            bboxCrs = new URI("http://www.opengis.net/def/crs/EPSG/0/28992");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        apiClient.setBasePath("https://api.pdok.nl/brt/top10nl/ogc/v1");
    }
    
    public FeatureCollectionGeoJSONWaterdeelVlak getWaterdeelVlak(List<BigDecimal> bbox) throws ApiException {
        var api = new FeaturesApi(apiClient);
        return api.waterdeelVlakGetFeatures("json", 1000, crs, bbox, crs, null, null, null, null);
    }
}
