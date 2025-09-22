package org.openstreetmap.josm.plugins.nl_brt.features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.csv4pojoparser.util.impl.CSVReaderImpl;
import org.openstreetmap.josm.plugins.nl_brt.io.BrtFeatureTags;
import org.openstreetmap.josm.plugins.nl_brt.NlBrtPlugin;
import org.openstreetmap.josm.tools.Logging;

public class FeatureTagBuilderCache {
    private static final String CLASS_NAME_PREFIX = "nl.pdok.ogc.brt.model.FeatureGeoJSON";
    private static final Map<Class<?>, FeatureTagBuilder> cache = new HashMap<>();
    
    static {
        initialize();
    }
    
    private static void initialize() {
        try (var is = NlBrtPlugin.class.getResourceAsStream("brt_tags.csv");) {
            // Read the file and group by featureName
            Map<String, List<FeatureDto>> perFeatureMap = new HashMap<>();
            new CSVReaderImpl().createPojoStreamFromCSVInputStream(FeatureDto.class, is)
            .forEach(dto -> {
                List<FeatureDto> dtoList = perFeatureMap.computeIfAbsent(dto.getFeature(), feature -> new ArrayList<>());
                dtoList.add(dto);
            });
            // Create a FeatureTagBuilder per feature
            perFeatureMap.forEach((feature, dtoList) -> {
                Map<BrtFeatureTags, Map<String, String>> map = new HashMap<>();
                dtoList.forEach(dto -> {
                    var featureTags = new BrtFeatureTags(dto.getType(), dto.getFunctie(), dto.getFysiekVoorkomen());
                    var tagMap = map.computeIfAbsent(featureTags, s -> new HashMap<>());
                    tagMap.put(dto.getKey(), dto.getValue());
                });
                var builder = new FeatureTagBuilder(feature, map);
                var className = CLASS_NAME_PREFIX + capitalize(feature);
                try {
                    var clazz = Class.forName(className);
                    cache.put(clazz, builder);
                } catch (ClassNotFoundException e) {
                    Logging.warn(e);
                }
            });
        } catch (Throwable t) {
            Logging.warn(t);
        }
    }
    
    public static FeatureTagBuilder forClass(Class<?> clazz) {
        return cache.get(clazz);
    }
    
    private static String capitalize(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
