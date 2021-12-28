package modele;

import modele.dao.BaseMongo;

public class GestionCapacite {
    private static int idCompas;
    private static int idBois;
    private static int idArgile;
    private static int idPierre;
    private static int idMinerai;
    private static int idVerre;
    private static int idPapyrus;
    private static int idTissu;
    private static int idRoue;
    private static int idTablette;
    private int nombreCompas;
    private int nombreBois;
    private int nombreArgile;
    private int nombrePierre;
    private int nombreMinerai;
    private int nombreVerre;
    private int nombrePapyrus;
    private int nombreTissu;
    private int nombreRoue;
    private int nombreTablette;
    private Tour tour;

    public GestionCapacite(Tour tour){
        this.tour=tour;
    }




    public void gererCapacitePlateau(){
        BaseMongo b = BaseMongo.getBase();
        for (Plateau p: b.getPlateauList()) {
            switch (p.getCapacite()){
                case "argile":
                    if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                        nombreArgile = idArgile + 1;
                    }
                    break;
                case "pierre":
                    if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                        nombrePierre = idPierre + 1;
                    }
                    break;
                case "minerai":
                    if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                        nombreMinerai = idMinerai + 1;
                    }
                    break;
                case "bois":
                    if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                        nombreBois = idBois + 1;
                    }
                    break;
                case "tissu":
                    if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                        nombreTissu = idTissu + 1;
                    }
                    break;
                case "verre":
                    if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                        nombreVerre = idVerre + 1;
                    }
                    break;
                case "papyrus":
                    if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                        nombrePapyrus = idPapyrus + 1;
                    }
                    break;
            }
        }
    }

    public void augmenterRessource(String ressource,int nombreUnite){
        switch (ressource){
            case "argile":
                if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                    nombreArgile = idArgile + nombreUnite;
                }
                break;
            case "pierre":
                if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                    nombrePierre = idPierre + nombreUnite;
                }
                break;
            case "minerai":
                if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                    nombreMinerai = idMinerai + nombreUnite;
                }
                break;
            case "bois":
                if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                    nombreBois = idBois + nombreUnite;
                }
                break;
            case "tissu":
                if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                    nombreTissu = idTissu + nombreUnite;
                }
                break;
            case "verre":
                if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                    nombreVerre = idVerre + nombreUnite;
                }
                break;
            case "papyrus":
                if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                    nombrePapyrus = idPapyrus + nombreUnite;
                }
                break;
        }
    }

    public void diminuer(String ressource,int nombreUnite){
        switch (ressource){
            case "argile":
                if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                    nombreArgile = idArgile - nombreUnite;
                }
                break;
            case "pierre":
                if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                    nombrePierre = idPierre - nombreUnite;
                }
                break;
            case "minerai":
                if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                    nombreMinerai = idMinerai - nombreUnite;
                }
                break;
            case "bois":
                if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                    nombreBois = idBois - nombreUnite;
                }
                break;
            case "tissu":
                if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                    nombreTissu = idTissu + nombreUnite;
                }
                break;
            case "verre":
                if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                    nombreVerre = idVerre - nombreUnite;
                }
                break;
            case "papyrus":
                if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours()>0 ) {
                    nombrePapyrus = idPapyrus - nombreUnite;
                }
                break;
        }
    }


    public int getNombreCompas() {
        return nombreCompas;
    }

    public void setNombreCompas(int nombreCompas) {
        this.nombreCompas = nombreCompas;
    }

    public int getNombreBois() {
        return nombreBois;
    }

    public void setNombreBois(int nombreBois) {
        this.nombreBois = nombreBois;
    }

    public int getNombreArgile() {
        return nombreArgile;
    }

    public void setNombreArgile(int nombreArgile) {
        this.nombreArgile = nombreArgile;
    }

    public int getNombrePierre() {
        return nombrePierre;
    }

    public void setNombrePierre(int nombrePierre) {
        this.nombrePierre = nombrePierre;
    }

    public int getNombreMinerai() {
        return nombreMinerai;
    }

    public void setNombreMinerai(int nombreMinerai) {
        this.nombreMinerai = nombreMinerai;
    }

    public int getNombreVerre() {
        return nombreVerre;
    }

    public void setNombreVerre(int nombreVerre) {
        this.nombreVerre = nombreVerre;
    }

    public int getNombrePapyrus() {
        return nombrePapyrus;
    }

    public void setNombrePapyrus(int nombrePapyrus) {
        this.nombrePapyrus = nombrePapyrus;
    }

    public int getNombreTissu() {
        return nombreTissu;
    }

    public void setNombreTissu(int nombreTissu) {
        this.nombreTissu = nombreTissu;
    }

    public int getNombreRoue() {
        return nombreRoue;
    }

    public void setNombreRoue(int nombreRoue) {
        this.nombreRoue = nombreRoue;
    }

    public int getNombreTablette() {
        return nombreTablette;
    }

    public void setNombreTablette(int nombreTablette) {
        this.nombreTablette = nombreTablette;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
