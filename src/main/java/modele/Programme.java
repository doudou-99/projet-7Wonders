package modele;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import modele.dao.BaseMongo;
import org.bson.conversions.Bson;

import java.util.*;

public class Programme {
    public static void main(String[] args) {
        BaseMongo baseMongo=BaseMongo.getBase();
        System.out.println(baseMongo.getPlateauList());
        System.out.println(baseMongo.getAges());
        System.out.println(baseMongo.getCartes());
        System.out.println(baseMongo.getCartesAges("age1"));
        //System.out.println(baseMongo.getJoueurList());
        Defausse defausse = new Defausse();

        PaquetCarte paquetCarte = new PaquetCarte(BaseMongo.getBase().getAges().get(0));
        Collections.shuffle((List<?>) paquetCarte.distribuire(4),new Random(1));
        System.out.println(paquetCarte.distribuire(4).size()+","+paquetCarte.distribuire(4));
        Random random = new Random();
        List<BatimentCivil> batimentCivils = new ArrayList<>();
        List<Piece> pieces = new ArrayList<>();
        Joueur joueur = new Joueur("momo","ndoye","mo","18","momndoye");
        joueur.cartesEnPossession(paquetCarte);
        joueur.setMerveilles(BaseMongo.getBase().getPlateauList().get(0).getId());
        joueur.setNombreBoucliers(0);
        joueur.setNombrePiecesArgent(0);
        joueur.setNombrePiecesOr(0);
        joueur.setBatimentCivilList(batimentCivils);
        joueur.setListePieces(pieces);
        joueur.setDefausse(defausse);
        List<Jeton> jetons =new ArrayList<Jeton>();
        joueur.setJetons(jetons);
        //BaseMongo.getBase().getJoueurs().insertOne(joueur);
        System.out.println(joueur.getListeCartes().size()+","+joueur.getListeCartes());
        System.out.println(BaseMongo.getBase().getJetons());

        List<BatimentCivil> batimentCivil = new ArrayList<>();
        List<Piece> piece = new ArrayList<>();
        Joueur joueu = new Joueur("fatou","mbaye","afaf","16","faf56");
        joueu.cartesEnPossession(paquetCarte);
        joueu.setMerveilles(BaseMongo.getBase().getPlateauList().get(1).getId());
        joueu.setBatimentCivilList(batimentCivils);
        joueu.setListePieces(pieces);
        joueu.setDefausse(defausse);
        List<Jeton> jeton =new ArrayList<Jeton>();
        joueu.setJetons(jeton);

        Tour tour=new Tour();
        tour.setNombreTourEnCours(6);
        tour.setAge(BaseMongo.getBase().getAges().get(0));
        Map<String, List<Carte>> cartes = new HashMap<>();
        cartes.put(joueu.getPseudo(), (List<Carte>) joueu.cartesEnPossession(paquetCarte));
        tour.setCartesJouees(cartes);
        //BaseMongo.getBase().getTours().insertOne(tour);
        System.out.println(BaseMongo.getBase().getTourList());

        Bataille bataille = new Bataille();
        bataille.setNombreDePoints(40);
        bataille.setAge(BaseMongo.getBase().getAges().get(0));
        Collection<String> jou=new ArrayList<>();
        jou.add(joueu.getPseudo());
        bataille.setNomDuVaincu(jou);
        bataille.setNomDuVainqueur(joueur.getPseudo());
        //BaseMongo.getBase().getBatailles().insertOne(bataille);

        System.out.println(BaseMongo.getBase().getBatailleList());

        Partie partie = new Partie(joueur);
        List<Joueur> joueuse=new ArrayList<>();
        joueuse.add(joueu);
        partie.setParticipants(joueuse);
        //BaseMongo.getBase().getJeu().insertOne(partie);
        System.out.println(BaseMongo.getBase().getPartieList());
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Bson filter = Filters.eq("pseudo",joueur.getPseudo());
        Bson update =Updates.push("defausse.listeDesCartesDefausse",joueur.getListeCartes().get(0));
        BaseMongo.getBase().getJoueurs().findOneAndUpdate(filter,update, options);

    }
}
