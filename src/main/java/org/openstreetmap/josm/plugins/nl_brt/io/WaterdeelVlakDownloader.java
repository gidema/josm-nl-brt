package org.openstreetmap.josm.plugins.nl_brt.io;

import org.openstreetmap.josm.gui.MainApplication;
import org.openstreetmap.josm.plugins.nl_brt.BRTClient;

import nl.pdok.ogc.brt.ApiException;
import nl.pdok.ogc.brt.model.FeatureGeoJSONWaterdeelVlak;

public class WaterdeelVlakDownloader extends AbstractFeatureDownloader<FeatureGeoJSONWaterdeelVlak> {

    public WaterdeelVlakDownloader(OgcLayerManager layerManager) {
        super(FeatureGeoJSONWaterdeelVlak.class, layerManager);
    }

    @Override
    public TaskStatus call() {
        var client = new BRTClient();
        var bbox = getBoundary().toBigDecimalList();
        try {
            var features = client.getWaterdeelVlak(bbox);
            features.getFeatures().forEach(feature -> {
                // TODO filter obsolete features with the feature request. Not here. 
                if (getFeatureIdCache().add(feature.getProperties().getLokaalId())) {
                    addToOsm(feature);
                }
            });
            MainApplication.getMainPanel().repaint();
        } catch (ApiException e) {
            return TaskStatus.exception(e);
        }
        return TaskStatus.ok;
    }

    @Override
    public void addToOsm(FeatureGeoJSONWaterdeelVlak feature) {
        var geometry = feature.getGeometry().getActualInstance();
        var osmPrimitive = getPrimitiveFactory().createPrimitive(geometry, false);
        osmPrimitive.put("source", "NL:BRT");
        osmPrimitive.put("ref:NL:BRT", feature.getProperties().getLokaalId());
        var type = feature.getProperties().getTypewater();
        var functie = feature.getProperties().getFunctie();
        var featureTags = new BrtFeatureTags(type, functie, null);
        getTagBuilder().buildTags(osmPrimitive, featureTags);
    }
}
