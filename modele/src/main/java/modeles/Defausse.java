package modeles;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.ArrayList;
import java.util.Collection;

public class Defausse {
    @BsonProperty("listeDesCartesDefausse")
    private Collection<Carte> carteDefaussees;

    public Defausse(){
        this.carteDefaussees=new ArrayList<>();
    }



    public Collection<Carte> getCarteDefaussees() {
        return carteDefaussees;
    }

    public void setCarteDefaussees(Collection<Carte> carteDefaussees) {
        this.carteDefaussees = carteDefaussees;
    }

    public void ajoutCarte(Carte carte){
        this.carteDefaussees.add(carte);
    }

    public void retirerCarte(Carte carte){
        this.carteDefaussees.remove(carte);
    }

    @Override
    public String toString() {
        return "Defausse{" +
                "carteDefaussees=" + carteDefaussees +
                '}';
    }
}
