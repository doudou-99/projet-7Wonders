package modele;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionGuilde {
    private Map<String,Joueur> joueurCommerce;
    private Map<String, Integer> nombreJoueur;
    private Tour tour;


    public GestionGuilde(Tour tour, Joueur joueur, List<Joueur> joueurList){
        this.joueurCommerce=new HashMap<>();
        this.joueurCommerce.put(joueur.getPseudo(),joueur);
        this.nombreJoueur.put(joueur.getPseudo(),0);
        for (int i=1;i<joueurList.size();i++) {
            this.joueurCommerce.put(joueurList.get(i).getPseudo(),joueurList.get(i));
            this.nombreJoueur.put(joueurList.get(i).getPseudo(),i);
        }
        this.tour=tour;
    }

    public void beneficeChaqueTour(){

    }



}
