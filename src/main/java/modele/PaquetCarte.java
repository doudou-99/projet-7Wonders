package modele;

import modele.dao.BaseMongo;

import java.util.*;

public class PaquetCarte {
    private List<Carte> cartesCollection;
    private Age age;

    public PaquetCarte( Age age) {
        this.cartesCollection = new ArrayList<>();
        this.age=age;
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

    public List<Carte> distribuire(int nbJoueurs){

        if (age.getId().equals("age1")){
            Collections.shuffle(BaseMongo.getBase().getCartesAges(age.getId()));
            cartesCollection.addAll(BaseMongo.getBase().getCartesAges(age.getId()));
        }
        else if (age.getId().equals("age2")){
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
            List<Carte> guildes = new ArrayList<>();
            for(Carte c: BaseMongo.getBase().getCartesAges(age.getId())){
                if (c.getType().equals("Guilde")){
                    guildes.add(c);
                }
            }
            Collections.shuffle(guildes);
            if (BaseMongo.getBase().getCartesAges(age.getId()).containsAll(guildes)){
                for (int i=0;i<guildes.size();i++){
                    if (i>=nbJoueurs+2){
                        BaseMongo.getBase().getCartesAges(age.getId()).remove(guildes.get(i));
                    }
                }
            }
            cartesCollection.addAll(BaseMongo.getBase().getCartesAges(age.getId()));
        }
        return cartesCollection;
    }
}
