package modele;

import java.util.*;

public class Tour {
    private int nombreTourEnCours;
    private Map<String,List<Carte>> cartesJouees;
    private Age age;

    public Tour(){}


    public Tour(Age age,int nombreTourEnCours) {
        this.nombreTourEnCours = nombreTourEnCours;
        this.cartesJouees = new HashMap<>();
        this.age=age;
    }

    public int getNombreTourEnCours() {
        return nombreTourEnCours;
    }

    public void setNombreTourEnCours(int nombreTourEnCours) {
        this.nombreTourEnCours = nombreTourEnCours;
    }

    public Map<String,List<Carte>> getCartesJouees() {
        return cartesJouees;
    }

    public void setCartesJouees(Map<String, List<Carte>> cartes) {
        this.cartesJouees = cartes;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "nombreTourEnCours=" + nombreTourEnCours +
                ", cartesJouees=" + cartesJouees +
                ", age="+age+
                '}';
    }
}
