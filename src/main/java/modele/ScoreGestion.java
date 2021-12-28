package modele;

import modele.dao.BaseMongo;
import modele.exceptions.ChoixDejaFaitException;
import modele.exceptions.ConstructionMerveilleImpossible;

import java.util.*;

public class ScoreGestion {
    private Tour tour;
    private Map<String,Joueur> joueurs;
    private Map<String,Integer> score;
    private Map<String,String> choix;

    public ScoreGestion(Joueur joueur, List<Joueur> participants,Tour tour){
        this.joueurs=new HashMap<>();
        this.choix=new HashMap<>();
        this.score=new HashMap<>();
        this.joueurs.put(joueur.getPseudo(),joueur);
        this.score.put(joueur.getPseudo(),joueur.getNombreDePoints());
        for (int i=1;i<participants.size();i++) {
            this.joueurs.put(participants.get(i).getPseudo(),participants.get(i));
            this.score.put(participants.get(i).getPseudo(),participants.get(i).getNombreDePoints());
        }
        this.tour=tour;

    }

    public void setChoixJoueur(String joueur, String choix,String nomCarte) throws ChoixDejaFaitException, ConstructionMerveilleImpossible {
        if (!Objects.isNull(this.choix.get(joueurs.get(joueur).getPseudo()))) {
            throw new ChoixDejaFaitException();
        }
        switch (choix){
            case "Defausser carte":
                Joueur joueur1 = joueurs.get(joueur);
                PaquetCarte paquetCarte = new PaquetCarte(tour.getAge());
                Collection<Carte> cartes = joueur1.cartesEnPossession(paquetCarte);
                for (Carte c: cartes) {
                    if (c.getNom().equals(nomCarte)){
                        joueur1.defausser(c);
                    }
                }

                this.choix.put(joueur1.getPseudo(),nomCarte);
                break;
            case "Construire batiment":
                Joueur joueur2 = joueurs.get(joueur);
                PaquetCarte paquetCart = new PaquetCarte(tour.getAge());
                Collection<Carte> carte = joueur2.cartesEnPossession(paquetCart);
                for (Carte c: carte) {
                    if (c.getNom().equals(nomCarte)){
                       joueur2.ajoutCite(c);
                    }
                }
                this.choix.put(joueur2.getPseudo(),nomCarte);
                break;
            case "Construire merveille":
                Joueur joueur3 = joueurs.get(joueur);
                PaquetCarte paquetCar = new PaquetCarte(tour.getAge());
                Collection<Carte> car = joueur3.cartesEnPossession(paquetCar);
                for (Carte c: car) {
                    if (c.getNom().equals(nomCarte)){
                        Plateau plateau = BaseMongo.getBase().getPlateauNom(joueur3.getMerveilles());
                        for (Etage etage: plateau.getEtages()) {
                           if (etage.getId().equals("etage:1")){
                               joueur3.constructionMerveille(etage, c);
                               for (Effet e:etage.getEffet()) {
                                   if (e.getCapacite().equals("point de victoire")) {
                                       this.score.put(joueur3.getPseudo(), e.getNombre());
                                   }
                               }

                           }else if (etage.getId().equals("etage:2")){
                               joueur3.constructionMerveille(etage, c);
                               for (Effet e:etage.getEffet()) {
                                   if (!e.getCapacite().equals("point de victoire")) {
                                       joueur3.getGestionCapacite().augmenterRessource(e.getCapacite(),e.getNombre());
                                   }
                               }
                           }else if(etage.getId().equals("etage:3")){
                                joueur3.constructionMerveille(etage, c);
                               for (Effet e:etage.getEffet()) {
                                   if (e.getCapacite().equals("point de victoire")) {
                                       this.score.put(joueur3.getPseudo(), e.getNombre());
                                   }
                               }
                            }
                        }
                    }
                }
                this.choix.put(joueur3.getPseudo(),nomCarte);
                break;
        }
    }




}
