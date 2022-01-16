package modeles;

import modeles.dao.BaseMongo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionBataille  implements Serializable{
    private final static long serialVersionUID=4L;
    private Bataille bataille;
    private Map<String,Joueur> joueurBataille;
    private Map<String, Integer> nombreJoueur;
    private GestionTour gestionTour;
    private List<String> perdus = new ArrayList<>();
    private List<Integer> numeros=new ArrayList<>();



    public GestionBataille(){
        this.joueurBataille=new HashMap<>();
        this.nombreJoueur=new HashMap<>();
        for (int i = 0; i< BaseMongo.getBase().getJoueurList().size(); i++) {
            this.joueurBataille.put(String.valueOf(i),BaseMongo.getBase().getJoueurList().get(i));
            this.nombreJoueur.put(BaseMongo.getBase().getJoueurList().get(i).getPseudo(),i);
        }
    }

    public Joueur voisinDroite(String joueur) {
        Joueur joueur1 = BaseMongo.getBase().getJoueur(joueur);
        int voisin = 0;
        if (joueurBataille.containsValue(joueur1) && nombreJoueur.containsKey(joueur)) {
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
        return joueurBataille.get(String.valueOf(voisin));
    }

    public Joueur voisinGauche(String joueur) {
        Joueur joueur1 = BaseMongo.getBase().getJoueur(joueur);
        int voisin = 0;
        if (joueurBataille.containsValue(joueur1) && nombreJoueur.containsKey(joueur)) {
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
        return joueurBataille.get(String.valueOf(voisin));
    }


    public void vainqueurBataille(String pseudo,Age age){
        this.bataille = new Bataille();
        String vainqueur="";
        Joueur joueur = BaseMongo.getBase().getJoueur(pseudo);
        if (joueurBataille.containsValue(joueur) && nombreJoueur.containsKey(pseudo)) {
            if (gestionTour.getTour().getAge() == age) {
                this.bataille.setAge(gestionTour.getTour().getAge());
                Jeton jetonvictoire = BaseMongo.getBase().getJeton("victoire", age.getId());
                Jeton jetonDefaite = BaseMongo.getBase().getJeton("defaite", "");
                if (jetonvictoire.getAge().equals(this.gestionTour.getTour().getAge().getId()) && jetonDefaite.getAge().equals("")) {
                    if (joueur.getNombreBoucliers() > voisinDroite(pseudo).getNombreBoucliers()) {
                        joueur.getJetons().add(jetonvictoire);
                        perdus.add(voisinDroite(pseudo).getPseudo());

                        joueur.getListePointsBataille().add(jetonvictoire.getPoints());
                        voisinDroite(pseudo).getListePointsBataille().add(jetonDefaite.getPoints());
                        BaseMongo.getBase().ajoutPointsBataille(pseudo, jetonvictoire.getPoints());
                        BaseMongo.getBase().ajoutPointsBataille(voisinDroite(pseudo).getPseudo(), jetonDefaite.getPoints());
                        BaseMongo.getBase().ajoutJetonJoueur(joueur.getPseudo(), jetonvictoire);
                        BaseMongo.getBase().ajoutJetonJoueur(voisinDroite(pseudo).getPseudo(), jetonDefaite);
                        if (joueur.getNombreBoucliers() > voisinGauche(pseudo).getNombreBoucliers()) {
                            joueur.getJetons().add(jetonvictoire);
                            voisinGauche(pseudo).getJetons().add(jetonDefaite);
                            this.bataille.setNomDuVainqueur(joueur.getPseudo());
                            perdus.add(voisinGauche(pseudo).getPseudo());
                            this.bataille.setNomDuVaincu(perdus);
                            joueur.getListePointsBataille().add(jetonvictoire.getPoints());
                            voisinGauche(pseudo).getListePointsBataille().add(jetonDefaite.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(pseudo, jetonvictoire.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(voisinGauche(pseudo).getPseudo(), jetonDefaite.getPoints());
                            BaseMongo.getBase().ajoutJetonJoueur(joueur.getPseudo(), jetonvictoire);
                            BaseMongo.getBase().ajoutJetonJoueur(voisinGauche(pseudo).getPseudo(), jetonDefaite);
                            BaseMongo.getBase().ajoutBataille(this.bataille);
                            vainqueur = joueur.getPseudo();
                        } else if (joueur.getNombreBoucliers() < voisinGauche(pseudo).getNombreBoucliers()) {
                            joueur.getJetons().add(jetonDefaite);
                            voisinGauche(pseudo).getJetons().add(jetonvictoire);
                            perdus.add(joueur.getPseudo());
                            this.bataille.setNomDuVaincu(perdus);
                            joueur.getListePointsBataille().add(jetonDefaite.getPoints());
                            voisinGauche(pseudo).getListePointsBataille().add(jetonvictoire.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(pseudo, jetonDefaite.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(voisinGauche(pseudo).getPseudo(), jetonvictoire.getPoints());
                            BaseMongo.getBase().ajoutJetonJoueur(joueur.getPseudo(), jetonDefaite);
                            BaseMongo.getBase().ajoutJetonJoueur(voisinGauche(pseudo).getPseudo(), jetonvictoire);
                            BaseMongo.getBase().ajoutBataille(this.bataille);
                        } else if (joueur.getNombreBoucliers() == voisinGauche(pseudo).getNombreBoucliers()) {
                            joueur.getListePointsBataille().add(0);
                            voisinDroite(pseudo).getListePointsBataille().add(0);
                            BaseMongo.getBase().ajoutPointsBataille(pseudo, 0);
                            BaseMongo.getBase().ajoutPointsBataille(voisinDroite(pseudo).getPseudo(), 0);
                            BaseMongo.getBase().ajoutBataille(this.bataille);
                        }

                    }
                    if (joueur.getNombreBoucliers() > voisinGauche(pseudo).getNombreBoucliers()) {
                        joueur.getJetons().add(jetonvictoire);
                        perdus.add(voisinGauche(pseudo).getPseudo());
                        joueur.getListePointsBataille().add(jetonvictoire.getPoints());
                        voisinGauche(pseudo).getListePointsBataille().add(jetonDefaite.getPoints());
                        BaseMongo.getBase().ajoutPointsBataille(joueur.getPseudo(), jetonvictoire.getPoints());
                        BaseMongo.getBase().ajoutPointsBataille(voisinGauche(pseudo).getPseudo(), jetonDefaite.getPoints());
                        BaseMongo.getBase().ajoutJetonJoueur(joueur.getPseudo(), jetonvictoire);
                        BaseMongo.getBase().ajoutJetonJoueur(voisinGauche(pseudo).getPseudo(), jetonDefaite);
                        if (joueur.getNombreBoucliers() > voisinDroite(pseudo).getNombreBoucliers()) {
                            joueur.getJetons().add(jetonvictoire);
                            voisinDroite(pseudo).getJetons().add(jetonDefaite);
                            this.bataille.setNomDuVainqueur(joueur.getPseudo());
                            perdus.add(voisinDroite(pseudo).getPseudo());
                            this.bataille.setNomDuVaincu(perdus);
                            joueur.getListePointsBataille().add(jetonvictoire.getPoints());
                            voisinDroite(pseudo).getListePointsBataille().add(jetonDefaite.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(pseudo, jetonvictoire.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(voisinDroite(pseudo).getPseudo(), jetonDefaite.getPoints());
                            BaseMongo.getBase().ajoutJetonJoueur(joueur.getPseudo(), jetonvictoire);
                            BaseMongo.getBase().ajoutJetonJoueur(voisinDroite(pseudo).getPseudo(), jetonDefaite);
                            BaseMongo.getBase().ajoutBataille(this.bataille);
                            vainqueur = joueur.getPseudo();
                        } else if (joueur.getNombreBoucliers() < voisinDroite(pseudo).getNombreBoucliers()) {
                            joueur.getJetons().add(jetonDefaite);
                            voisinDroite(pseudo).getJetons().add(jetonvictoire);
                            perdus.add(joueur.getPseudo());
                            this.bataille.setNomDuVaincu(perdus);
                            joueur.getListePointsBataille().add(jetonDefaite.getPoints());
                            voisinDroite(pseudo).getListePointsBataille().add(jetonvictoire.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(pseudo, jetonDefaite.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(voisinDroite(pseudo).getPseudo(), jetonvictoire.getPoints());
                            BaseMongo.getBase().ajoutJetonJoueur(joueur.getPseudo(), jetonDefaite);
                            BaseMongo.getBase().ajoutJetonJoueur(voisinDroite(pseudo).getPseudo(), jetonvictoire);
                            BaseMongo.getBase().ajoutBataille(this.bataille);
                        } else if (joueur.getNombreBoucliers() == voisinDroite(pseudo).getNombreBoucliers()) {
                            joueur.getListePointsBataille().add(0);
                            voisinDroite(pseudo).getListePointsBataille().add(0);
                            BaseMongo.getBase().ajoutPointsBataille(pseudo, 0);
                            BaseMongo.getBase().ajoutPointsBataille(voisinDroite(pseudo).getPseudo(), 0);
                            BaseMongo.getBase().ajoutBataille(this.bataille);
                        }

                    }
                    if (joueur.getNombreBoucliers() < voisinDroite(pseudo).getNombreBoucliers()) {
                        joueur.getJetons().add(jetonDefaite);
                        perdus.add(voisinDroite(pseudo).getPseudo());
                        joueur.getListePointsBataille().add(jetonDefaite.getPoints());
                        voisinDroite(pseudo).getListePointsBataille().add(jetonvictoire.getPoints());
                        BaseMongo.getBase().ajoutPointsBataille(pseudo, jetonDefaite.getPoints());
                        BaseMongo.getBase().ajoutPointsBataille(voisinDroite(pseudo).getPseudo(), jetonvictoire.getPoints());
                        BaseMongo.getBase().ajoutJetonJoueur(joueur.getPseudo(), jetonDefaite);
                        BaseMongo.getBase().ajoutJetonJoueur(voisinDroite(pseudo).getPseudo(), jetonvictoire);
                        if (joueur.getNombreBoucliers() > voisinGauche(pseudo).getNombreBoucliers()) {
                            joueur.getJetons().add(jetonvictoire);
                            voisinGauche(pseudo).getJetons().add(jetonDefaite);
                            this.bataille.setNomDuVainqueur(voisinDroite(pseudo).getPseudo());
                            perdus.add(voisinGauche(pseudo).getPseudo());
                            this.bataille.setNomDuVaincu(perdus);
                            joueur.getListePointsBataille().add(jetonvictoire.getPoints());
                            voisinGauche(pseudo).getListePointsBataille().add(jetonDefaite.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(pseudo, jetonvictoire.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(voisinGauche(pseudo).getPseudo(), jetonDefaite.getPoints());
                            BaseMongo.getBase().ajoutJetonJoueur(joueur.getPseudo(), jetonvictoire);
                            BaseMongo.getBase().ajoutJetonJoueur(voisinGauche(pseudo).getPseudo(), jetonDefaite);
                            BaseMongo.getBase().ajoutBataille(this.bataille);
                            vainqueur = voisinDroite(pseudo).getPseudo();
                        } else if (joueur.getNombreBoucliers() < voisinGauche(pseudo).getNombreBoucliers()) {
                            joueur.getJetons().add(jetonDefaite);
                            voisinGauche(pseudo).getJetons().add(jetonvictoire);
                            perdus.add(joueur.getPseudo());
                            this.bataille.setNomDuVaincu(perdus);
                            joueur.getListePointsBataille().add(jetonDefaite.getPoints());
                            voisinGauche(pseudo).getListePointsBataille().add(jetonvictoire.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(pseudo, jetonDefaite.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(voisinGauche(pseudo).getPseudo(), jetonvictoire.getPoints());
                            BaseMongo.getBase().ajoutJetonJoueur(joueur.getPseudo(), jetonDefaite);
                            BaseMongo.getBase().ajoutJetonJoueur(voisinGauche(pseudo).getPseudo(), jetonvictoire);

                            BaseMongo.getBase().ajoutBataille(this.bataille);
                        } else if (joueur.getNombreBoucliers() == voisinGauche(pseudo).getNombreBoucliers()) {
                            joueur.getListePointsBataille().add(0);
                            voisinDroite(pseudo).getListePointsBataille().add(0);
                            BaseMongo.getBase().ajoutPointsBataille(pseudo, 0);
                            BaseMongo.getBase().ajoutPointsBataille(voisinDroite(pseudo).getPseudo(), 0);
                            BaseMongo.getBase().ajoutBataille(this.bataille);
                        }

                    }
                    if (joueur.getNombreBoucliers() < voisinGauche(pseudo).getNombreBoucliers()) {
                        joueur.getJetons().add(jetonDefaite);
                        perdus.add(voisinDroite(pseudo).getPseudo());
                        joueur.getListePointsBataille().add(jetonDefaite.getPoints());
                        voisinGauche(pseudo).getListePointsBataille().add(jetonvictoire.getPoints());
                        BaseMongo.getBase().ajoutPointsBataille(pseudo, jetonDefaite.getPoints());
                        BaseMongo.getBase().ajoutPointsBataille(voisinGauche(pseudo).getPseudo(), jetonvictoire.getPoints());
                        BaseMongo.getBase().ajoutJetonJoueur(joueur.getPseudo(), jetonDefaite);
                        BaseMongo.getBase().ajoutJetonJoueur(voisinGauche(pseudo).getPseudo(), jetonvictoire);
                        if (joueur.getNombreBoucliers() > voisinDroite(pseudo).getNombreBoucliers()) {
                            joueur.getJetons().add(jetonvictoire);
                            voisinDroite(pseudo).getJetons().add(jetonDefaite);
                            perdus.add(voisinDroite(pseudo).getPseudo());
                            this.bataille.setNomDuVainqueur(voisinGauche(pseudo).getPseudo());
                            this.bataille.setNomDuVaincu(perdus);
                            joueur.getListePointsBataille().add(jetonvictoire.getPoints());
                            voisinDroite(pseudo).getListePointsBataille().add(jetonDefaite.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(pseudo, jetonvictoire.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(voisinDroite(pseudo).getPseudo(), jetonDefaite.getPoints());
                            BaseMongo.getBase().ajoutJetonJoueur(joueur.getPseudo(), jetonvictoire);
                            BaseMongo.getBase().ajoutJetonJoueur(voisinDroite(pseudo).getPseudo(), jetonDefaite);
                            BaseMongo.getBase().ajoutBataille(this.bataille);
                            vainqueur = voisinGauche(pseudo).getPseudo();
                        } else if (joueur.getNombreBoucliers() < voisinDroite(pseudo).getNombreBoucliers()) {
                            joueur.getJetons().add(jetonDefaite);
                            voisinDroite(pseudo).getJetons().add(jetonvictoire);
                            perdus.add(joueur.getPseudo());
                            this.bataille.setNomDuVaincu(perdus);
                            joueur.getListePointsBataille().add(jetonDefaite.getPoints());
                            voisinGauche(pseudo).getListePointsBataille().add(jetonvictoire.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(pseudo, jetonDefaite.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(voisinDroite(pseudo).getPseudo(), jetonvictoire.getPoints());
                            BaseMongo.getBase().ajoutJetonJoueur(joueur.getPseudo(), jetonDefaite);
                            BaseMongo.getBase().ajoutJetonJoueur(voisinDroite(pseudo).getPseudo(), jetonvictoire);

                            BaseMongo.getBase().ajoutBataille(this.bataille);
                        } else if (joueur.getNombreBoucliers() == voisinDroite(pseudo).getNombreBoucliers()) {
                            joueur.getListePointsBataille().add(0);
                            voisinDroite(pseudo).getListePointsBataille().add(0);
                            BaseMongo.getBase().ajoutPointsBataille(pseudo, 0);
                            BaseMongo.getBase().ajoutPointsBataille(voisinDroite(pseudo).getPseudo(), 0);
                            BaseMongo.getBase().ajoutBataille(this.bataille);
                        }
                    }
                    if (joueur.getNombreBoucliers() == voisinDroite(pseudo).getNombreBoucliers()) {
                        joueur.getListePointsBataille().add(0);
                        voisinDroite(pseudo).getListePointsBataille().add(0);
                        BaseMongo.getBase().ajoutPointsBataille(pseudo, 0);
                        BaseMongo.getBase().ajoutPointsBataille(voisinDroite(pseudo).getPseudo(), 0);
                        if (joueur.getNombreBoucliers() > voisinGauche(pseudo).getNombreBoucliers()) {
                            joueur.getJetons().add(jetonvictoire);
                            voisinGauche(pseudo).getJetons().add(jetonDefaite);
                            this.bataille.setNomDuVainqueur(voisinDroite(pseudo).getPseudo());
                            perdus.add(voisinGauche(pseudo).getPseudo());
                            this.bataille.setNomDuVaincu(perdus);
                            joueur.getListePointsBataille().add(jetonvictoire.getPoints());
                            voisinGauche(pseudo).getListePointsBataille().add(jetonDefaite.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(pseudo, jetonvictoire.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(voisinGauche(pseudo).getPseudo(), jetonDefaite.getPoints());
                            BaseMongo.getBase().ajoutJetonJoueur(joueur.getPseudo(), jetonvictoire);
                            BaseMongo.getBase().ajoutJetonJoueur(voisinGauche(pseudo).getPseudo(), jetonDefaite);
                            BaseMongo.getBase().ajoutBataille(this.bataille);
                            vainqueur = voisinDroite(pseudo).getPseudo();
                        } else if (joueur.getNombreBoucliers() < voisinGauche(pseudo).getNombreBoucliers()) {
                            joueur.getJetons().add(jetonDefaite);
                            voisinGauche(pseudo).getJetons().add(jetonvictoire);
                            perdus.add(joueur.getPseudo());
                            this.bataille.setNomDuVaincu(perdus);
                            joueur.getListePointsBataille().add(jetonDefaite.getPoints());
                            voisinGauche(pseudo).getListePointsBataille().add(jetonvictoire.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(pseudo, jetonDefaite.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(voisinGauche(pseudo).getPseudo(), jetonvictoire.getPoints());
                            BaseMongo.getBase().ajoutJetonJoueur(joueur.getPseudo(), jetonDefaite);
                            BaseMongo.getBase().ajoutJetonJoueur(voisinGauche(pseudo).getPseudo(), jetonvictoire);

                            BaseMongo.getBase().ajoutBataille(this.bataille);
                        } else if (joueur.getNombreBoucliers() == voisinGauche(pseudo).getNombreBoucliers()) {
                            joueur.getListePointsBataille().add(0);
                            voisinGauche(pseudo).getListePointsBataille().add(0);
                            BaseMongo.getBase().ajoutPointsBataille(pseudo, 0);
                            BaseMongo.getBase().ajoutPointsBataille(voisinGauche(pseudo).getPseudo(), 0);
                            BaseMongo.getBase().ajoutBataille(this.bataille);
                        }
                    }
                    if (joueur.getNombreBoucliers() == voisinGauche(pseudo).getNombreBoucliers()) {
                        joueur.getListePointsBataille().add(0);
                        voisinGauche(pseudo).getListePointsBataille().add(0);
                        BaseMongo.getBase().ajoutPointsBataille(pseudo, 0);
                        BaseMongo.getBase().ajoutPointsBataille(voisinGauche(pseudo).getPseudo(), 0);
                        if (joueur.getNombreBoucliers() > voisinDroite(pseudo).getNombreBoucliers()) {
                            joueur.getJetons().add(jetonvictoire);
                            voisinDroite(pseudo).getJetons().add(jetonDefaite);
                            perdus.add(voisinDroite(pseudo).getPseudo());
                            this.bataille.setNomDuVainqueur(voisinGauche(pseudo).getPseudo());
                            this.bataille.setNomDuVaincu(perdus);
                            joueur.getListePointsBataille().add(jetonvictoire.getPoints());
                            voisinDroite(pseudo).getListePointsBataille().add(jetonDefaite.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(pseudo, jetonvictoire.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(voisinDroite(pseudo).getPseudo(), jetonDefaite.getPoints());
                            BaseMongo.getBase().ajoutJetonJoueur(joueur.getPseudo(), jetonvictoire);
                            BaseMongo.getBase().ajoutJetonJoueur(voisinDroite(pseudo).getPseudo(), jetonDefaite);
                            BaseMongo.getBase().ajoutBataille(this.bataille);
                            vainqueur = voisinGauche(pseudo).getPseudo();
                        } else if (joueur.getNombreBoucliers() < voisinDroite(pseudo).getNombreBoucliers()) {
                            joueur.getJetons().add(jetonDefaite);
                            voisinDroite(pseudo).getJetons().add(jetonvictoire);
                            perdus.add(joueur.getPseudo());
                            this.bataille.setNomDuVaincu(perdus);
                            joueur.getListePointsBataille().add(jetonDefaite.getPoints());
                            voisinGauche(pseudo).getListePointsBataille().add(jetonvictoire.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(pseudo, jetonDefaite.getPoints());
                            BaseMongo.getBase().ajoutPointsBataille(voisinDroite(pseudo).getPseudo(), jetonvictoire.getPoints());
                            BaseMongo.getBase().ajoutJetonJoueur(joueur.getPseudo(), jetonDefaite);
                            BaseMongo.getBase().ajoutJetonJoueur(voisinDroite(pseudo).getPseudo(), jetonvictoire);

                            BaseMongo.getBase().ajoutBataille(this.bataille);
                        } else if (joueur.getNombreBoucliers() == voisinDroite(pseudo).getNombreBoucliers()) {
                            joueur.getListePointsBataille().add(0);
                            voisinDroite(pseudo).getListePointsBataille().add(0);
                            BaseMongo.getBase().ajoutPointsBataille(pseudo, 0);
                            BaseMongo.getBase().ajoutPointsBataille(voisinDroite(pseudo).getPseudo(), 0);
                            BaseMongo.getBase().ajoutBataille(this.bataille);
                        }
                    }
                }
            }
        }
    }

    public Bataille getBataille() {
        return bataille;
    }

    public void setBataille(Bataille bataille) {
        this.bataille = bataille;
    }

    public Map<String, Joueur> getJoueurBataille() {
        return joueurBataille;
    }

    public void setJoueurBataille(Map<String, Joueur> joueurBataille) {
        this.joueurBataille = joueurBataille;
    }

    public Map<String, Integer> getNombreJoueur() {
        return nombreJoueur;
    }

    public void setNombreJoueur(Map<String, Integer> nombreJoueur) {
        this.nombreJoueur = nombreJoueur;
    }

    public GestionTour getGestionTour() {
        return gestionTour;
    }

    public void setGestionTour(GestionTour gestionTour) {
        this.gestionTour = gestionTour;
    }

    public List<String> getPerdus() {
        return perdus;
    }

    public void setPerdus(List<String> perdus) {
        this.perdus = perdus;
    }

    public List<Integer> getNumeros() {
        return numeros;
    }

    public void setNumeros(List<Integer> numeros) {
        this.numeros = numeros;
    }
}
