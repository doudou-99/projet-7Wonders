package modeles;

import modeles.dao.BaseMongo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MainJoueur {
    private PaquetCarte paquetCarte;
    private Map<String,Joueur> joueurs;
    private Map<String, Integer> nombreJoueur;
    private Map<String,String> choixJoueur;
    private GestionTour gestionTour;

    public MainJoueur(){}

    public MainJoueur(PaquetCarte paquetCarte){
        this.joueurs=new HashMap<>();
        this.nombreJoueur=new HashMap<>();
        this.choixJoueur=new HashMap<>();
        this.paquetCarte=paquetCarte;
        this.donnerMainCarte();
    }
    public void donnerMainCarte(){
        for(Joueur joueur: BaseMongo.getBase().getJoueurList()){
            if (joueurs.containsKey(String.valueOf(nombreJoueur.get(joueur.getPseudo()))) && this.choixJoueur.containsKey(joueur.getPseudo())) {
                joueur.cartesEnPossession(paquetCarte);
            }
        }
    }

    public Joueur voisinDroite(String joueur) {
        Joueur joueur1 = BaseMongo.getBase().getJoueur(joueur);
        int voisin = 0;
        if (joueurs.containsValue(joueur1) && nombreJoueur.containsKey(joueur)) {
            switch (nombreJoueur.get(joueur)) {
                case 0:
                    voisin = 1;
                    break;
                case 1:
                    voisin = 2;
                    break;
                case 2:
                    voisin = 3;
                    break;
                case 3:
                    voisin = 0;
                    break;
            }
        }
        return joueurs.get(String.valueOf(voisin));
    }

    public Joueur voisinGauche(String joueur) {
        Joueur joueur1 = BaseMongo.getBase().getJoueur(joueur);
        int voisin = 0;
        if (joueurs.containsValue(joueur1) && nombreJoueur.containsKey(joueur)) {
            switch (nombreJoueur.get(joueur)) {
                case 0:
                    voisin = 3;
                    break;
                case 1:
                    voisin = 0;
                    break;
                case 2:
                    voisin = 1;
                    break;
                case 3:
                    voisin = 2;
                    break;
            }
        }
        return joueurs.get(String.valueOf(voisin));
    }

    public boolean tourJoueursFini(){
        return choixJoueur.get(joueurs.get("0").getPseudo()).length() == 1 && choixJoueur.get(joueurs.get("1").getPseudo()).length() == 1
                && choixJoueur.get(joueurs.get("2").getPseudo()).length() == 1 && choixJoueur.get(joueurs.get("3").getPseudo()).length() == 1
                ;
    }



    public void passageMainSuivante() {
        if (paquetCarte.getTour().getTour().getAge().getSensDeRotation().equals("Horaire")) {
            if (tourJoueursFini()) {
                for (Joueur j : joueurs.values()) {
                    voisinGauche(joueurs.get(String.valueOf(nombreJoueur.get(j.getPseudo()))).getPseudo()).setListeCartes(
                            joueurs.get(String.valueOf(nombreJoueur.get(j.getPseudo()))).getListeCartes());
                    if (joueurs.get(String.valueOf(nombreJoueur.get(j.getPseudo()))).getListeCartes().size() == 1) {
                        joueurs.get(String.valueOf(nombreJoueur.get(j.getPseudo()))).getDefausse().ajoutCarte(
                                joueurs.get(String.valueOf(nombreJoueur.get(j.getPseudo()))).getListeCartes().get(0)
                        );
                    }
                }
            }
        } else {
            if (tourJoueursFini()) {
                for (Joueur j : joueurs.values()) {
                    voisinDroite(joueurs.get(String.valueOf(nombreJoueur.get(j.getPseudo()))).getPseudo()).setListeCartes(
                            joueurs.get(String.valueOf(nombreJoueur.get(j.getPseudo()))).getListeCartes());
                    if (joueurs.get(String.valueOf(nombreJoueur.get(j.getPseudo()))).getListeCartes().size() == 1) {
                        joueurs.get(String.valueOf(nombreJoueur.get(j.getPseudo()))).getDefausse().ajoutCarte(
                                joueurs.get(String.valueOf(nombreJoueur.get(j.getPseudo()))).getListeCartes().get(0)
                        );
                    }
                }

            }
        }
    }

    public PaquetCarte getPaquetCarte() {
        return paquetCarte;
    }

    public void setPaquetCarte(PaquetCarte paquetCarte) {
        this.paquetCarte = paquetCarte;
    }

    public Map<String, Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(Map<String, Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public Map<String, Integer> getNombreJoueur() {
        return nombreJoueur;
    }

    public void setNombreJoueur(Map<String, Integer> nombreJoueur) {
        this.nombreJoueur = nombreJoueur;
    }

    public Map<String, String> getChoixJoueur() {
        return choixJoueur;
    }

    public void setChoixJoueur(Map<String, String> choixJoueur) {
        this.choixJoueur = choixJoueur;
    }

    public GestionTour getGestionTour() {
        return gestionTour;
    }

    public void setGestionTour(GestionTour gestionTour) {
        this.gestionTour = gestionTour;
    }

    @Override
    public String toString() {
        return "MainJoueur{" +
                "paquetCarte=" + paquetCarte +
                ", joueurs=" + joueurs +
                ", nombreJoueur=" + nombreJoueur +
                ", choixJoueur=" + choixJoueur +
                ", gestionTour=" + gestionTour +
                '}';
    }
}
