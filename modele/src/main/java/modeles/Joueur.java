package modeles;

import modeles.dao.BaseMongo;
import modeles.exceptions.*;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import java.io.Serializable;
import java.util.*;

public class Joueur implements Serializable {
    private static final long serialVersionUID=1L;

    @BsonProperty("_id")
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    private String nom;
    private String prenom;
    private String pseudo;
    private String age;

    private String motDePasse;
    @BsonProperty("nombreDePieces")
    private int nombrePieces;
    @BsonProperty("nombreDeBoucliers")
    private int nombreBoucliers;
    @BsonProperty("listeDeCartesEnPossession")
    private List<Carte> listeCartes;
    @BsonProperty("uneListeDePieces")
    private List<Piece> listePieces;
    @BsonProperty("listeDesBatimentsCivils")
    private List<BatimentCivil> batimentCivilList;
    @BsonProperty("nombreDePoints")
    private int nombreDePoints;
    private String merveilles;
    private Defausse defausse;
    private Collection<Jeton> jetons;
    private Cite cite;
    private GestionCapacite gestionCapacite;
    private Map<String,Carte> cartesEtages;
    private GestionCommerce gestionCommerce;
    private GestionGuilde gestionGuilde;
    private List<Integer> listePointsBataille;
    private List<BatimentScientifique> batimentScientifiques;
    private int pointsVictoireCommerce;
    private int pointsVictoireGuilde;
    private int pointMerveille;

    public Joueur(){}


    public Joueur( String nom, String prenom, String pseudo, String age, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.age = age;
        this.motDePasse = motDePasse;
        this.nombreBoucliers = 0;
        this.nombreDePoints=0;
        this.listeCartes = new ArrayList<>();
        this.listePieces = new ArrayList<>();
        this.defausse= new Defausse();
        this.nombrePieces=0;
        this.batimentCivilList = new ArrayList<>();
        this.cartesEtages = new HashMap<>();
        this.listePointsBataille=new ArrayList<>();
        this.batimentScientifiques=new ArrayList<>();
        this.pointsVictoireCommerce=0;
        this.pointsVictoireGuilde=0;
        this.pointMerveille=0;
        this.gestionCapacite=new GestionCapacite();
        this.merveilles="";
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getNombrePieces() {
        return nombrePieces;
    }

    public void setNombrePieces(int nombrePieces) {
        this.nombrePieces = nombrePieces;
    }


    public int getNombreBoucliers() {
        return nombreBoucliers;
    }

    public void setNombreBoucliers(int nombreBoucliers) {
        this.nombreBoucliers = nombreBoucliers;
    }

    public List<Carte> getListeCartes() {
        return listeCartes;
    }

    public void setListeCartes(List<Carte> listeCartes) {
        this.listeCartes = listeCartes;
    }

    public List<Piece> getListePieces() {
        return listePieces;
    }

    public void setListePieces(List<Piece> listePieces) {
        this.listePieces = listePieces;
    }

    public List<BatimentCivil> getBatimentCivilList() {
        return batimentCivilList;
    }

    public void setBatimentCivilList(List<BatimentCivil> batimentCivilList) {
        this.batimentCivilList = batimentCivilList;
    }

    public String getMerveilles() {
        return merveilles;
    }

    public void setMerveilles(String merveilles) {
        this.merveilles = merveilles;
    }

    public void setDefausse(Defausse defausse) {
        this.defausse = defausse;
    }

    public void setJetons(Collection<Jeton> jetons) {
        this.jetons = jetons;
    }


    public Defausse getDefausse() {
        return defausse;
    }

    public Collection<Jeton> getJetons() {
        return jetons;
    }

    public int getNombreDePoints() {
        return nombreDePoints;
    }

    public void setNombreDePoints(int nombreDePoints) {
        this.nombreDePoints = nombreDePoints;
    }

    public void setCite(Cite cite) {
        this.cite = cite;
    }

    public Cite getCite() {
        return cite;
    }

    public GestionCommerce getGestionCommerce() {
        return gestionCommerce;
    }

    public GestionGuilde getGestionGuilde() {
        return gestionGuilde;
    }

    public void setGestionCommerce(GestionCommerce gestionCommerce) {
        this.gestionCommerce = gestionCommerce;
    }

    public void setGestionGuilde(GestionGuilde gestionGuilde) {
        this.gestionGuilde = gestionGuilde;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", age='" + age + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", nombrePieces=" + nombrePieces +
                ", nombreBoucliers=" + nombreBoucliers +
                ", listeCartes=" + listeCartes +
                ", listePieces=" + listePieces +
                ", batimentCivilList=" + batimentCivilList +
                ", merveilles='" + merveilles + '\'' +
                ", cite="+cite+
                '}';
    }

    public void choisirPlateau(String nomPlateau){
            this.setMerveilles(nomPlateau);
            BaseMongo.getBase().ajoutMerveilleJoueur(this.pseudo,nomPlateau);
    }

    public int scoreEtages(modeles.Joueur joueur){

        if (joueur.merveilleFinie() && joueur == this){
            joueur.setNombreDePoints(joueur.getNombreDePoints()+10);
        }else{
            joueur.setNombreDePoints(joueur.getNombreDePoints()+3);
        }
        return this.getNombreDePoints();
    }

    public void defausser(Carte carte){
        defausse.getCarteDefaussees().add(carte);
        Piece piece = BaseMongo.getBase().getPiece("or");
        this.listePieces.add(piece);
        this.nombrePieces+=piece.getValeur();
        Banque.setPieces(Banque.getPieces()-piece.getValeur());
        BaseMongo.getBase().ajoutCarteDefausse(pseudo,carte);
    }

    public boolean citeMemeCarte(String nomCarte){
        for (Carte c: this.cite.getCartes()){
            if (c.getNom().equals(nomCarte)){
                return true;
            }
        }
        return false;
    }

    public List<Carte> cartesEnPossession(PaquetCarte paquetCarte){
        for(int i=0;i<7;i++){
            this.listeCartes.add(paquetCarte.distribuire(4).get(i));
            paquetCarte.distribuire(4).remove(paquetCarte.distribuire(4).get(i));
        }
        return this.listeCartes;
    }

    public void ajoutCite(Carte carte){
        this.cite.ajoutCarte(carte);
        BaseMongo.getBase().ajoutCarteCite(this.pseudo,carte);
    }

    public void constructionMerveille(Etage etage,Carte carte) throws ConstructionMerveilleImpossible {
        switch (etage.getCoutConstruction().getCout().getRessource()){
            case "pierre":
                if (this.gestionCapacite.getNombrePierre()>=etage.getCoutConstruction().getCout().getNombreUnite()){
                    this.gestionCapacite.diminuer("pierre",etage.getCoutConstruction().getCout().getNombreUnite());
                }else {
                    throw new ConstructionMerveilleImpossible();
                }
                break;
            case "bois":
                if (this.gestionCapacite.getNombreBois()>=etage.getCoutConstruction().getCout().getNombreUnite()){
                    this.gestionCapacite.diminuer("bois",etage.getCoutConstruction().getCout().getNombreUnite());
                }else {
                    throw new ConstructionMerveilleImpossible();
                }
                break;
            case "argile":
                if (this.gestionCapacite.getNombreArgile()>=etage.getCoutConstruction().getCout().getNombreUnite()){
                    this.gestionCapacite.diminuer("argile",etage.getCoutConstruction().getCout().getNombreUnite());
                }else {
                    throw new ConstructionMerveilleImpossible();
                }
                break;
            case "minerai":
                if (this.gestionCapacite.getNombreMinerai()>=etage.getCoutConstruction().getCout().getNombreUnite()){
                    this.gestionCapacite.diminuer("minerai",etage.getCoutConstruction().getCout().getNombreUnite());
                }else {
                    throw new ConstructionMerveilleImpossible();
                }
                break;
            case "verre":
                if (this.gestionCapacite.getNombreVerre()>=etage.getCoutConstruction().getCout().getNombreUnite()){
                    this.gestionCapacite.diminuer("verre",etage.getCoutConstruction().getCout().getNombreUnite());
                }else {
                    throw new ConstructionMerveilleImpossible();
                }
                break;
            case "papyrus":
                if (this.gestionCapacite.getNombrePapyrus()>=etage.getCoutConstruction().getCout().getNombreUnite()){
                    this.gestionCapacite.diminuer("papyrus",etage.getCoutConstruction().getCout().getNombreUnite());
                }else {
                    throw new ConstructionMerveilleImpossible();
                }
                break;
            case "tissu":
                if (this.gestionCapacite.getNombreTissu()>=etage.getCoutConstruction().getCout().getNombreUnite()){
                    this.gestionCapacite.diminuer("tissu",etage.getCoutConstruction().getCout().getNombreUnite());
                }else {
                    throw new ConstructionMerveilleImpossible();
                }
                break;
        }
        this.cartesEtages.put(etage.getId(),carte);
        BaseMongo.getBase().ajoutCarteEtagesJoueur(pseudo,this.cartesEtages.get(etage.getId()));

    }


    public void construireBatimentCivils(Carte c) throws RessourceInsuffisanteException, ConstructionImpossibleException, PieceInsuffisanteException, CiteContientCarteException {
        if (!citeMemeCarte(c.getNom())) {
            if (this.gestionCapacite.verifierCout(c.getCout()) || this.gestionCapacite.verifGratuits(((List<Cout>) c.getCout()).get(0))) {
                if (!c.getCout().isEmpty()) {
                    for (Cout cout : c.getCout()) {
                        this.gestionCapacite.diminuer(cout.getRessource(), cout.getNombreUnite());
                    }
                }
                for (Effet e : c.getEffet()) {
                    if (e.avoirMemeRessource("point de victoire")) {
                        this.nombreDePoints += e.getNombre();
                        BatimentCivil civil = new BatimentCivil(c.getNom(), e.getNombre());
                        this.batimentCivilList.add(civil);
                        BaseMongo.getBase().ajoutBatimentsCivils(this.pseudo, civil);
                        BaseMongo.getBase().ajoutPointsJoueur(this.pseudo, this.nombreDePoints);
                    }
                }
                this.ajoutCite(c);
            } else {
                if (!this.gestionCapacite.verifierCout(c.getCout())) {
                    if (this.pieceSuffisante(GestionCommerce.getCoutPieces())) {
                        gestionCommerce.acheterRessourceVoisin(pseudo, c.getNom());
                    }
                    throw new ConstructionImpossibleException();
                }

            }
        }else{
            throw new CiteContientCarteException();
        }
    }

    public List<BatimentScientifique> getSymboleScientifique(String typeSymbole){
        List<BatimentScientifique> list = new ArrayList<>();
        for (BatimentScientifique b: this.getBatimentScientifiques()){
            if (b.getTypeSymbole().equals(typeSymbole)){
                list.add(b);
            }
        }
        return list;
    }

    public int symbolesScientifiquesDifferents(){

        Map<Integer,List<BatimentScientifique>> groupes=new HashMap<>();
        if (this.getSymboleScientifique("compas").size()==1 || this.getSymboleScientifique("roue").size()==1
        || this.getSymboleScientifique("tablette").size()==1){
            this.setNombreDePoints(this.getNombreDePoints()+7);

        }
        else if (this.getSymboleScientifique("compas").size() <= this.getSymboleScientifique("roue").size()
        && this.getSymboleScientifique("compas").size()<=this.getSymboleScientifique("tablette").size()){
            this.setNombreDePoints(this.getNombreDePoints()+(7*this.getSymboleScientifique("compas").size()));

        }
        else if (this.getSymboleScientifique("roue").size() <= this.getSymboleScientifique("compas").size()
                && this.getSymboleScientifique("roue").size()<=this.getSymboleScientifique("tablette").size()){
            this.setNombreDePoints(this.getNombreDePoints()+(7*this.getSymboleScientifique("roue").size()));

        }
        else if (this.getSymboleScientifique("tablette").size() <= this.getSymboleScientifique("roue").size()
                && this.getSymboleScientifique("tablette").size()<=this.getSymboleScientifique("compas").size()){
            this.setNombreDePoints(this.getNombreDePoints()+(7*this.getSymboleScientifique("tablette").size()));
        }
        return this.getNombreDePoints();
    }

    public void construireBatimentsScientifiques(Carte c) throws RessourceInsuffisanteException, PieceInsuffisanteException, ConstructionImpossibleException, CiteContientCarteException {
        if (!this.citeMemeCarte(c.getNom())) {
            if (this.gestionCapacite.verifierCout(c.getCout()) || this.gestionCapacite.verifGratuits(((List<Cout>) c.getCout()).get(0))) {
                if (!c.getCout().isEmpty()) {
                    for (Cout cout : c.getCout()) {
                        this.gestionCapacite.diminuer(cout.getRessource(), cout.getNombreUnite());
                    }
                }
                for (Effet e : c.getEffet()) {
                    if (e.avoirMemeRessource("compas") || e.avoirMemeRessource("roue") || e.avoirMemeRessource("tablette")) {
                        this.gestionCapacite.ajoutSymbolesScientifiques(e.getCapacite(), e.getNombre());
                        BatimentScientifique batimentScientifique = new BatimentScientifique(c.getNom(), e.getCapacite(), e.getNombre());
                        this.batimentScientifiques.add(batimentScientifique);
                    }
                }

                this.ajoutCite(c);
            } else {
                if (!this.gestionCapacite.verifierCout(c.getCout())) {
                    if (this.pieceSuffisante(GestionCommerce.getCoutPieces())) {
                        gestionCommerce.acheterRessourceVoisin(pseudo, c.getNom());
                    }
                    throw new ConstructionImpossibleException();
                }

            }
        }else{
            throw new CiteContientCarteException();
        }
    }

    public void construireBatimentMilitaires(Carte c) throws RessourceInsuffisanteException, PieceInsuffisanteException, ConstructionImpossibleException, CiteContientCarteException {
        if (!citeMemeCarte(c.getNom())) {
            if (this.gestionCapacite.verifierCout(c.getCout()) || this.gestionCapacite.verifGratuits(((List<Cout>) c.getCout()).get(0))) {
                if (!c.getCout().isEmpty()) {
                    for (Cout cout : c.getCout()) {
                        this.gestionCapacite.diminuer(cout.getRessource(), cout.getNombreUnite());
                    }
                }
                for (Effet e : c.getEffet()) {
                    if (e.avoirMemeRessource("bouclier")) {
                        this.nombreBoucliers += e.getNombre();
                        BaseMongo.getBase().ajoutBouclierJoueur(this.pseudo, this.nombreBoucliers);
                    }
                }
                this.ajoutCite(c);
            } else {
                if (!this.gestionCapacite.verifierCout(c.getCout())) {
                    if (this.pieceSuffisante(GestionCommerce.getCoutPieces())) {
                        gestionCommerce.acheterRessourceVoisin(pseudo, c.getNom());
                    }
                    throw new ConstructionImpossibleException();
                }

            }
        }else{
            throw new CiteContientCarteException();
        }
    }

    public void construireMatierePremiere(Carte c) throws RessourceInsuffisanteException, PieceInsuffisanteException, ConstructionImpossibleException, CiteContientCarteException {
        if (!citeMemeCarte(c.getNom())) {
            if (this.gestionCapacite.verifierCout(c.getCout()) || this.gestionCapacite.verifGratuits(((List<Cout>) c.getCout()).get(0))) {
                if (!c.getCout().isEmpty()) {
                    for (Cout cout : c.getCout()) {
                        this.gestionCapacite.diminuer(cout.getRessource(), cout.getNombreUnite());
                    }
                }
                for (Effet e : c.getEffet()) {
                    if (e.avoirMemeRessource("pierre") || e.avoirMemeRessource("argile") || e.avoirMemeRessource("bois")
                            || e.avoirMemeRessource("minerai")) {
                        this.gestionCapacite.ajoutMatierePremiere(e.getCapacite(), e.getNombre());
                    }
                }
                this.ajoutCite(c);
            } else {
                if (!this.gestionCapacite.verifierCout(c.getCout())) {
                    if (this.pieceSuffisante(GestionCommerce.getCoutPieces())) {
                        gestionCommerce.acheterRessourceVoisin(pseudo, c.getNom());
                    }
                    throw new ConstructionImpossibleException();
                }

            }
        }else{
            throw new CiteContientCarteException();
        }
    }

    public void construireGuilde( String nomCarte) throws RessourceInsuffisanteException, ConstructionImpossibleException, PieceInsuffisanteException, CiteContientCarteException {
        if (!citeMemeCarte(nomCarte)) {
            Carte c = BaseMongo.getBase().getCartesNom(nomCarte);
            if (this.gestionCapacite.verifierCout(c.getCout()) || this.gestionCapacite.verifGratuits(((List<Cout>) c.getCout()).get(0))) {
                if (!c.getCout().isEmpty()) {
                    for (Cout cout : c.getCout()) {
                        this.gestionCapacite.diminuer(cout.getRessource(), cout.getNombreUnite());
                    }
                    this.ajoutCite(c);
                }
                gestionGuilde.beneficeGuilde(pseudo,nomCarte);
            } else {
                if (!this.gestionCapacite.verifierCout(c.getCout())) {
                    if (this.pieceSuffisante(GestionCommerce.getCoutPieces())) {
                        gestionCommerce.acheterRessourceVoisin(pseudo, c.getNom());
                    }
                    throw new ConstructionImpossibleException();
                }
            }
        }else{
            throw new CiteContientCarteException();
        }
    }


    public void construireBatimentCommercial(String nomCarte) throws RessourceInsuffisanteException, PieceInsuffisanteException, RessourceVoisinInsuffisantException, RessourceInexistanteException, ConstructionImpossibleException, CiteContientCarteException {
        if (!citeMemeCarte(nomCarte)) {
            this.gestionCommerce.constructionBatiment(pseudo, nomCarte);
        }else{
            throw new CiteContientCarteException();
        }
    }

    public void construireProduitManufactures(Carte c) throws RessourceInsuffisanteException, ConstructionImpossibleException, PieceInsuffisanteException, CiteContientCarteException {
        if (!citeMemeCarte(c.getNom())) {
            if (this.gestionCapacite.verifierCout(c.getCout()) || this.gestionCapacite.verifGratuits(((List<Cout>) c.getCout()).get(0))) {
                for (Cout cout : c.getCout()) {

                    this.gestionCapacite.diminuer(cout.getRessource(), cout.getNombreUnite());
                }
                for (Effet e : c.getEffet()) {
                    if (e.avoirMemeRessource("verre") || e.avoirMemeRessource("tissu") || e.avoirMemeRessource("papyrus")) {
                        this.gestionCapacite.ajoutProduitsManufactures(e.getCapacite(), e.getNombre());
                    }
                }
                this.ajoutCite(c);
            } else {
                if (!this.gestionCapacite.verifierCout(c.getCout())) {
                    if (this.pieceSuffisante(GestionCommerce.getCoutPieces())) {
                        gestionCommerce.acheterRessourceVoisin(pseudo, c.getNom());
                    }
                    throw new ConstructionImpossibleException();
                }

            }
        }else{
            throw new CiteContientCarteException();
        }

    }


    public void jouerCarte(String carte) throws RessourceInsuffisanteException, PieceInsuffisanteException, RessourceVoisinInsuffisantException, RessourceInexistanteException, CartePasConstruiteException, ConstructionImpossibleException, CiteContientCarteException {

        for (Carte c: this.listeCartes){
            if (c.getNom().equals(carte)){
                switch(c.getType()){
                    case "Bâtiments civils":
                        this.construireBatimentCivils(c);

                    case "Bâtiments scientifiques":
                        this.construireBatimentsScientifiques(c);

                    case "Matières premeières":
                        this.construireMatierePremiere(c);


                    case "Produits manufacturés":
                        this.construireProduitManufactures(c);

                    case "Bâtiments militaires":
                        this.construireBatimentMilitaires(c);

                    case "Guilde":
                        this.construireGuilde(c.getNom());

                    case "Bâtiments commerciaux":
                        this.construireBatimentCommercial(c.getNom());

                }
            }
        }
        throw new CartePasConstruiteException();
    }


    public boolean verifieConstructionEtage(){
        return !cartesEtages.isEmpty();
    }

    /**
     *Cette méthode vérifie si toutes les étage de merveille du joueur est finie
     */
    public boolean merveilleFinie(){
        return this.cartesEtages.containsKey("etage:1") && this.cartesEtages.containsKey("etage:2")
                && this.cartesEtages.containsKey("etage:3");
    }

    public boolean verifEffetCarteCite(String ressource){
        for (Carte c: this.cite.getCartes()){
            for (Effet e : c.getEffet()){
                if (e.avoirMemeRessource(ressource)) {
                    return true;
                }
            }
        }
        return false;
    }
    public int somme(){
        int somme=0;
        for (Piece piece:this.listePieces){
            somme+=piece.getValeur();
        }
        this.nombrePieces=somme;
        return somme;
    }

    public boolean pieceSuffisante(int nb){
        return this.nombrePieces >= nb;
    }

    public void soustrairePiece(int nombre){
        this.nombrePieces= this.nombrePieces-nombre;
    }

    public Effet effetCarteCite(String ressource) throws RessourceInexistanteException {
        for (Carte c: this.cite.getCartes()){
            for (Effet e : c.getEffet()){
                if (e.avoirMemeRessource(ressource)) {
                    return e;
                }
            }
        }
        throw new RessourceInexistanteException();

    }

    public Map<String, Carte> getCartesEtages() {
        return cartesEtages;
    }

    public void setCartesEtages(Map<String, Carte> cartesEtages) {
        this.cartesEtages = cartesEtages;
    }

    public void setGestionCapacite(GestionCapacite gestionCapacite) {
        this.gestionCapacite = gestionCapacite;
    }

    public GestionCapacite getGestionCapacite() {
        return gestionCapacite;
    }

    public List<Integer> getListePointsBataille() {
        return listePointsBataille;
    }

    public void setListePointsBataille(List<Integer> listePointsBataille) {
        this.listePointsBataille = listePointsBataille;
    }

    public List<BatimentScientifique> getBatimentScientifiques() {
        return batimentScientifiques;
    }

    public void setBatimentScientifiques(List<BatimentScientifique> batimentScientifiques) {
        this.batimentScientifiques = batimentScientifiques;
    }

    public int getPointsVictoireCommerce() {
        return pointsVictoireCommerce;
    }

    public void setPointsVictoireCommerce(int pointsVictoireCommerce) {
        this.pointsVictoireCommerce = pointsVictoireCommerce;
    }

    public int getPointsVictoireGuilde() {
        return pointsVictoireGuilde;
    }

    public void setPointsVictoireGuilde(int pointsVictoireGuilde) {
        this.pointsVictoireGuilde = pointsVictoireGuilde;
    }

    public int getPointMerveille() {
        return pointMerveille;
    }

    public void setPointMerveille(int pointMerveille) {
        this.pointMerveille = pointMerveille;
    }
}
