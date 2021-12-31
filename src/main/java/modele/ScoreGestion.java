package modele;

import modele.dao.BaseMongo;
import modele.exceptions.ChoixDejaFaitException;
import modele.exceptions.ChoixIncompletsException;
import modele.exceptions.ConstructionMerveilleImpossible;
import modele.exceptions.PartieNonTermineeException;
import modele.interfaces.Score;

import java.util.*;

public class ScoreGestion {
    /*private final static int ZERO=0;
    private final static int UN=1;
    private final static int DEUX=2;
    private final static int TROIS=3;*/
    private Tour tour;
    private Map<Integer,Joueur> joueurs;
    private Map<Integer,Integer> score;
    private Map<String,String> choix;
    private Map<String,Integer> citeJoueurs;

    public ScoreGestion(Joueur joueur, Joueur joueur1, Joueur joueur2,Joueur joueur3,Tour tour){
        this.joueurs=new HashMap<>();
        this.choix=new HashMap<>();
        this.score=new HashMap<>();
        this.citeJoueurs=new HashMap<>();
        this.joueurs.put(0,joueur);
        this.joueurs.put(1,joueur1);
        this.joueurs.put(2,joueur2);
        this.joueurs.put(3,joueur3);
        this.score.put(0,joueur.getNombreDePoints());
        this.score.put(1,joueur1.getNombreDePoints());
        this.score.put(2, joueur2.getNombreDePoints());
        this.score.put(3,joueur3.getNombreDePoints());
        this.citeJoueurs.put(joueur.getPseudo(),0);
        this.citeJoueurs.put(joueur1.getPseudo(),1);
        this.citeJoueurs.put(joueur2.getPseudo(),2);
        this.citeJoueurs.put(joueur3.getPseudo(),3);
        this.tour=tour;

    }

    public void setChoixJoueur(String joueur, String choix,String nomCarte) throws ChoixDejaFaitException, ConstructionMerveilleImpossible {
        if (!Objects.isNull(this.choix.get(joueur))) {
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
                                       if (citeJoueurs.get(joueur3.getPseudo())==1) {
                                           this.score.put(1, e.getNombre());
                                       }if (citeJoueurs.get(joueur3.getPseudo())==0) {
                                           this.score.put(0, e.getNombre());
                                       }if (citeJoueurs.get(joueur3.getPseudo())==2) {
                                           this.score.put(2, e.getNombre());
                                       }if (citeJoueurs.get(joueur3.getPseudo())==3) {
                                           this.score.put(3, e.getNombre());
                                       }
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
                                       if (citeJoueurs.get(joueur3.getPseudo())==1) {
                                           this.score.put(1, e.getNombre());
                                       }if (citeJoueurs.get(joueur3.getPseudo())==0) {
                                           this.score.put(0, e.getNombre());
                                       }if (citeJoueurs.get(joueur3.getPseudo())==2) {
                                           this.score.put(2, e.getNombre());
                                       }if (citeJoueurs.get(joueur3.getPseudo())==3) {
                                           this.score.put(3, e.getNombre());
                                       }
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


    public boolean partieTerminee() {
        return (tour.getAge() == BaseMongo.getBase().getAges().get(2)) && tour.getNombreTourEnCours() >= tour.getAge().getNombreTour();
    }

    public int scoreTotal(Joueur joueur){
        int score=0;
        if (this.joueurs.containsValue(joueur)){
            score+=(joueur.getNombrePiecesOr()/3);
            for(Jeton j: joueur.getJetons()){
                if (BaseMongo.getBase().getJetons().contains(j)){
                    score+=j.getPoints();
                }
            }
            for(BatimentCivil batimentCivil: joueur.getBatimentCivilList()){
                score+=batimentCivil.getNombre();
            }
            if (joueur.merveilleFinie()){
                score+=10;
            }else if(joueur.verifieConstructionEtage()){
                score+=3;
            }
            score+=joueur.getNombreDePoints();
        }
        return score;
    }

    public String getVainqueur() throws PartieNonTermineeException {
        if (partieTerminee()) {
            for(Joueur j: this.joueurs.values()){
                if (citeJoueurs.get(j.getPseudo())==1) {
                    if(this.score.get(1)>this.score.get(2) && this.score.get(1)>this.score.get(0)
                            && this.score.get(1)>this.score.get(3)){
                        return j.getPseudo();
                    }
                }if (citeJoueurs.get(j.getPseudo())==0) {
                    if(this.score.get(0)>this.score.get(2) && this.score.get(0)>this.score.get(1)
                            && this.score.get(0)>this.score.get(3)){
                        return j.getPseudo();
                    }
                }if (citeJoueurs.get(j.getPseudo())==2) {
                    if(this.score.get(2)>this.score.get(1) && this.score.get(2)>this.score.get(0)
                            && this.score.get(2)>this.score.get(3)){
                        return j.getPseudo();
                    }
                }if (citeJoueurs.get(j.getPseudo())==3) {
                    if(this.score.get(3)>this.score.get(2) && this.score.get(3)>this.score.get(0)
                            && this.score.get(3)>this.score.get(1)){
                        return j.getPseudo();
                    }
                }
            }
        }
            throw new PartieNonTermineeException();

    }

    public Score maj(String joueur,String choix,String nomCarte) throws ChoixIncompletsException, ConstructionMerveilleImpossible, ChoixDejaFaitException {
        if (this.choix.values().size()<2) {
            throw new ChoixIncompletsException();
        }
        setChoixJoueur(joueur,choix,nomCarte);
        this.tour.setNombreTourEnCours(tour.getNombreTourEnCours()+1);
        Score score = new ScoreImpl(this.tour,joueurs.get(0),this.joueurs.get(1),this.joueurs.get(2),this.joueurs.get(3),
                this.choix.get(joueurs.get(0).getPseudo()),this.choix.get(this.joueurs.get(1).getPseudo()),
                this.choix.get(this.joueurs.get(2).getPseudo()), this.choix.get(this.joueurs.get(3).getPseudo()));
        this.choix.clear();
        return score;

    }


    /*public Carte construireGuilde(String pseudo,Carte c) throws RessourceInsuffisanteException {
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);

        //Bson filter = Filters.eq("pseudo", jo.getPseudo());

        return null;
    }

    public Carte construireBatimentCommercial(String pseudo,Carte c) throws RessourceInsuffisanteException{
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);

        //Bson filter = Filters.eq("pseudo", .getPseudo());

        return null;
    }*/

    /*public Carte jouerCarte(String carte) throws RessourceInsuffisanteException {
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo", this.getPseudo());
        for (Carte c: this.listeCartes){
            if (c.getNom().equals(carte)){
                switch(c.getType()){
                    case "Bâtiments civils":
                        this.construireBatimentCivils(c);
                        break;
                    case "Bâtiments scientifiques":
                        this.construireBatimentsScientifiques(c);
                        break;
                    case "Matières premeières":
                        this.construireMatierePremiere(c);
                        break;
                    case "Produits manufacturés":
                        .construireProduitManufacturés(c);
                        break;

                    case "Bâtiments militaires":
                        this.construireBatimentMilitaires(c);

                    case "Guilde":


                }
            }
        }
    }*/

    public Map<String, String> getChoix() {
        return choix;
    }
}
