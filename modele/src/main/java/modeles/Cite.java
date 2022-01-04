package modeles;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Cite {
    @BsonProperty("_id")
    private String id;
    private List<Carte> cartes;

    public Cite(){}

    public Cite(@BsonProperty("_id") String id) {
        this.id = id;
        this.cartes=new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Carte> getCartes() {
        return cartes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCartes(List<Carte> cartes) {
        this.cartes = cartes;
    }

    public void ajoutCarte(Carte carte){
        this.cartes.add(carte);
    }

    @Override
    public String toString() {
        return "Cite{" +
                "id='" + id + '\'' +
                ", cartes=" + cartes +
                '}';
    }
}
