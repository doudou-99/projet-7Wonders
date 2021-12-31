package modele;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import modele.dao.BaseMongo;
import modele.exceptions.ConstructionMerveilleImpossible;
import modele.exceptions.RessourceInexistanteException;
import modele.exceptions.RessourceInsuffisanteException;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.bson.conversions.Bson;

import java.util.*;

public class Joueur {
    @BsonProperty("_id")
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    private String nom;
    private String prenom;
    private String pseudo;
    private String age;
    private static int nb=0;
    private String motDePasse;
    @BsonProperty("nombreDePiecesEnOr")
    private int nombrePiecesOr;
    @BsonProperty("nombreDePiecesEnArgent")
    private int nombrePiecesArgent;
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

    public Joueur(){}


    public Joueur( String nom, String prenom, String pseudo, String age, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.age = age;
        this.motDePasse = motDePasse;
        this.nombreBoucliers = nb;
        this.nombrePiecesOr=0;
        this.nombrePiecesArgent=0;
        this.nombreDePoints=0;
        this.listeCartes = new ArrayList<>();
        this.listePieces = new ArrayList<>();
        this.batimentCivilList = new ArrayList<>();
        this.cartesEtages = new HashMap<>();
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

    public int getNombrePiecesOr() {
        return nombrePiecesOr;
    }

    public void setNombrePiecesOr(int nombrePiecesOr) {
        this.nombrePiecesOr = nombrePiecesOr;
    }

    public int getNombrePiecesArgent() {
        return nombrePiecesArgent;
    }

    public void setNombrePiecesArgent(int nombrePiecesArgent) {
        this.nombrePiecesArgent = nombrePiecesArgent;
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

    @Override
    public String toString() {
        return "Joueur{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", age='" + age + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", nombrePiecesOr=" + nombrePiecesOr +
                ", nombrePiecesArgent=" + nombrePiecesArgent +
                ", nombreBoucliers=" + nombreBoucliers +
                ", listeCartes=" + listeCartes +
                ", listePieces=" + listePieces +
                ", batimentCivilList=" + batimentCivilList +
                ", merveilles='" + merveilles + '\'' +
                ", cite="+cite+
                '}';
    }

    public void defausser(Carte carte){
        defausse.getCarteDefaussees().add(carte);
        Piece piece = new Piece(3,"OR");
        this.listePieces.add(piece);
        nombrePiecesOr+=piece.getPiece();
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo",this.getPseudo());
        Bson update = Updates.push("defausse.listeDesCartesDefausse",carte);
        BaseMongo.getBase().getJoueurs().findOneAndUpdate(filter,update, options);
    }

    public List<Carte> cartesEnPossession(PaquetCarte paquetCarte){
        for(int i=0;i<6;i++){
            this.listeCartes.add((Carte) paquetCarte.distribuire(4).toArray()[i]);
            paquetCarte.distribuire(4).remove(paquetCarte.distribuire(4).get(i));
        }
        return this.listeCartes;
    }

    public void ajoutCite(Carte carte){
        this.cite.ajoutCarte(carte);
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo",this.getPseudo());
        Bson update = Updates.push("cite.cartes",carte);
        BaseMongo.getBase().getJoueurs().findOneAndUpdate(filter,update, options);
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
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo",this.getPseudo());
        Bson update = Updates.push("joueurs.cartesEtages",this.cartesEtages);
        BaseMongo.getBase().getJoueurs().findOneAndUpdate(filter,update, options);

    }

    public Carte construireBatimentCivils(Carte c) throws RessourceInsuffisanteException {
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo", this.getPseudo());
        if (this.gestionCapacite.verifierCout(c.getCout()) || this.gestionCapacite.verifGratuits(((List<Cout>) c.getCout()).get(0))) {
            if (!c.getCout().isEmpty()){
                for (Cout cout : c.getCout()) {
                    this.gestionCapacite.diminuer(cout.getRessource(), cout.getNombreUnite());
                }
            }
            for (Effet e : c.getEffet()) {
                if (e.avoirMemeRessource("point de victoire")) {
                    this.nombreDePoints += e.getNombre();
                    BatimentCivil civil = new BatimentCivil(e.getNombre());
                    this.batimentCivilList.add(civil);
                    Bson updat = Updates.push("listeDesBatimentsCivils", civil);
                    BaseMongo.getBase().getJoueurs().findOneAndUpdate(filter, updat, options);
                    Bson upda = Updates.push("nombreDePoints", this.nombreDePoints);
                    BaseMongo.getBase().getJoueurs().findOneAndUpdate(filter, upda, options);
                }
            }
            Bson update = Updates.push("cite.cartes", c);
            BaseMongo.getBase().getJoueurs().findOneAndUpdate(filter, update, options);
            this.cite.ajoutCarte(c);
            return c;
        }
        throw new RessourceInsuffisanteException();

    }

    public Carte construireBatimentsScientifiques(Carte c) throws RessourceInsuffisanteException {
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo", this.getPseudo());
        if (this.gestionCapacite.verifierCout(c.getCout()) || this.gestionCapacite.verifGratuits(((List<Cout>) c.getCout()).get(0))){
            if (!c.getCout().isEmpty()){
                for (Cout cout : c.getCout()) {
                    this.gestionCapacite.diminuer(cout.getRessource(), cout.getNombreUnite());
                }
            }
            for (Effet e : c.getEffet()) {
                if (e.avoirMemeRessource("compas") || e.avoirMemeRessource("roue") || e.avoirMemeRessource("tablette")) {
                    this.gestionCapacite.ajoutSymbolesScientifiques(e.getCapacite(), e.getNombre());
                }
            }
            Bson update = Updates.push("cite.cartes", c);
            BaseMongo.getBase().getJoueurs().findOneAndUpdate(filter, update, options);
            this.cite.ajoutCarte(c);
            return c;
        }
        throw new RessourceInsuffisanteException();
    }

    public Carte construireBatimentMilitaires(Carte c) throws RessourceInsuffisanteException{
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo", this.getPseudo());
        if (this.gestionCapacite.verifierCout(c.getCout()) || this.gestionCapacite.verifGratuits(((List<Cout>) c.getCout()).get(0))){
            if (!c.getCout().isEmpty()){
                for (Cout cout : c.getCout()) {
                    this.gestionCapacite.diminuer(cout.getRessource(), cout.getNombreUnite());
                }
            }
            for (Effet e : c.getEffet()) {
                if (e.avoirMemeRessource("bouclier")) {
                    this.nombreBoucliers+= e.getNombre();
                    Bson update = Updates.push("nombreDeBoucliers", this.nombreBoucliers);
                    BaseMongo.getBase().getJoueurs().findOneAndUpdate(filter, update, options);
                }
            }
            Bson update = Updates.push("cite.cartes", c);
            BaseMongo.getBase().getJoueurs().findOneAndUpdate(filter, update, options);
            this.cite.ajoutCarte(c);
            return c;
        }
        throw new RessourceInsuffisanteException();
    }

    public Carte construireMatierePremiere(Carte c) throws RessourceInsuffisanteException {
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo", this.getPseudo());
        if (this.gestionCapacite.verifierCout(c.getCout()) || this.gestionCapacite.verifGratuits(((List<Cout>) c.getCout()).get(0))){
            if (!c.getCout().isEmpty()){
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
            Bson update = Updates.push("cite.cartes", c);
            BaseMongo.getBase().getJoueurs().findOneAndUpdate(filter, update, options);
            this.cite.ajoutCarte(c);
            return c;
        }
        throw new RessourceInsuffisanteException();
    }

    public Carte construireProduitManufacturés(Carte c) throws RessourceInsuffisanteException{
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo", this.getPseudo());
        if (this.gestionCapacite.verifierCout(c.getCout()) || this.gestionCapacite.verifGratuits(((List<Cout>) c.getCout()).get(0))){
            if (!c.getCout().isEmpty()){
                for (Cout cout : c.getCout()) {
                    this.gestionCapacite.diminuer(cout.getRessource(), cout.getNombreUnite());
                }
            }
            for (Effet e : c.getEffet()) {
                if (e.avoirMemeRessource("verre") || e.avoirMemeRessource("tissu") || e.avoirMemeRessource("papyrus")) {
                    this.gestionCapacite.ajoutProduitsManufactures(e.getCapacite(), e.getNombre());
                }
            }
            Bson update = Updates.push("cite.cartes", c);
            BaseMongo.getBase().getJoueurs().findOneAndUpdate(filter, update, options);
            this.cite.ajoutCarte(c);
        }
        throw new RessourceInsuffisanteException();
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

}
