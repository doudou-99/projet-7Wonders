package modeles;

import modeles.dao.BaseMongo;
import modeles.facade.FacadeWondersImpl;
import modeles.interfaces.FacadeWonders;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Programme {
    public static void main(String[] args) {
        System.out.println(new File("collections/ages.json").toPath().toAbsolutePath());
        try {
            System.out.println(Files.readString(Path.of("modele/src/main/java/modeles/dao/collections/ages.json").toAbsolutePath(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BaseMongo baseMongo=BaseMongo.getBase();
        Joueur joueur = new Joueur("ndoye","momar","momo","22","momo99");
        Partie partie = new Partie(joueur,4);
        FacadeWonders facadeWonders = new FacadeWondersImpl();
        Joueur joueus = new Joueur("ndoye","fatou","fatou","18","fatou2007");
        Joueur j = new Joueur("titi","toto","tutu","10","titi10");
        Joueur joueur1 = new Joueur("ndoye","babacar","baba","13","baba2008");
        facadeWonders.ajoutJoueur(j);
        List<Joueur> joueuse=new ArrayList<>();
        joueuse.add(joueus);
        joueuse.add(j);
        joueuse.add(joueur1);
        PartieGestion partieGestion = new PartieGestion(joueuse);
        partie.setPartieGestionCourant(partieGestion);
        //BaseMongo.getBase().getJeu().insertOne(partie);
        GestionTour gestion = new GestionTour(BaseMongo.getBase().getAges().get(0));

        partie.setParticipants(joueuse);
        //List<Carte> cartesMain = facadeWonders.donnerCarteJoueur(j);
        System.out.println(partieGestion.getJoueurs().containsValue(j));

        partie.debutJeu(j,"Le Colosse de Rhodes");
        System.out.println(partie.getNombrePartie());
        System.out.println(j.getListeCartes());
        System.out.println(j.getMerveilles());




    }
}
