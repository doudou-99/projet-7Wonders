package modeles;

import modeles.dao.BaseMongo;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PaquetCarte {

    private List<Carte> cartesCollection;
    private GestionTour tour;

    public PaquetCarte(){}

    public PaquetCarte( Age age) {
        this.cartesCollection = new ArrayList<>();
        this.tour=new GestionTour(age);
    }

    public List<Carte> carteGuilde(List<Carte> cartes){
        List<Carte> guildes = new ArrayList<>();
        if (tour.getTour().getAge().getId().equals("age3")) {

            for (Carte c : cartes) {
                if (c.getType().equals("Guilde")) {
                    guildes.add(c);
                }
            }
        }
        return guildes;
    }
    public boolean testeNombreJoueur(int nbJoueurs,String age){
        for(Carte carte: BaseMongo.getBase().getCartesAges(age)) {
            if (carte.getChiffre().contains(nbJoueurs) && carte.getChiffre().contains(nbJoueurs-1)) {
                return true;
            }
        }
        return false;
    }

    public boolean testeCarteGuilde(List<Carte> cartes){
        for (Carte carte: cartes){
            if (carte.getType().equals("Guilde")){
                return true;
            }
        }
        return false;
    }

    public int nbGuildes(List<Carte> cartes){
        int nb=0;
        for(Carte carte: cartes){
            if (carte.getType().equals("Guilde")){
                nb+=1;
            }
        }
        return nb;
    }

    public List<Carte> distribuire(int nbJoueurs){
        if (tour.getTour().getAge().getId().equals("age1") && testeNombreJoueur(nbJoueurs, tour.getTour().getAge().getId())) {
            Collections.shuffle(BaseMongo.getBase().getCartesAges(tour.getTour().getAge().getId()));
            cartesCollection.addAll(BaseMongo.getBase().getCartesAges(tour.getTour().getAge().getId()));
            cartesCollection.addAll(BaseMongo.getBase().getCartesAges(tour.getTour().getAge().getId()));
        } else if (tour.getTour().getAge().getId().equals("age2") && testeNombreJoueur(nbJoueurs, tour.getTour().getAge().getId())) {
            if (cartesCollection.containsAll(BaseMongo.getBase().getCartesAges(tour.getTour().getAge().getId()))) {
                cartesCollection.clear();
            }
            Collections.shuffle(BaseMongo.getBase().getCartesAges(tour.getTour().getAge().getId()));
            cartesCollection.addAll(BaseMongo.getBase().getCartesAges(tour.getTour().getAge().getId()));
            cartesCollection.addAll(BaseMongo.getBase().getCartesAges(tour.getTour().getAge().getId()));
        } else if (tour.getTour().getAge().getId().equals("age3") && testeNombreJoueur(nbJoueurs, tour.getTour().getAge().getId())) {
            if (cartesCollection.containsAll(BaseMongo.getBase().getCartesAges(tour.getTour().getAge().getId()))) {
                cartesCollection.clear();
            }
            Collections.shuffle(BaseMongo.getBase().getCartesAges(tour.getTour().getAge().getId()));
            cartesCollection.addAll(BaseMongo.getBase().getCartesAges(tour.getTour().getAge().getId()));
            cartesCollection.addAll(BaseMongo.getBase().getCartesAges(tour.getTour().getAge().getId()));
            List<Carte> guildes = this.carteGuilde(cartesCollection);
            Collections.shuffle(guildes);
            if (this.testeCarteGuilde(cartesCollection) && nbGuildes(cartesCollection)>nbJoueurs+2){
                for (int i = 0; i < guildes.size(); i++) {
                    if (i >= nbJoueurs + 2) {
                        cartesCollection.remove(guildes.get(i));
                    }
                }
            }
        }

        return cartesCollection;
    }

    public void setCartesCollection(List<Carte> cartesColllection) {
        this.cartesCollection = cartesColllection;
    }

    public List<Carte> getCartesCollection() {
        return cartesCollection;
    }





    public GestionTour getTour() {
        return tour;
    }

    public void setTour(GestionTour tour) {
        this.tour = tour;
    }

    @Override
    public String toString() {
        return "PaquetCarte{" +
                "cartesCollection=" + cartesCollection +
                ", tour=" + tour +
                '}';
    }
}
