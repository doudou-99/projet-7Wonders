package modeles;

import modeles.interfaces.Score;

public class ScoreImpl implements Score {
    private GestionTour gestionTour;
    private Joueur joueur1;
    private Joueur joueur2;
    private Joueur joueur3;
    private Joueur joueur4;
    private int scoreJoueur1;
    private int scoreJoueur2;
    private int scoreJoueur3;
    private int scoreJoueur4;

    private String choixJoueur1;
    private String choixJoueur2;
    private String choixJoueur3;
    private String choixJoueur4;

    public ScoreImpl(GestionTour tour, Joueur joueur1, Joueur joueur2, Joueur joueur3, Joueur joueur4, String choixJoueur1, String choixJoueur2, String choixJoueur3, String choixJoueur4) {
        this.gestionTour = tour;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.joueur3 = joueur3;
        this.joueur4 = joueur4;
        this.scoreJoueur1=joueur1.getNombreDePoints();
        this.scoreJoueur2=joueur2.getNombreDePoints();
        this.scoreJoueur3=joueur3.getNombreDePoints();
        this.scoreJoueur4=joueur4.getNombreDePoints();
        this.choixJoueur1 = choixJoueur1;
        this.choixJoueur2 = choixJoueur2;
        this.choixJoueur3 = choixJoueur3;
        this.choixJoueur4 = choixJoueur4;
    }


    @Override
    public int getScoreJoueur1() {
        return this.scoreJoueur1;
    }

    @Override
    public int getScoreJoueur2() {
        return this.scoreJoueur2;
    }

    @Override
    public int getScoreJoueur3() {
        return this.scoreJoueur3;
    }

    @Override
    public int getScoreJoueur4() {
        return this.scoreJoueur4;
    }

    @Override
    public Joueur getJoueur1() {
        return this.joueur1;
    }

    @Override
    public Joueur getJoueur2() {
        return this.joueur2;
    }

    @Override
    public Joueur getJoueur3() {
        return this.joueur3;
    }

    @Override
    public Joueur getJoueur4() {
        return this.joueur4;
    }

    @Override
    public String getChoixJoueur1() {
        return this.choixJoueur1;
    }

    @Override
    public String getChoixJoueur2() {
        return this.choixJoueur2;
    }

    @Override
    public String getChoixJoueur3() {
        return this.choixJoueur3;
    }

    @Override
    public String getChoixJoueur4() {
        return this.choixJoueur4;
    }


    @Override
    public int getNombreTourEnCours() {
        return this.gestionTour.getTour().getNombreTourEnCours();
    }
}
