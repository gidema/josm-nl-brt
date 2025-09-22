package org.openstreetmap.josm.plugins.nl_brt.io;

import java.util.Objects;

public class BrtFeatureTags {
    private final String type;
    private final String functie;
    private final String functioneelVoorkomen;

    public BrtFeatureTags(String type, String functie, String functioneelVoorkomen) {
        super();
        this.type = type;
        this.functie = functie;
        this.functioneelVoorkomen = functioneelVoorkomen;
    }

    public String getType() {
        return type;
    }

    public String getFunctie() {
        return functie;
    }

    public String getFunctioneelVoorkomen() {
        return functioneelVoorkomen;
    }

    @Override
    public int hashCode() {
        return Objects.hash(functie, functioneelVoorkomen, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BrtFeatureTags other = (BrtFeatureTags) obj;
        return Objects.equals(functie, other.functie)
                && Objects.equals(functioneelVoorkomen, other.functioneelVoorkomen)
                && Objects.equals(type, other.type);
    }
}
