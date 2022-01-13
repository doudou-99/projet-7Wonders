package modeles;

import modeles.dao.BaseMongo;
import modeles.facade.FacadeWondersImpl;
import modeles.interfaces.FacadeWonders;

import java.util.*;

public class Programme {
    public static void main(String[] args) {
        BaseMongo baseMongo=BaseMongo.getBase();
        Defausse defausse = new Defausse();

        PaquetCarte paquetCarte = new PaquetCarte(BaseMongo.getBase().getAges().get(0));
        System.out.println(paquetCarte.distribuire(4).size()+","+paquetCarte.getTour().getTour().getAge());
        PaquetCarte paquet = new PaquetCarte(BaseMongo.getBase().getAges().get(1));
        System.out.println(paquet.distribuire(4).size()+", "+paquet.getTour().getTour().getAge());
        PaquetCarte paquetCarte1 = new PaquetCarte(BaseMongo.getBase().getAges().get(2));
        System.out.println(paquetCarte1.distribuire(4).size()+", "+paquetCarte1.getTour().getTour().getAge());
        Random random = new Random();
        List<BatimentCivil> batimentCivils = new ArrayList<>();
        List<Piece> pieces = new ArrayList<>();
        Joueur joueur = new Joueur("momo","ndoye","mo","18","momndoye");
        joueur.cartesEnPossession(paquetCarte);
        joueur.setMerveilles(BaseMongo.getBase().getPlateauList().get(0).getId());
        joueur.setBatimentCivilList(batimentCivils);
        joueur.setListePieces(pieces);
        joueur.setDefausse(defausse);
        List<Jeton> jetons =new ArrayList<>();
        joueur.setJetons(jetons);
        BaseMongo.getBase().getJoueurs().insertOne(joueur);
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

        tour.setAge(BaseMongo.getBase().getAges().get(0));
        Map<String, List<Carte>> cartes = new HashMap<>();
        cartes.put(joueu.getPseudo(), (List<Carte>) joueu.cartesEnPossession(paquetCarte));
        tour.setCartesJouees(cartes);
        BaseMongo.getBase().getTours().insertOne(tour);
        System.out.println(BaseMongo.getBase().getTourList());

        Bataille bataille = new Bataille();
        bataille.setAge(BaseMongo.getBase().getAges().get(0));
        List<String> jou=new ArrayList<>();
        Joueur joueur1 = new Joueur("fama","ndoye","famas","11","fama2010");
        Joueur joueur2 = new Joueur("baba","ndoye","babs","13","babandoye2008");
        BaseMongo.getBase().ajoutJoueur(joueur1);
        BaseMongo.getBase().ajoutJoueur(joueur2);
        GestionBataille gestionBataille = new GestionBataille();
        gestionBataille.vainqueurBataille(joueur.getPseudo(),tour.getAge());
        bataille.setNomDuVainqueur(joueur.getPseudo());
        bataille.setNomDuVaincu(jou);
        BaseMongo.getBase().getBatailles().insertOne(bataille);

        System.out.println(BaseMongo.getBase().getBatailleList());

        Partie partie = new Partie(joueur);
        List<Joueur> joueuse=new ArrayList<>();
        joueuse.add(joueu);
        partie.setParticipants(joueuse);

        PartieGestion partieGestion = new PartieGestion(joueuse);
        partie.setPartieGestionCourant(partieGestion);
        //BaseMongo.getBase().getJeu().insertOne(partie);
        GestionTour gestion = new GestionTour(BaseMongo.getBase().getAges().get(0));
        FacadeWonders facadeWonders = new FacadeWondersImpl();
        Joueur j = new Joueur("titi","toto","tutu","10","titi10");
        facadeWonders.ajoutJoueur(j);
        System.out.println(partie);




    }
}
