package modeles;

import modeles.dao.BaseMongo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaquetCarte {
    private List<Carte> cartesCollection;
    private Age age;
    private Tour tour;

    public PaquetCarte( Age age) {
        this.cartesCollection = new ArrayList<>();
        this.age=age;
        this.tour=tour;
    }

    public void setCartesCollection(List<Carte> cartesColllection) {
        this.cartesCollection = cartesColllection;
    }

    public List<Carte> getCartesColllection() {
        return cartesCollection;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }


    public List<Carte> carteGuilde(){
        List<Carte> guildes = new ArrayList<>();
        if (age.getId().equals("age3")) {

            for (Carte c : BaseMongo.getBase().getCartesAges(age.getId())) {
                if (c.getType().equals("Guilde")) {
                    guildes.add(c);
                }
            }
        }
        return guildes;
    }

    public List<Carte> distribuire(int nbJoueurs){

        if (age.getId().equals("age1") ){
            Collections.shuffle(BaseMongo.getBase().getCartesAges(age.getId()));
            cartesCollection.addAll(BaseMongo.getBase().getCartesAges(age.getId()));
        }
        else if (age.getId().equals("age2") ){
            if (cartesCollection.containsAll(BaseMongo.getBase().getCartesAges(age.getId()))){
                cartesCollection.removeAll(BaseMongo.getBase().getCartesAges(age.getId()));
            }
            Collections.shuffle(BaseMongo.getBase().getCartesAges(age.getId()));
            cartesCollection.addAll(BaseMongo.getBase().getCartesAges(age.getId()));
        }
        else if (age.getId().equals("age3")){
            if (cartesCollection.containsAll(BaseMongo.getBase().getCartesAges(age.getId()))){
                cartesCollection.removeAll(BaseMongo.getBase().getCartesAges(age.getId()));
            }
            Collections.shuffle(BaseMongo.getBase().getCartesAges(age.getId()));
            List<Carte> guildes = this.carteGuilde();
            Collections.shuffle(guildes);
            cartesCollection.addAll(BaseMongo.getBase().getCartesAges(age.getId()));
            if (cartesCollection.containsAll(guildes)){
                for (int i=0;i<guildes.size();i++){
                    if (i>=nbJoueurs+2){
                        cartesCollection.remove(guildes.get(i));
                    }
                }
            }
        }
        return cartesCollection;
    }


    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
