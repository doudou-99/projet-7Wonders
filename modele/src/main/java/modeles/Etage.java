package modeles;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.io.Serializable;
import java.util.Collection;

public class Etage implements Serializable {
    @BsonProperty("_id")
    private String id;
    private CoutConstructionEtage coutConstruction;
    private Collection<Effet> effet;

    public Etage(){}
    public Etage(@BsonProperty("_id") String id, Collection<Effet> effet,CoutConstructionEtage coutConstruction) {
        this.id = id;
        this.effet = effet;
        this.coutConstruction=coutConstruction;
    }

    public String getId() {
        return id;
    }

    public Collection<Effet> getEffet() {
        return effet;
    }

    public CoutConstructionEtage getCoutConstruction() {
        return coutConstruction;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCoutConstruction(CoutConstructionEtage coutConstruction) {
        this.coutConstruction = coutConstruction;
    }

    public void setEffet(Collection<Effet> effet) {
        this.effet = effet;
    }

    @Override
    public String toString() {
        return "Etage{" +
                "id='" + id + '\'' +
                ", coutConstruction=" + coutConstruction +
                ", effet='" + effet + '\'' +
                '}';
    }
}
