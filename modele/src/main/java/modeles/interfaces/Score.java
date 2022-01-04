package modeles.interfaces;

import modeles.Joueur;

public interface Score {
    int getScoreJoueur1();

    int getScoreJoueur2();

    int getScoreJoueur3();

    int getScoreJoueur4();

    Joueur getJoueur1();

    Joueur getJoueur2();

    Joueur getJoueur3();

    Joueur getJoueur4();

    String getChoixJoueur1();

    String getChoixJoueur2();

    String getChoixJoueur3();

    String getChoixJoueur4();

    int getNombreTourEnCours();
}
