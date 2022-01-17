package modeles;

import modeles.dao.BaseMongo;
import modeles.exceptions.*;
import modeles.interfaces.Score;

import java.io.Serializable;
import java.util.*;

public class PartieGestion implements Serializable {
    private static final long serialVersionUID=1L;
    private GestionTour gestiontour;
    private Map<String,Joueur> joueurs;
    private Map<String,String> choix;
    private Map<String,Integer> score;
    private Map<String,String> citeJoueurs;
    private Map<String,Integer> nombreJoueur;
    private GestionCommerce gestionCommerce;
    private GestionGuilde gestionGuilde;
    private GestionBataille gestionBataille;
    private MainJoueur mainJoueur;
    private PaquetCarte paquetCarte;

    public PartieGestion(){}

    public PartieGestion(List<Joueur> joueurList){
        this.joueurs=new HashMap<>();
        this.choix=new HashMap<>();
        this.score=new HashMap<>();
        this.citeJoueurs=new HashMap<>();
        this.nombreJoueur=new HashMap<>();
        for (int i=0;i<joueurList.size();i++){
            this.joueurs.put(String.valueOf(i),joueurList.get(i));
            this.score.put(String.valueOf(i),joueurList.get(i).getNombreDePoints());
            this.citeJoueurs.put(joueurList.get(i).getPseudo(),String.valueOf(i));
            this.nombreJoueur.put(joueurList.get(i).getPseudo(), i);
            //this.cartes.put(joueurList.get(i).getP)

        }
        this.gestiontour=new GestionTour(BaseMongo.getBase().getAges().get(0));
        Tour tour = new Tour(BaseMongo.getBase().getAges().get(0));
        this.gestiontour.setTour(tour);
        this.paquetCarte=new PaquetCarte(this.gestiontour.getTour().getAge());
        this.mainJoueur=new MainJoueur(this.paquetCarte);
        this.mainJoueur.setJoueurs(joueurs);
        this.mainJoueur.setChoixJoueur(choix);
        this.mainJoueur.setNombreJoueur(this.nombreJoueur);
    }

    public void choisirPlateau(Joueur joueur,String nomPlateau){
        if (this.joueurs.containsValue(joueur)){
            joueur.choisirPlateau(nomPlateau);
        }
    }




    public void choixJoueur( String joueur,String choix, String nomCarte,String choixCarte) throws ChoixDejaFaitException, CiteContientCarteException, RessourceInexistanteException, CartePasConstruiteException, RessourceVoisinInsuffisantException, RessourceInsuffisanteException, ConstructionImpossibleException, PieceInsuffisanteException, ConstructionMerveilleImpossible {
        switch (choix){
            case "Defausse carte":
                choixDefausse(joueur, nomCarte);
                break;
            case "Construire bâtiment":
                choixJouerCarte(joueur,nomCarte,choixCarte);
                break;
            case "Construire merveille":
                choixConstructionMerveille(joueur, nomCarte);
                break;
        }
    }

    public void defausseCarte(String pseudo,String carte){
        Joueur joueur =BaseMongo.getBase().getJoueur(pseudo);
        Collection<Carte> cartes = joueur.cartesEnPossession(paquetCarte);
        for (Carte c: cartes) {
            if (c.getNom().equals(carte)){
                joueur.defausser(c);
            }
        }
        this.choix.put(joueur.getPseudo(),carte);

    }

    public void choixDefausse(String joueur, String nomCarte) throws ChoixDejaFaitException {
        if (!Objects.isNull(this.choix.get(joueur))) {
            throw new ChoixDejaFaitException();
        }
        this.defausseCarte(joueur,nomCarte);
    }

    public void choixJouerCarte(String joueur, String nomCarte,String choixCarte) throws ChoixDejaFaitException, CiteContientCarteException, RessourceInsuffisanteException, RessourceInexistanteException, CartePasConstruiteException, RessourceVoisinInsuffisantException, ConstructionImpossibleException, PieceInsuffisanteException {
        if (!Objects.isNull(this.choix.get(joueur))) {
            throw new ChoixDejaFaitException();
        }
        for (Joueur j: joueurs.values()){
            if(j.getPseudo().equals(joueur)){
                j.jouerCarte(nomCarte);
                this.choix.put(j.getPseudo(),nomCarte);
                for(Effet e: BaseMongo.getBase().getCartesNom(nomCarte).getEffet()){
                    if (e.getCapacite().contains("/")){
                        for (String cap: e.getChoix()){
                            if (choixCarte.equals(cap)){
                                j.getGestionCapacite().augmenterRessource(choixCarte,1);
                            }
                        }
                    }
                }
            }
        }
    }

    public void choixConstructionMerveille(String joueur, String nomCarte) throws ConstructionMerveilleImpossible, ChoixDejaFaitException {
        if (!Objects.isNull(this.choix.get(joueur))) {
            throw new ChoixDejaFaitException();
        }
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
                                        if (citeJoueurs.get(j.getPseudo()).equals("1")) {
                                            this.score.put("1", e.getNombre());
                                            j.setPointMerveille(j.getPointMerveille() + e.getNombre());
                                        }
                                        if (citeJoueurs.get(j.getPseudo()).equals("0")) {
                                            this.score.put("0", e.getNombre());
                                            j.setPointMerveille(j.getPointMerveille() + e.getNombre());
                                        }
                                        if (citeJoueurs.get(j.getPseudo()).equals("2")) {
                                            this.score.put("2", e.getNombre());
                                            j.setPointMerveille(j.getPointMerveille() + e.getNombre());
                                        }
                                        if (citeJoueurs.get(j.getPseudo()).equals("3")) {
                                            this.score.put("3", e.getNombre());
                                            j.setPointMerveille(j.getPointMerveille() + e.getNombre());
                                        }
                                    }
                                }

                            } else if (etage.getId().equals("etage:2")) {
                                j.constructionMerveille(etage, c);
                                for (Effet e : etage.getEffet()) {
                                    if (!e.getCapacite().equals("point de victoire")) {
                                        j.getGestionCapacite().augmenterRessource(e.getCapacite(), e.getNombre());
                                    } else {
                                        this.score.put("3", e.getNombre());
                                        j.setPointMerveille(j.getPointMerveille() + e.getNombre());
                                    }
                                }
                            } else if (etage.getId().equals("etage:3")) {
                                j.constructionMerveille(etage, c);
                                for (Effet e : etage.getEffet()) {
                                    if (e.getCapacite().equals("point de victoire")) {
                                        if (citeJoueurs.get(j.getPseudo()).equals("1")) {
                                            this.score.put("1", e.getNombre());
                                            j.setPointMerveille(j.getPointMerveille() + e.getNombre());
                                        }
                                        if (citeJoueurs.get(j.getPseudo()).equals("0")) {
                                            this.score.put("0", e.getNombre());
                                            j.setPointMerveille(j.getPointMerveille() + e.getNombre());
                                        }
                                        if (citeJoueurs.get(j.getPseudo()).equals("2")) {
                                            this.score.put("2", e.getNombre());
                                            j.setPointMerveille(j.getPointMerveille() + e.getNombre());
                                        }
                                        if (citeJoueurs.get(j.getPseudo()).equals("3")) {
                                            this.score.put("3", e.getNombre());
                                            j.setPointMerveille(j.getPointMerveille() + e.getNombre());
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
    }

    public int getPiecesJoueur(String pseudo){
        if (pseudo.equals(joueurs.get("0").getPseudo())){
            return joueurs.get("0").somme();
        }else if (pseudo.equals(joueurs.get("1").getPseudo())){
            return joueurs.get("1").somme();
        }else if(pseudo.equals(joueurs.get("2").getPseudo())){
            return joueurs.get("2").somme();
        }
        return joueurs.get("3").somme();
    }


    public boolean partieTerminee() {
        return (this.gestiontour.getTour().getAge() == BaseMongo.getBase().getAges().get(2))
                && (this.gestiontour.getTour().getNombreTourEnCours() >= this.gestiontour.getTour().getAge().getNombreTour());
    }

    public int scoreTotal(Joueur joueur){
        int score=0;
        if (this.joueurs.containsValue(joueur)){
            score+=(joueur.getNombrePieces()/3);
            for (int s: joueur.getListePointsBataille()){
                score+=s;
            }
            for (BatimentCivil b: joueur.getBatimentCivilList()){
                score+=b.getNombre();
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
        return choix.get(joueurs.get("0").getPseudo()).length() == 1 && choix.get(joueurs.get("1").getPseudo()).length() == 1
                && choix.get(joueurs.get("2").getPseudo()).length() == 1 && choix.get(joueurs.get("3").getPseudo()).length() == 1
                ;
    }

    public String vainqueur() throws PartieNonTermineeException {
        if (partieTerminee()) {
            String vainqueur="";
            for(Joueur j: this.joueurs.values()){
                if (citeJoueurs.get(j.getPseudo()).equals("1")) {
                    if(this.scoreTotal(joueurs.get("1"))>this.scoreTotal(joueurs.get("0")) && this.scoreTotal(joueurs.get("1"))>this.scoreTotal(joueurs.get("2"))
                            && this.scoreTotal(joueurs.get("1"))>this.scoreTotal(joueurs.get("3"))){
                        vainqueur=j.getPseudo();
                    }
                }if (citeJoueurs.get(j.getPseudo()).equals("0")) {
                    if(this.scoreTotal(joueurs.get("0"))>this.scoreTotal(joueurs.get("2")) && this.scoreTotal(joueurs.get("0"))>this.scoreTotal(joueurs.get("1"))
                            && this.scoreTotal(joueurs.get("0"))>this.scoreTotal(joueurs.get("3"))){
                        vainqueur=j.getPseudo();
                    }
                }if (citeJoueurs.get(j.getPseudo()).equals("2")) {
                    if(this.scoreTotal(joueurs.get("2"))>this.scoreTotal(joueurs.get("1")) && this.scoreTotal(joueurs.get("2"))>this.scoreTotal(joueurs.get("0"))
                            && this.scoreTotal(joueurs.get("2"))>this.scoreTotal(joueurs.get("3"))){
                        vainqueur=j.getPseudo();
                    }
                }if (citeJoueurs.get(j.getPseudo()).equals("3")) {
                    if(this.scoreTotal(joueurs.get("3"))>this.scoreTotal(joueurs.get("2")) && this.scoreTotal(joueurs.get("3"))>this.scoreTotal(joueurs.get("0"))
                            && this.scoreTotal(joueurs.get("3"))>this.scoreTotal(joueurs.get("1"))){
                        vainqueur= j.getPseudo();
                    }
                }
            }
            return vainqueur;
        }
        throw new PartieNonTermineeException();

    }


    public void maj(String joueur, String choix, String nomCarte, String choixCarte) throws ChoixIncompletsException, ConstructionMerveilleImpossible, ChoixDejaFaitException, CartePasConstruiteException, RessourceInexistanteException, RessourceInsuffisanteException, PieceInsuffisanteException, RessourceVoisinInsuffisantException, ConstructionImpossibleException, CiteContientCarteException {
        if (!tourJoueursFini()) {
            throw new ChoixIncompletsException();
        }
        choixJoueur(joueur,choix,nomCarte,choixCarte);
        mainJoueur.setChoixJoueur(this.choix);
        mainJoueur.setGestionTour(gestiontour);
        mainJoueur.setPaquetCarte(this.paquetCarte);
        mainJoueur.setNombreJoueur(this.gestionCommerce.getNombreJoueur());
        mainJoueur.passageMainSuivante();

        Score score = new ScoreImpl(this.gestiontour,joueurs.get("0"),this.joueurs.get("1"),this.joueurs.get("2"),this.joueurs.get("3"),
                this.choix.get(joueurs.get("0").getPseudo()),this.choix.get(this.joueurs.get("1").getPseudo()),
                this.choix.get(this.joueurs.get("2").getPseudo()), this.choix.get(this.joueurs.get("3").getPseudo()));
        this.choix.clear();
        this.gestiontour.passageAgeSuivant();

    }


    public Map<String, String> getChoix() {
        return choix;
    }

    public GestionTour getGestiontour() {
        return gestiontour;
    }

    public void setGestiontour(GestionTour gestiontour) {
        this.gestiontour = gestiontour;
    }

    public Map<String, Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(Map<String, Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public Map<String, Integer> getScore() {
        return score;
    }

    public void setScore(Map<String, Integer> score) {
        this.score = score;
    }

    public void setChoix(Map<String, String> choix) {
        this.choix = choix;
    }

    public Map<String, String> getCiteJoueurs() {
        return citeJoueurs;
    }

    public void setCiteJoueurs(Map<String, String> citeJoueurs) {
        this.citeJoueurs = citeJoueurs;
    }

    public GestionCommerce getGestionCommerce() {
        return gestionCommerce;
    }

    public void setGestionCommerce(GestionCommerce gestionCommerce) {
        this.gestionCommerce = gestionCommerce;
    }

    public GestionGuilde getGestionGuilde() {
        return gestionGuilde;
    }

    public void setGestionGuilde(GestionGuilde gestionGuilde) {
        this.gestionGuilde = gestionGuilde;
    }

    public GestionBataille getGestionBataille() {
        return gestionBataille;
    }

    public void setGestionBataille(GestionBataille gestionBataille) {
        this.gestionBataille = gestionBataille;
    }

    public MainJoueur getMainJoueur() {
        return mainJoueur;
    }

    public void setMainJoueur(MainJoueur mainJoueur) {
        this.mainJoueur = mainJoueur;
    }

    public PaquetCarte getPaquetCarte() {
        return paquetCarte;
    }

    public void setPaquetCarte(PaquetCarte paquetCarte) {
        this.paquetCarte = paquetCarte;
    }

    public Map<String, Integer> getNombreJoueur() {
        return nombreJoueur;
    }

    public void setNombreJoueur(Map<String, Integer> nombreJoueur) {
        this.nombreJoueur = nombreJoueur;
    }

    @Override
    public String toString() {
        return "PartieGestion{" +
                "gestiontour=" + gestiontour +
                ", joueurs=" + joueurs +
                ", choix=" + choix +
                ", score=" + score +
                ", citeJoueurs=" + citeJoueurs +
                ", gestionCommerce=" + gestionCommerce +
                ", gestionGuilde=" + gestionGuilde +
                ", gestionBataille=" + gestionBataille +
                ", mainJoueur=" + mainJoueur +
                ", paquetCarte=" + paquetCarte +
                '}';
    }
}
