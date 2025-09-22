package org.openstreetmap.josm.plugins.nl_brt.features;

import org.csv4pojoparser.annotation.FieldType;
import org.csv4pojoparser.annotation.Type;

public class FeatureDto {
    @FieldType(dataType = Type.STRING, csvColumnName = "feature")
    private String feature;
    @FieldType(dataType = Type.STRING, csvColumnName = "type")
    private String type;
    @FieldType(dataType = Type.STRING, csvColumnName = "functie")
    private String functie;
    @FieldType(dataType = Type.STRING, csvColumnName = "fysiekvoorkomen")
    private String fysiekVoorkomen;
    @FieldType(dataType = Type.STRING, csvColumnName = "key")
    private String key;
    @FieldType(dataType = Type.STRING, csvColumnName = "value")
    private String value;

    public FeatureDto() {
        super();
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public String getFysiekVoorkomen() {
        return fysiekVoorkomen;
    }

    public void setFysiekVoorkomen(String fysiekVoorkomen) {
        this.fysiekVoorkomen = fysiekVoorkomen;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
