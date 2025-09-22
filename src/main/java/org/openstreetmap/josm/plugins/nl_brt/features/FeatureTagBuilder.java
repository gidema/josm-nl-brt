package org.openstreetmap.josm.plugins.nl_brt.features;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.openstreetmap.josm.data.osm.OsmPrimitive;
import org.openstreetmap.josm.plugins.nl_brt.io.BrtFeatureTags;
import org.openstreetmap.josm.tools.Logging;

public class FeatureTagBuilder {
    private final String feature;
    private final Map<BrtFeatureTags, Map<String,String>> tagMaps;
    private final Set<BrtFeatureTags> unknownFeatureTypes = new HashSet<>();
    
    public FeatureTagBuilder(String feature, Map<BrtFeatureTags, Map<String, String>> tagMaps) {
        super();
        this.feature = feature;
        this.tagMaps = tagMaps;
    }

    public void buildTags(OsmPrimitive primitive, BrtFeatureTags featureTags) {
        var tagMap = tagMaps.get(featureTags);
        if (tagMap != null) {
            tagMap.forEach(primitive::put);
        }
        else {
            if (unknownFeatureTypes.add(featureTags)) {
                Logging.info("Unknown type: {}:{}:{}:{}", feature,
                        Objects.requireNonNullElse(featureTags.getType(), ""), 
                        Objects.requireNonNullElse(featureTags.getFunctie(), ""), 
                        Objects.requireNonNullElse(featureTags.getFunctioneelVoorkomen(), ""));
            }
        }
    }
}
