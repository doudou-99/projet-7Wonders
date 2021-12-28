package modele.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import modele.*;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
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

        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().register("modele").register(classModel).register(classMode)
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
