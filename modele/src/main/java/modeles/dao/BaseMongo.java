package modeles.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import modeles.*;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class BaseMongo{
    private static BaseMongo base = new BaseMongo();

    private List<Joueur> joueurList= new ArrayList<>();
    private List<Tour> tourList = new ArrayList<>();
    private List<Bataille> batailleList = new ArrayList<>();
    private List<Partie> partieList = new ArrayList<>();
    private MongoDatabase mongoDatabase;
    


    private MongoCollection<Joueur> joueurs;

    private MongoCollection<Tour> tours;
    private MongoCollection<Bataille> batailles;
    private MongoCollection<Partie> jeu;


    private BaseMongo(){
        ClassModel<Plateau> classModel = ClassModel.builder(Plateau.class).conventions(Arrays.asList(Conventions.ANNOTATION_CONVENTION)).build();
        ClassModel<Age> classMode = ClassModel.builder(Age.class).conventions(Arrays.asList(Conventions.ANNOTATION_CONVENTION)).build();
        ClassModel<Tour> classMo =ClassModel.builder(Tour.class).conventions(Arrays.asList(Conventions.ANNOTATION_CONVENTION)).build();
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().register("modeles")
                .register(classModel).register(classMode).register(classMo)
                .build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        mongoDatabase= mongoClient.getDatabase("wonders").withCodecRegistry(pojoCodecRegistry);
        this.joueurs=mongoDatabase.getCollection("joueurs",Joueur.class);
        this.tours= mongoDatabase.getCollection("tours",Tour.class);
        this.batailles= mongoDatabase.getCollection("batailles",Bataille.class);
        this.jeu=mongoDatabase.getCollection("jeu",Partie.class);

        joueurs.find().into(joueurList);
        this.tours.find().into(tourList);
        this.batailles.find().into(batailleList);
        this.jeu.find().into(partieList);
    }


    public List<Plateau> getPlateauList() {
        MongoCollection<Plateau> plateau =
                mongoDatabase.getCollection("plateaux", Plateau.class);
        List<Plateau> plateauArrayList = new ArrayList<>();
        plateau.find().forEach(plateauArrayList::add);
        return plateauArrayList;
    }

    public Plateau getPlateauNom(String nomPlateau) {
        MongoCollection<Plateau> plateau =
                mongoDatabase.getCollection("plateaux", Plateau.class);

        return plateau.find(Filters.eq("nom",nomPlateau)).first();

    }

    public List<Carte> getCartes() {
        MongoCollection<Carte> cartes = mongoDatabase.getCollection("cartes",Carte.class);
        List<Carte> collection = new ArrayList<>();
        cartes.find().forEach(collection::add);
        return collection;
    }


    public List<Carte> getCartesAges(String a) {
        MongoCollection<Carte> cartes = mongoDatabase.getCollection("cartes",Carte.class);
        List<Carte> carteCollection = new ArrayList<>();
        cartes.find(Filters.eq("age",a)).forEach(carteCollection::add);
        return carteCollection;
    }

    public Carte getCartesNom(String nom) {
        MongoCollection<Carte> cartes = mongoDatabase.getCollection("cartes",Carte.class);
        return cartes.find(Filters.eq("nom",nom)).first();
    }


    public List<Age> getAges() {
        MongoCollection<Age> ages = mongoDatabase.getCollection("ages",Age.class);
        List<Age> ageCollection = new ArrayList<>();
        ages.find().forEach(ageCollection::add);
        return ageCollection;
    }


    public List<Jeton> getJetons() {
        MongoCollection<Jeton> jetons = mongoDatabase.getCollection("jetons",Jeton.class);
        List<Jeton> jetonCollection = new ArrayList<>();
        jetons.find().forEach(jetonCollection::add);
        return jetonCollection;
    }

    public Jeton getJeton(String type,String age){
        MongoCollection<Jeton> jeton = mongoDatabase.getCollection("jetons",Jeton.class);
        return jeton.find(Filters.and(Filters.eq("type",type),Filters.eq("age",age))).first();
    }



    public void ajoutCarteCite(String pseudo, Carte c){
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo",pseudo);
        Bson update = Updates.push("cite.cartes",c);
        this.getJoueurs().findOneAndUpdate(filter,update, options);
    }

    public void ajoutJetonJoueur(String pseudo, Jeton jet){
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo",pseudo);
        Bson update = Updates.push("jetons",jet);
        this.getJoueurs().findOneAndUpdate(filter,update, options);
    }

    public void ajoutCarteDefausse(String pseudo,Carte carte){
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo",pseudo);
        Bson update = Updates.push("defausse.listeDesCarteDefausse",carte);
        this.getJoueurs().findOneAndUpdate(filter,update, options);
    }

    public void ajoutMerveilleJoueur(String pseudo,String merveille){
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo",pseudo);
        Bson update = Updates.push("merveilles",merveille);
        this.getJoueurs().findOneAndUpdate(filter,update, options);
    }

    public void ajoutBataille(Bataille bataille){
        this.getBatailles().insertOne(bataille);
    }

    public void ajoutJoueur(Joueur joueur){
        this.getJoueurs().insertOne(joueur);
    }


    public void ajoutBatimentsCivils(String pseudo,BatimentCivil civil){
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo", pseudo);
        Bson updat = Updates.push("listeDesBatimentsCivils", civil);
        BaseMongo.getBase().getJoueurs().findOneAndUpdate(filter, updat, options);
    }

    public void ajoutPointsJoueur(String pseudo,int nombrePoint){
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo", pseudo);
        Bson upda = Updates.push("nombreDePoints", nombrePoint);
        BaseMongo.getBase().getJoueurs().findOneAndUpdate(filter, upda, options);
    }
    
    public void ajoutCarteEtagesJoueur(String pseudo, Carte carteEtage){
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo",pseudo);
        Bson update = Updates.push("joueurs.cartesEtages",carteEtage);
        BaseMongo.getBase().getJoueurs().findOneAndUpdate(filter,update, options);
    }

    public void ajoutBouclierJoueur(String pseudo, int nombreBouclier){
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo",pseudo);
        Bson update = Updates.push("nombreDeBoucliers", nombreBouclier);
        BaseMongo.getBase().getJoueurs().findOneAndUpdate(filter, update, options);
    }

    public List<Piece> getPieceList(){
        MongoCollection<Piece> mongoCollection = mongoDatabase.getCollection("pieces",Piece.class);
        List<Piece> pieces = new ArrayList<>();
        mongoCollection.find().forEach(pieces::add);
        return pieces;
    }

    public void modifiePieceJoueur(String pseudo,Piece piece){
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo",pseudo);
        Bson update = Updates.push("uneListeDePieces", piece);
        BaseMongo.getBase().getJoueurs().findOneAndUpdate(filter, update, options);
    }

    public void modifieNombrePiecesJoueur(String pseudo,int nb){
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo",pseudo);
        Bson update = Updates.push("nombrePieces", nb);
        BaseMongo.getBase().getJoueurs().findOneAndUpdate(filter, update, options);
    }

    public void ajoutPointsBataille(String pseudo,int point){
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo",pseudo);
        Bson update = Updates.push("listePointsBataille", point);
        BaseMongo.getBase().getJoueurs().findOneAndUpdate(filter, update, options);
    }

    public void ajoutPointsMerveille(String pseudo,int point){
        BaseMongo.getBase().getJoueurs().updateOne(Filters.eq("pseudo",pseudo),combine(set("pointMerveille",point)));
    }

    public void ajoutPointsGuilde(String pseudo, int point){
        BaseMongo.getBase().getJoueurs().updateOne(Filters.eq("pseudo",pseudo),combine(set("pointsVictoireGuilde",point)));
    }

    public void ajoutPointsCommerce(String pseudo, int point){
        BaseMongo.getBase().getJoueurs().updateOne(Filters.eq("pseudo",pseudo),combine(set("pointsVictoireCommerce",point)));
    }

    public Piece getPiece(String type){
        MongoCollection<Piece> piece = mongoDatabase.getCollection("pieces",Piece.class);
        return piece.find(Filters.eq("type",type)).first();
    }

    public Joueur getJoueur(String pseudo){
        return this.getJoueurs().find(Filters.eq("pseudo",pseudo)).first();
    }


    public static BaseMongo getBase() {
        return base;
    }

    public List<Joueur> getJoueurList() {
        return joueurList;
    }

    public MongoCollection<Joueur> getJoueurs() {
        return joueurs;
    }


    public MongoCollection<Bataille> getBatailles() {
        return batailles;
    }

    public MongoCollection<Tour> getTours() {
        return tours;
    }

    public List<Tour> getTourList() {
        return tourList;
    }


    public List<Bataille> getBatailleList() {
        return batailleList;
    }

    public MongoCollection<Partie> getJeu() {
        return jeu;
    }

    public List<Partie> getPartieList() {
        return partieList;
    }
}
