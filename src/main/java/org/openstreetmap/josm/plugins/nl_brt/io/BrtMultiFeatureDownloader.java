package org.openstreetmap.josm.plugins.nl_brt.io;

import java.util.List;

import org.openstreetmap.josm.plugins.nl_brt.data.BrtGeometryHandler;
import org.openstreetmap.josm.shared.nl_ogc.data.OgcLayerManager;
import org.openstreetmap.josm.shared.nl_ogc.io.FeatureDownloader;
import org.openstreetmap.josm.shared.nl_ogc.io.MultiFeatureDownloader;

public class BrtMultiFeatureDownloader extends MultiFeatureDownloader {
    private static OgcLayerManager layerManager = new OgcLayerManager("NL_BRT", new BrtGeometryHandler());
    private static List<FeatureDownloader<?>> downloaders = List.of(
       new WaterdeelVlakDownloader(layerManager));
    private boolean cancelled = false;
    
    public BrtMultiFeatureDownloader() {
        super(layerManager, downloaders);
    }
}
