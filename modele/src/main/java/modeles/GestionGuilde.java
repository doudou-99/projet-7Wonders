package modeles;

import modeles.dao.BaseMongo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GestionGuilde {
    private Map<String,Joueur> joueurGuilde;
    private Map<String, Integer> nombreJoueur;
    private GestionTour tour;


    public GestionGuilde(){
        this.joueurGuilde=new HashMap<>();
        this.nombreJoueur=new HashMap<>();
        for (int i=0;i<BaseMongo.getBase().getJoueurList().size();i++) {
            this.joueurGuilde.put(String.valueOf(i),BaseMongo.getBase().getJoueurList().get(i));
            this.nombreJoueur.put(BaseMongo.getBase().getJoueurList().get(i).getPseudo(),i);
        }
    }

    public Joueur voisinDroite(String joueur) {
        Joueur joueur1 = BaseMongo.getBase().getJoueur(joueur);
        int voisin = 0;
        if (joueurGuilde.containsValue(joueur1) && nombreJoueur.containsKey(joueur)) {
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
        return joueurGuilde.get(String.valueOf(voisin));
    }

    public Joueur voisinGauche(String joueur) {
        Joueur joueur1 = BaseMongo.getBase().getJoueur(joueur);
        int voisin = 0;
        if (joueurGuilde.containsValue(joueur1) && nombreJoueur.containsKey(joueur)) {
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
        return joueurGuilde.get(String.valueOf(voisin));
    }


    public void beneficeGuilde(String joueur,String nomCarte){
        Joueur joueur1 = BaseMongo.getBase().getJoueur(joueur);
        Carte c = BaseMongo.getBase().getCartesNom(nomCarte);
        if (joueurGuilde.containsValue(joueur1) && nombreJoueur.containsKey(joueur)) {
            if (c.getType().equals("Guilde") && joueur1.getCite().getCartes().contains(c)) {
                    for (Effet e : c.getEffet()) {
                        if (e.getCapacite().equals("point de victoire")) {
                            if (e.getChoix().contains("cite gauche") || e.getChoix().contains("cite droite")
                                    || e.getChoix().contains("cite")) {
                                if (e.getCapaciteSup().equals(c.getCouleur()) && !c.getCouleur().equals("violet")) {
                                    int nb = 0;
                                    for (Carte carte : voisinDroite(joueur).getCite().getCartes()) {
                                        if (carte.getCouleur().equals(c.getCouleur())) {
                                            nb += e.getNombre();
                                        }
                                    }
                                    for (Carte carte : voisinGauche(joueur).getCite().getCartes()) {
                                        if (carte.getCouleur().equals(c.getCouleur())) {
                                            nb += e.getNombre();
                                        }
                                    }
                                    joueur1.setPointsVictoireGuilde(joueur1.getPointsVictoireGuilde()+ nb);
                                    BaseMongo.getBase().ajoutPointsGuilde(joueur1.getPseudo(), joueur1.getPointsVictoireGuilde());

                                }

                                if (e.getCapaciteSup().equals("marron,gris,violet")) {
                                    int nb = 0;
                                    for (Carte carte : joueur1.getCite().getCartes()) {
                                        if (carte.getCouleur().equals("marron")) {
                                            nb += e.getNombre();
                                        }
                                        if (carte.getCouleur().equals("violet")) {
                                            nb += e.getNombre();
                                        }
                                        if (carte.getCouleur().equals("gris")) {
                                            nb += e.getNombre();
                                        }

                                    }
                                    joueur1.setPointsVictoireGuilde(joueur1.getPointsVictoireGuilde()+ nb);
                                    BaseMongo.getBase().ajoutPointsGuilde(joueur1.getPseudo(), joueur1.getPointsVictoireGuilde());
                                }
                                if (e.getCapaciteSup().equals("defaite")) {
                                    int nombre = 0;
                                    if (!voisinDroite(joueur).getJetons().isEmpty()) {
                                        for (Jeton jeton : voisinDroite(joueur).getJetons()) {
                                            if (jeton.getType().equals("defaite")) {
                                                nombre += e.getNombre();
                                            }
                                        }
                                    } else if (!voisinGauche(joueur).getJetons().isEmpty()) {
                                        for (Jeton jeton : voisinGauche(joueur).getJetons()) {
                                            if (jeton.getType().equals("defaite")) {
                                                nombre += e.getNombre();
                                            }
                                        }
                                    }
                                    joueur1.setPointsVictoireGuilde(joueur1.getPointsVictoireGuilde()+ nombre);
                                    BaseMongo.getBase().ajoutPointsGuilde(joueur1.getPseudo(), joueur1.getPointsVictoireGuilde());
                                }
                                if (e.getCapaciteSup().equals("etage")) {
                                    int nombrePoint = 0;

                                    if (joueur1.getCartesEtages().values().size() == 1) {
                                        nombrePoint += joueur1.getCartesEtages().values().size();
                                    } else if (joueur1.getCartesEtages().values().size() == 2) {
                                        nombrePoint += joueur1.getCartesEtages().values().size();
                                    } else if (joueur1.getCartesEtages().values().size() == 3) {
                                        nombrePoint += joueur1.getCartesEtages().values().size();
                                    }
                                    if (voisinDroite(joueur).getCartesEtages().values().size() == 1) {
                                        nombrePoint += voisinDroite(joueur).getCartesEtages().values().size();
                                    } else if (voisinDroite(joueur).getCartesEtages().values().size() == 2) {
                                        nombrePoint += voisinDroite(joueur).getCartesEtages().values().size();
                                    } else if (voisinDroite(joueur).getCartesEtages().values().size() == 3) {
                                        nombrePoint += voisinDroite(joueur).getCartesEtages().values().size();
                                    }
                                    if (voisinGauche(joueur).getCartesEtages().values().size() == 1) {
                                        nombrePoint += voisinGauche(joueur).getCartesEtages().values().size();
                                    } else if (voisinGauche(joueur).getCartesEtages().values().size() == 2) {
                                        nombrePoint += voisinGauche(joueur).getCartesEtages().values().size();
                                    } else if (voisinGauche(joueur).getCartesEtages().values().size() == 3) {
                                        nombrePoint += voisinGauche(joueur).getCartesEtages().values().size();
                                    }

                                    joueur1.setPointsVictoireGuilde(joueur1.getPointsVictoireGuilde()+ nombrePoint);
                                    BaseMongo.getBase().ajoutPointsGuilde(joueur1.getPseudo(), joueur1.getPointsVictoireGuilde());
                                }
                            }
                        } else {
                            if (e.getCapacite().equals("compas/roue/tablette")) {
                                if (e.getChoix().contains("compas")) {
                                    joueur1.getGestionCapacite().ajoutSymbolesScientifiques("compas", 1);
                                }
                                if (e.getChoix().contains("roue")) {
                                    joueur1.getGestionCapacite().ajoutSymbolesScientifiques("roue", 1);
                                }
                                if (e.getChoix().contains("tablette")) {
                                    joueur1.getGestionCapacite().ajoutSymbolesScientifiques("tablette", 1);
                                }
                            }
                        }
                    }
                }
        }
    }


    public Map<String, Joueur> getJoueurGuilde() {
        return joueurGuilde;
    }

    public void setJoueurGuilde(Map<String, Joueur> joueurGuilde) {
        this.joueurGuilde = joueurGuilde;
    }

    public Map<String, Integer> getNombreJoueur() {
        return nombreJoueur;
    }

    public void setNombreJoueur(Map<String, Integer> nombreJoueur) {
        this.nombreJoueur = nombreJoueur;
    }

    public GestionTour getTour() {
        return tour;
    }

    public void setTour(GestionTour tour) {
        this.tour = tour;
    }
}
