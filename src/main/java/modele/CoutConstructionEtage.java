package modele;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CoutConstructionEtage {
    private Cout cout;
    @BsonProperty("carteUtilisees")
    private Collection<Carte> cartesUtilisees;

    public CoutConstructionEtage(){}
    public CoutConstructionEtage(Cout cout) {
        this.cout = cout;
        this.cartesUtilisees=new ArrayList<>();
    }

    public Cout getCout() {
        return cout;
    }

    public Collection<Carte> getCartesUtilisees() {
        return cartesUtilisees;
    }

    public void setCout(Cout cout) {
        this.cout = cout;
    }

    public void setCartesUtilisees(Collection<Carte> cartesUtilisees) {
        this.cartesUtilisees = cartesUtilisees;
    }


    public void ajouterCarteEtage(Carte carte){
        this.cartesUtilisees.add(carte);
    }
    @Override
    public String toString() {
        return "CoutConstructionEtage{" +
                "cout='" + cout + '\'' +
                ", cartesUtilisees=" + cartesUtilisees +
                '}';
    }
}
