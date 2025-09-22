package org.openstreetmap.josm.plugins.nl_brt.io;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.FutureTask;

import org.openstreetmap.josm.data.osm.DataSet;
import org.openstreetmap.josm.plugins.nl_brt.data.PrimitiveFactory;
import org.openstreetmap.josm.plugins.nl_brt.features.FeatureTagBuilder;
import org.openstreetmap.josm.plugins.nl_brt.features.FeatureTagBuilderCache;
import org.openstreetmap.josm.plugins.nl_brt.jts.Boundary;

public abstract class AbstractFeatureDownloader<T> implements FeatureDownloader<T> {

    private OgcLayerManager layerManager;
    private Boundary boundary;
    private final FeatureTagBuilder tagBuilder; 
    private final Set<String> featureIdCache = new HashSet<>();

    public AbstractFeatureDownloader(Class<T> clazz, OgcLayerManager layerManager) {
        super();
        this.layerManager = layerManager;
        tagBuilder = FeatureTagBuilderCache.forClass(clazz); 
    }

    protected static String getFeatureName(Class<?> clazz) {
        var className = clazz.getName();
        return className.substring(0, 14).toLowerCase() + className.substring(15); 
    }

    public FeatureTagBuilder getTagBuilder() {
        return tagBuilder;
    }

    public Set<String> getFeatureIdCache() {
        return featureIdCache;
    }

    @SuppressWarnings("hiding")
    @Override
    public FutureTask<TaskStatus> getFetchTask(Boundary boundary) {
        this.boundary = boundary;
        return new FutureTask<>(this);
    }
    
    protected Boundary getBoundary() {
        return boundary;
    }

    protected DataSet getDataSet() {
        return layerManager.getDataSet();
    }
    
    protected PrimitiveFactory getPrimitiveFactory() {
        return layerManager.getPrimitiveFactory();
    }
    
    @Override
    public void clearCache() {
        this.featureIdCache.clear();
    }
}
