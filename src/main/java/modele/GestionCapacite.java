package modele;

import modele.dao.BaseMongo;
import modele.exceptions.RessourceInsuffisanteException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        this.nombreCompas=idCompas;
        this.nombreBois=idBois;
        this.nombreArgile=idArgile;
        this.nombrePierre=idPierre;
        this.nombreMinerai=idMinerai;
        this.nombrePapyrus=idPapyrus;
        this.nombreVerre=idVerre;
        this.nombreTissu=idTissu;
        this.nombreRoue=idRoue;
        this.nombreTablette=idTablette;
    }

    /**
     * Cette methode vérifie si la ressource pour construire un bâtiment ou une carte est suffisante
     */
    public boolean ressourceSuffisante(Cout c){
            boolean estSuffisant=false;
            switch (c.getRessource()) {
                case "argile":
                    if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours() > 0) {
                        if (nombreArgile >= c.getNombreUnite()) {
                            estSuffisant = true;
                        }
                    }
                    break;
                case "pierre":
                    if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours() > 0) {
                        if (nombrePierre >= c.getNombreUnite()) {
                            estSuffisant = true;
                        }
                    }
                    break;
                case "minerai":
                    if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours() > 0) {
                        if (nombreMinerai >= c.getNombreUnite()) {
                            estSuffisant= true;
                        }

                    }
                    break;
                case "bois":
                    if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours() > 0) {
                        if (nombreBois >= c.getNombreUnite()) {
                            estSuffisant = true;
                        }
                    }
                    break;
                case "tissu":
                    if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours() > 0) {
                        if (nombreTissu >= c.getNombreUnite()) {
                            estSuffisant = true;
                        }

                    }
                    break;
                case "verre":
                    if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours() > 0) {
                        if (nombreVerre >= c.getNombreUnite()) {
                            estSuffisant = true;
                        }
                    }
                    break;
                case "papyrus":
                    if (tour.getNombreTourEnCours() <= 6 & tour.getNombreTourEnCours() > 0) {
                        if (nombrePapyrus >= c.getNombreUnite()) {
                            estSuffisant = true;
                        }
                    }
                    break;
            }

        return estSuffisant;
    }

    /**
     * Cette methode vérifie si les ressources pour construire un bâtiment ou une carte sont suffisantes
     */
    public boolean verifierCout(Collection<Cout> couts){
        boolean suffisants = true;
        for (Cout c:couts) {
                suffisants= suffisants && ressourceSuffisante(c);
        }
        return suffisants;

    }

    public boolean existeRessource(String ressource){
        boolean existe = false;
        switch (ressource){
            case "pierre":
                existe= nombrePierre > 0;
            break;
            case "argile":
                existe= nombreArgile>0;
                break;
            case "minerai":
                existe=nombreMinerai>0;
                break;
            case "bois":
                existe=nombreBois>0;
                break;
            case "papyrus":
                existe=nombrePapyrus>0;
                break;
            case "verre":
                existe=nombreVerre>0;
                break;
            case "tissu":
                existe=nombreTissu>0;
                break;
        }
        return existe;
    }

    public boolean verifGratuits(Cout couts){
        return couts.getRessource().equals("") && (couts.getNombreUnite() == 0);
    }

    /**
     * Cette methode incrémente le nombre de bâtiment scientifique en fonction de la ressource ici un symbole scientifique
     */
    public void ajoutSymbolesScientifiques(String ressource,int nombre){
        switch (ressource){
            case "compas":
                nombreCompas+=nombre;
                break;
            case "roue":
                nombreRoue+=nombre;
                break;
            case "tablette":
                nombreTablette+=nombre;
                break;
        }
    }

    /**
     * Cette méthode incrémente les matières premières et leur nombre en fonction des cartes jouées
     * @param ressource
     * @param nombre
     */
    public void ajoutMatierePremiere(String ressource,int nombre){
        switch (ressource){
            case "pierre":
                nombrePierre+=nombre;
                break;
            case "argile":
                nombreArgile+=nombre;
                break;
            case "minerai":
                nombreMinerai+=nombre;
                break;
            case "bois":
                nombreBois+=nombre;
                break;
        }
    }

    /**
     * Cette méthode incremente les produits manufacturés et leur nombre en fonction des cartes jouées par le joueur
     * @param ressource
     * @param nombre
     */
    public void ajoutProduitsManufactures(String ressource,int nombre){
        switch (ressource) {
            case "papyrus":
                nombrePapyrus += nombre;
                break;
            case "verre":
                nombreVerre += nombre;
                break;
            case "tissu":
                nombreTissu += nombre;
                break;
        }
    }


    /**
     * Cette méthode permet d'ajouter pour chaque tour la capacité du plateau
     */
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

    /**
     *Cette méthode permet d'augmenter la ressource quand on construit un bâtiment ou une carte
     */
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

    /**
     *Cette méthode permet de diminuer la ressource quand on construit un bâtiment ou une carte
     */
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
