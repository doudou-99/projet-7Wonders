package modele;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainJoueur {
    private PaquetCarte paquetCarte;
    private Map<String,Joueur> joueurs;
    private Map<String, Integer> nombreJoueur;
    private Tour tour;

    public MainJoueur(String joueur,PaquetCarte paquetCarte){
        this.joueurs=new HashMap<>();
        this.nombreJoueur=new HashMap<>();
        this.paquetCarte=paquetCarte;
    }
}
