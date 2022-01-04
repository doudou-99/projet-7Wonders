package modeles;

import modeles.dao.BaseMongo;
import modeles.exceptions.*;
import modeles.interfaces.Score;

import java.util.*;

public class PartieGestion {
    /*private final static int ZERO=0;
    private final static int UN=1;
    private final static int DEUX=2;
    private final static int TROIS=3;*/
    private GestionTour gestiontour;
    private Map<Integer,Joueur> joueurs;
    private Map<Integer,Integer> score;
    private Map<String,String> choix;
    private Map<String,Integer> citeJoueurs;
    private GestionCommerce gestionCommerce;
    private GestionGuilde gestionGuilde;
    private GestionBataille gestionBataille;

    public PartieGestion(Joueur joueur, Joueur joueur1, Joueur joueur2, Joueur joueur3, GestionTour gestiontour){
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
        this.gestiontour=gestiontour;
    }

    public void defausseCarte(String pseudo,String carte){
        Joueur joueur =BaseMongo.getBase().getJoueur(pseudo);
        PaquetCarte paquetCarte = new PaquetCarte(this.gestiontour.getTour().getAge());
        Collection<Carte> cartes = joueur.cartesEnPossession(paquetCarte);
        for (Carte c: cartes) {
            if (c.getNom().equals(carte)){
                joueur.defausser(c);
            }
        }
        this.choix.put(joueur.getPseudo(),carte);

    }

    public void choixJoueur(String joueur, String choixJeu,String nomCarte,String choixCarte) throws ChoixDejaFaitException, ConstructionMerveilleImpossible, RessourceVoisinInsuffisantException, RessourceInexistanteException, CartePasConstruiteException, RessourceInsuffisanteException, PieceInsuffisanteException, ConstructionImpossibleException {
        if (!Objects.isNull(this.choix.get(joueur))) {
            throw new ChoixDejaFaitException();
        }
        switch (choixJeu){
            case "Defausser carte":
                this.defausseCarte(joueur,nomCarte);
                break;
            case "Construire batiment":
                for (Joueur j: joueurs.values()){
                    if(j.getPseudo().equals(joueur)){
                        j.jouerCarte(j.getPseudo(),nomCarte,choixCarte);
                        this.choix.put(j.getPseudo(),nomCarte);
                    }
                }

                break;
            case "Construire merveille":

                PaquetCarte paquetCar = new PaquetCarte(this.gestiontour.getTour().getAge());
                for(Joueur j: joueurs.values()) {
                    if (j.getPseudo().equals(joueur)) {
                        Collection<Carte> car = j.cartesEnPossession(paquetCar);
                        for (Carte c : car) {
                            if (c.getNom().equals(nomCarte)) {
                                Plateau plateau = BaseMongo.getBase().getPlateauNom(j.getMerveilles());
                                for (Etage etage : plateau.getEtages()) {
                                    if (etage.getId().equals("etage:1")) {
                                        j.constructionMerveille(etage, c);
                                        for (Effet e : etage.getEffet()) {
                                            if (e.getCapacite().equals("point de victoire")) {
                                                if (citeJoueurs.get(j.getPseudo()) == 1) {
                                                    this.score.put(1, e.getNombre());
                                                    j.setNombreDePoints(j.getNombreDePoints()+e.getNombre());
                                                }
                                                if (citeJoueurs.get(j.getPseudo()) == 0) {
                                                    this.score.put(0, e.getNombre());
                                                    j.setNombreDePoints(j.getNombreDePoints()+e.getNombre());
                                                }
                                                if (citeJoueurs.get(j.getPseudo()) == 2) {
                                                    this.score.put(2, e.getNombre());
                                                    j.setNombreDePoints(j.getNombreDePoints()+e.getNombre());
                                                }
                                                if (citeJoueurs.get(j.getPseudo()) == 3) {
                                                    this.score.put(3, e.getNombre());
                                                    j.setNombreDePoints(j.getNombreDePoints()+e.getNombre());
                                                }
                                            }
                                        }

                                    } else if (etage.getId().equals("etage:2")) {
                                        j.constructionMerveille(etage, c);
                                        for (Effet e : etage.getEffet()) {
                                            if (!e.getCapacite().equals("point de victoire")) {
                                                j.getGestionCapacite().augmenterRessource(e.getCapacite(), e.getNombre());
                                            }else{
                                                this.score.put(3,e.getNombre());
                                                j.setNombreDePoints(j.getNombreDePoints()+e.getNombre());
                                            }
                                        }
                                    } else if (etage.getId().equals("etage:3")) {
                                        j.constructionMerveille(etage, c);
                                        for (Effet e : etage.getEffet()) {
                                            if (e.getCapacite().equals("point de victoire")) {
                                                if (citeJoueurs.get(j.getPseudo()) == 1) {
                                                    this.score.put(1, e.getNombre());
                                                    j.setNombreDePoints(j.getNombreDePoints()+e.getNombre());
                                                }
                                                if (citeJoueurs.get(j.getPseudo()) == 0) {
                                                    this.score.put(0, e.getNombre());
                                                    j.setNombreDePoints(j.getNombreDePoints()+e.getNombre());
                                                }
                                                if (citeJoueurs.get(j.getPseudo()) == 2) {
                                                    this.score.put(2, e.getNombre());
                                                    j.setNombreDePoints(j.getNombreDePoints()+e.getNombre());
                                                }
                                                if (citeJoueurs.get(j.getPseudo()) == 3) {
                                                    this.score.put(3, e.getNombre());
                                                    j.setNombreDePoints(j.getNombreDePoints()+e.getNombre());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        this.choix.put(j.getPseudo(), nomCarte);
                    }
                }
                break;
        }
    }


    public boolean partieTerminee() {
        return (this.gestiontour.getTour().getAge() == BaseMongo.getBase().getAges().get(2))
                && this.gestiontour.getTour().getNombreTourEnCours() >= this.gestiontour.getTour().getAge().getNombreTour();
    }

    public int scoreTotal(Joueur joueur){
        int score=0;
        if (this.joueurs.containsValue(joueur)){
            score+=(joueur.getNombrePieces()/3);
            for (int s: joueur.getListePointsBataille()){
                score+=s;
            }
            for(BatimentScientifique b: joueur.getBatimentScientifiques()){
                if (b.getTypeSymbole().equals("compas") && joueur.getGestionCapacite().getNombreCompas()>1){
                    score+=Math.pow(joueur.getGestionCapacite().getNombreCompas(),2);
                }else if(b.getTypeSymbole().equals("roue")){
                    score+=Math.pow(joueur.getGestionCapacite().getNombreRoue(),2);
                }else if (b.getTypeSymbole().equals("tablette")){
                    score+=Math.pow(joueur.getGestionCapacite().getNombreTablette(),2);
                }
            }
            if (joueur.getSymboleScientifique("compas").size()>=1 && joueur.getSymboleScientifique("roue").size()>=1
            && joueur.getSymboleScientifique("tablette").size()>=1){
                score+=joueur.symbolesScientifiquesDifferents();
            }
            score+=joueur.getNombreDePoints();
        }
        return score;
    }

    public boolean tourJoueursFini(){
        return choix.get(joueurs.get(0).getPseudo()).length() == 1 && choix.get(joueurs.get(1).getPseudo()).length() == 1
                && choix.get(joueurs.get(2).getPseudo()).length() == 1 && choix.get(joueurs.get(3).getPseudo()).length() == 1
                ;
    }

    public String getVainqueur() throws PartieNonTermineeException {
        if (partieTerminee()) {
            for(Joueur j: this.joueurs.values()){
                if (citeJoueurs.get(j.getPseudo())==1) {
                    if(this.scoreTotal(joueurs.get(1))>this.scoreTotal(joueurs.get(0)) && this.scoreTotal(joueurs.get(1))>this.scoreTotal(joueurs.get(2))
                            && this.scoreTotal(joueurs.get(1))>this.scoreTotal(joueurs.get(3))){
                        return j.getPseudo();
                    }
                }if (citeJoueurs.get(j.getPseudo())==0) {
                    if(this.scoreTotal(joueurs.get(0))>this.scoreTotal(joueurs.get(2)) && this.scoreTotal(joueurs.get(0))>this.scoreTotal(joueurs.get(1))
                            && this.scoreTotal(joueurs.get(0))>this.scoreTotal(joueurs.get(3))){
                        return j.getPseudo();
                    }
                }if (citeJoueurs.get(j.getPseudo())==2) {
                    if(this.scoreTotal(joueurs.get(2))>this.scoreTotal(joueurs.get(1)) && this.scoreTotal(joueurs.get(2))>this.scoreTotal(joueurs.get(0))
                            && this.scoreTotal(joueurs.get(2))>this.scoreTotal(joueurs.get(3))){
                        return j.getPseudo();
                    }
                }if (citeJoueurs.get(j.getPseudo())==3) {
                    if(this.scoreTotal(joueurs.get(3))>this.scoreTotal(joueurs.get(2)) && this.scoreTotal(joueurs.get(3))>this.scoreTotal(joueurs.get(0))
                            && this.scoreTotal(joueurs.get(3))>this.scoreTotal(joueurs.get(1))){
                        return j.getPseudo();
                    }
                }
            }
        }
            throw new PartieNonTermineeException();

    }


    public Score maj(String joueur,String choix,String nomCarte, String choixCarte) throws ChoixIncompletsException, ConstructionMerveilleImpossible, ChoixDejaFaitException, CartePasConstruiteException, RessourceInexistanteException, RessourceInsuffisanteException, PieceInsuffisanteException, RessourceVoisinInsuffisantException, ConstructionImpossibleException {
        if (!tourJoueursFini()) {
            throw new ChoixIncompletsException();
        }
        choixJoueur(joueur,choix,nomCarte,choixCarte);
        Score score = new ScoreImpl(this.gestiontour,joueurs.get(0),this.joueurs.get(1),this.joueurs.get(2),this.joueurs.get(3),
                this.choix.get(joueurs.get(0).getPseudo()),this.choix.get(this.joueurs.get(1).getPseudo()),
                this.choix.get(this.joueurs.get(2).getPseudo()), this.choix.get(this.joueurs.get(3).getPseudo()));
        this.choix.clear();
        this.gestiontour.passageAgeSuivant();
        return score;

    }


    public Map<String, String> getChoix() {
        return choix;
    }
}
