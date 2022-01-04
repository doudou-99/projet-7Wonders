package modeles;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modeles.dao.BaseMongo;
import modeles.exceptions.*;

public class GestionCommerce {
    private Map<Integer,Joueur> joueurCommerce;
    private static int coutPieces = 2;
    private Map<String, Integer> nombreJoueur;
    private Tour tour;

    public GestionCommerce(Tour tour){
        this.joueurCommerce=new HashMap<>();
        this.nombreJoueur=new HashMap<>();
        for (int i = 0; i< BaseMongo.getBase().getJoueurList().size(); i++) {
            this.joueurCommerce.put(i,BaseMongo.getBase().getJoueurList().get(i));
            this.nombreJoueur.put(BaseMongo.getBase().getJoueurList().get(i).getPseudo(),i);
        }
        this.tour=tour;
    }

    public Joueur voisinDroite(String joueur) {
        Joueur joueur1 = BaseMongo.getBase().getJoueur(joueur);
        int voisin = 0;
        if (joueurCommerce.containsValue(joueur1) && nombreJoueur.containsKey(joueur)) {
            switch (nombreJoueur.get(joueur)) {
                case 0:
                    voisin = 1;
                    break;
                case 1:
                    voisin = 2;
                    break;
                case 2:
                    voisin = 3;
                    break;
                case 3:
                    voisin = 0;
                    break;
            }
        }
        return joueurCommerce.get(voisin);
    }

    public Joueur voisinGauche(String joueur) {
        Joueur joueur1 = BaseMongo.getBase().getJoueur(joueur);
        int voisin = 0;
        if (joueurCommerce.containsValue(joueur1) && nombreJoueur.containsKey(joueur)) {
            switch (nombreJoueur.get(joueur)) {
                case 0:
                    voisin = 3;
                    break;
                case 1:
                    voisin = 0;
                    break;
                case 2:
                    voisin = 1;
                    break;
                case 3:
                    voisin = 2;
                    break;
            }
        }
        return joueurCommerce.get(voisin);
    }

    public Carte acheterRessourceVoisin(String joueur,String nomCarte) throws PieceInsuffisanteException {
        Joueur joueur1 = BaseMongo.getBase().getJoueur(joueur);
        Carte carte = BaseMongo.getBase().getCartesNom(nomCarte);
        if (joueurCommerce.containsValue(joueur1) && nombreJoueur.containsKey(joueur)) {
            for (Cout c : carte.getCout()) {
                if (!joueur1.getGestionCapacite().verifierCout(carte.getCout()) && joueur1.pieceSuffisante(coutPieces)) {

                    if (voisinDroite(joueur).getGestionCapacite().existeRessource(c.getRessource()) && voisinDroite(joueur).getGestionCapacite().ressourceSuffisante(c)) {
                        joueur1.soustrairePiece(2);
                        joueur1.getGestionCapacite().augmenterRessource(c.getRessource(), 1);
                        if (!joueur1.getGestionCapacite().ressourceSuffisante(c) && !joueur1.pieceSuffisante(coutPieces)) {
                            while (!joueur1.getGestionCapacite().ressourceSuffisante(c) || voisinDroite(joueur).getGestionCapacite().ressourceSuffisante(c) && joueur1.pieceSuffisante(2)) {
                                joueur1.soustrairePiece(2);
                                joueur1.getGestionCapacite().augmenterRessource(c.getRessource(), 1);
                            }
                        } else {
                            if (joueur1.getGestionCapacite().ressourceSuffisante(c)) {
                                joueur1.ajoutCite(carte);
                            }
                            throw new PieceInsuffisanteException();
                        }
                    }
                } else {
                    if (voisinGauche(joueur).getGestionCapacite().existeRessource(c.getRessource()) && voisinGauche(joueur).getGestionCapacite().ressourceSuffisante(c)) {
                        joueur1.soustrairePiece(GestionCommerce.coutPieces);
                        joueur1.getGestionCapacite().augmenterRessource(c.getRessource(), 1);
                        if (!joueur1.getGestionCapacite().ressourceSuffisante(c) && !joueur1.pieceSuffisante(GestionCommerce.coutPieces)) {
                            while (!joueur1.getGestionCapacite().ressourceSuffisante(c) || voisinGauche(joueur).getGestionCapacite().ressourceSuffisante(c) && joueur1.pieceSuffisante(2)) {
                                joueur1.soustrairePiece(2);
                                joueur1.getGestionCapacite().augmenterRessource(c.getRessource(), 1);
                            }
                        } else {
                            if (joueur1.getGestionCapacite().ressourceSuffisante(c)) {
                                joueur1.ajoutCite(carte);
                            }
                            throw new PieceInsuffisanteException();
                        }

                    }
                }
            }
        }
        return carte;
    }


    public void reduction(String joueur,String choixCite) throws RessourceVoisinInsuffisantException {
        Joueur joueur1=BaseMongo.getBase().getJoueur(joueur);
        if (joueurCommerce.containsValue(joueur1) && nombreJoueur.containsKey(joueur)) {
            for (Carte c: joueur1.getCite().getCartes()) {
                if (c.getType().equals("Bâtiments commerciaux")) {
                    for (Effet e : c.getEffet()) {
                        if (e.getCapacite().equals("reduction de piece")) {
                            if (e.getChoix().contains(choixCite) && choixCite.equals("cite gauche")){
                                if(e.getCapaciteSup().equals("matières premières")) {
                                    if (voisinGauche(joueur).getGestionCapacite().avoirMatierePremiere()) {
                                        for (Carte carte : voisinGauche(joueur).getCite().getCartes()) {
                                            if (carte.getType().equals("Matières premières")) {
                                                for (Effet benef : carte.getEffet()) {
                                                    if (voisinGauche(joueur).getGestionCapacite().existeRessource(benef.getCapacite())) {
                                                        GestionCommerce.coutPieces = 1;
                                                    } else {
                                                        throw new RessourceVoisinInsuffisantException();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }else if(e.getCapaciteSup().equals("produits manufacturés")) {
                                    if (voisinGauche(joueur).getGestionCapacite().avoirProduitManufacture()) {
                                        for (Carte carte : voisinGauche(joueur).getCite().getCartes()) {
                                            if (carte.getType().equals("Produits manufacturés")) {
                                                for (Effet benef : carte.getEffet()) {
                                                    if (voisinGauche(joueur).getGestionCapacite().existeRessource(benef.getCapacite())) {
                                                        GestionCommerce.coutPieces = 1;
                                                    } else {
                                                        throw new RessourceVoisinInsuffisantException();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }else{
                                if (e.getChoix().contains(choixCite) && choixCite.equals("cite droite")){
                                    if(e.getCapaciteSup().equals("matières premières")) {
                                        if (voisinDroite(joueur).getGestionCapacite().avoirMatierePremiere()) {
                                            for (Carte carte : voisinDroite(joueur).getCite().getCartes()) {
                                                if (carte.getType().equals("Matières premières")) {
                                                    for (Effet benef : carte.getEffet()) {
                                                        if (voisinDroite(joueur).getGestionCapacite().existeRessource(benef.getCapacite())) {
                                                            joueur1.getGestionCapacite().ajoutMatierePremiere(benef.getCapacite(), benef.getNombre());
                                                            GestionCommerce.coutPieces = 1;
                                                        } else {
                                                            throw new RessourceVoisinInsuffisantException();
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }else if(e.getCapaciteSup().equals("produits manufacturés")) {
                                        if (voisinDroite(joueur).getGestionCapacite().avoirProduitManufacture()) {
                                            for (Carte carte : voisinDroite(joueur).getCite().getCartes()) {
                                                if (carte.getType().equals("Produits manufacturés")) {
                                                    for (Effet benef : carte.getEffet()) {
                                                        if (voisinDroite(joueur).getGestionCapacite().existeRessource(benef.getCapacite())) {
                                                            GestionCommerce.coutPieces = 1;
                                                        } else {
                                                            throw new RessourceVoisinInsuffisantException();
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public Carte faireAffaire(String joueur, String choixCite,String nomCarte) throws RessourceVoisinInsuffisantException, RessourceInexistanteException, PieceInsuffisanteException {
        Joueur joueur1 = BaseMongo.getBase().getJoueur(joueur);
        Carte c = BaseMongo.getBase().getCartesNom(nomCarte);
        if (joueurCommerce.containsValue(joueur1) && nombreJoueur.containsKey(joueur)) {
                if (c.getType().equals("Bâtiments commerciaux")) {
                    if (!joueur1.getGestionCapacite().verifGratuits(((List<Cout>) c.getCout()).get(0))){
                        for (Cout cout:c.getCout()){
                            if (!joueur1.getGestionCapacite().ressourceSuffisante(cout) && joueur1.pieceSuffisante(GestionCommerce.coutPieces)){
                                this.acheterRessourceVoisin(joueur,nomCarte);
                            }else{
                                joueur1.ajoutCite(c);
                            }
                        }
                    }
                    for (Effet e : c.getEffet()) {
                        if (e.avoirMemeRessource(e.getCapacite()) && e.avoirMemeRessource(choixCite) && choixCite.equals("cite gauche")) {

                            if (e.getCapacite().equals("reduction de piece")) {
                                this.reduction(joueur, choixCite);
                            } else if (e.getCapacite().equals("piece")) {
                                switch (e.getCapaciteSup()) {
                                    case "marron":
                                        int nb = 0;
                                        for (Carte carte : joueur1.getCite().getCartes()) {
                                            if (carte.getCouleur().equals("marron")) {
                                                nb += (e.getNombre());
                                            }
                                        }
                                        for (Carte carte : voisinDroite(joueur).getCite().getCartes()) {
                                            if (carte.getCouleur().equals("marron")) {
                                                nb += e.getNombre();
                                            }
                                        }
                                        for (Carte carte : voisinGauche(joueur).getCite().getCartes()) {
                                            if (carte.getCouleur().equals("marron")) {
                                                nb += e.getNombre();
                                            }
                                        }
                                        joueur1.setNombrePieces(joueur1.getNombrePieces() + nb);
                                        BaseMongo.getBase().modifieNombrePiecesJoueur(joueur1.getPseudo(), joueur1.getNombrePieces());
                                        break;
                                    case "gris":
                                        int nombre = 0;
                                        for (Carte carte : joueur1.getCite().getCartes()) {
                                            if (carte.getCouleur().equals("gris")) {
                                                nombre += (e.getNombre());
                                            }
                                        }
                                        for (Carte carte : voisinDroite(joueur).getCite().getCartes()) {
                                            if (carte.getCouleur().equals("gris")) {
                                                nombre += e.getNombre();
                                            }
                                        }
                                        for (Carte carte : voisinGauche(joueur).getCite().getCartes()) {
                                            if (carte.getCouleur().equals("gris")) {
                                                nombre += e.getNombre();
                                            }
                                        }
                                        joueur1.setNombrePieces(joueur1.getNombrePieces() + nombre);
                                        BaseMongo.getBase().modifieNombrePiecesJoueur(joueur1.getPseudo(), joueur1.getNombrePieces());
                                        break;

                                }
                            }
                        }
                        if (e.getChoix().contains(choixCite) && choixCite.equals("cite")) {
                            if (e.getCapacite().equals("reduction de piece")) {
                                this.reduction(joueur, choixCite);
                            } else if (e.getCapacite().equals("piece")) {
                                switch (e.getCapaciteSup()) {
                                    case "marron":
                                        int nb = 0;
                                        for (Carte carte : joueur1.getCite().getCartes()) {
                                            if (carte.getCouleur().equals("marron")) {
                                                nb += (e.getNombre());
                                            }
                                        }
                                        for (Carte carte : voisinDroite(joueur).getCite().getCartes()) {
                                            if (carte.getCouleur().equals("marron")) {
                                                nb += e.getNombre();
                                            }
                                        }
                                        for (Carte carte : voisinGauche(joueur).getCite().getCartes()) {
                                            if (carte.getCouleur().equals("marron")) {
                                                nb += e.getNombre();
                                            }
                                        }
                                        joueur1.setNombrePieces(joueur1.getNombrePieces() + nb);
                                        BaseMongo.getBase().modifieNombrePiecesJoueur(joueur1.getPseudo(), joueur1.getNombrePieces());
                                        break;
                                    case "gris":
                                        int nombre = 0;
                                        for (Carte carte : joueur1.getCite().getCartes()) {
                                            if (carte.getCouleur().equals("gris")) {
                                                nombre += (e.getNombre());
                                            }
                                        }
                                        for (Carte carte : voisinDroite(joueur).getCite().getCartes()) {
                                            if (carte.getCouleur().equals("gris")) {
                                                nombre += e.getNombre();
                                            }
                                        }
                                        for (Carte carte : voisinGauche(joueur).getCite().getCartes()) {
                                            if (carte.getCouleur().equals("gris")) {
                                                nombre += e.getNombre();
                                            }
                                        }
                                        joueur1.setNombrePieces(joueur1.getNombrePieces() + nombre);
                                        BaseMongo.getBase().modifieNombrePiecesJoueur(joueur1.getPseudo(), joueur1.getNombrePieces());
                                        break;

                                }
                            }
                        }
                        if (e.getChoix().contains(choixCite) && choixCite.equals("cite droite")) {
                            if (e.getCapacite().equals("reduction de piece")) {
                                this.reduction(joueur, choixCite);
                            } else if (e.getCapacite().equals("piece")) {
                                switch (e.getCapaciteSup()) {
                                    case "marron":
                                        int nb = 0;
                                        for (Carte carte : joueur1.getCite().getCartes()) {
                                            if (carte.getCouleur().equals("marron")) {
                                                nb += (e.getNombre());
                                            }
                                        }
                                        for (Carte carte : voisinDroite(joueur).getCite().getCartes()) {
                                            if (carte.getCouleur().equals("marron")) {
                                                nb += e.getNombre();
                                            }
                                        }
                                        for (Carte carte : voisinGauche(joueur).getCite().getCartes()) {
                                            if (carte.getCouleur().equals("marron")) {
                                                nb += e.getNombre();
                                            }
                                        }
                                        joueur1.setNombrePieces(joueur1.getNombrePieces() + nb);
                                        BaseMongo.getBase().modifieNombrePiecesJoueur(joueur1.getPseudo(), joueur1.getNombrePieces());
                                        break;
                                    case "gris":
                                        int nombre = 0;
                                        for (Carte carte : joueur1.getCite().getCartes()) {
                                            if (carte.getCouleur().equals("gris")) {
                                                nombre += (e.getNombre());
                                            }
                                        }
                                        for (Carte carte : voisinDroite(joueur).getCite().getCartes()) {
                                            if (carte.getCouleur().equals("gris")) {
                                                nombre += e.getNombre();
                                            }
                                        }
                                        for (Carte carte : voisinGauche(joueur).getCite().getCartes()) {
                                            if (carte.getCouleur().equals("gris")) {
                                                nombre += e.getNombre();
                                            }
                                        }
                                        joueur1.setNombrePieces(joueur1.getNombrePieces() + nombre);
                                        BaseMongo.getBase().modifieNombrePiecesJoueur(joueur1.getPseudo(), joueur1.getNombrePieces());
                                        break;

                                }
                            }

                        } else {
                            if (e.getCapacite().equals("point de victoire,piece") && e.getChoix().isEmpty()) {
                                switch (e.getCapaciteSup()) {
                                    case "marron":
                                        int nb = 0;
                                        for (Carte carte : joueur1.getCite().getCartes()) {
                                            if (carte.getCouleur().equals("marron")) {
                                                nb += (e.getNombre());
                                            }
                                        }

                                        joueur1.setNombrePieces(joueur1.getNombrePieces() + nb);
                                        joueur1.setNombreDePoints(joueur1.getNombreDePoints() + nb);
                                        BaseMongo.getBase().ajoutPointsJoueur(joueur1.getPseudo(), joueur1.getNombreDePoints());
                                        BaseMongo.getBase().modifieNombrePiecesJoueur(joueur1.getPseudo(), joueur1.getNombrePieces());
                                        break;
                                    case "gris":
                                        int nombre = 0;
                                        for (Carte carte : joueur1.getCite().getCartes()) {
                                            if (carte.getCouleur().equals("gris")) {
                                                nombre += (e.getNombre());
                                            }
                                        }

                                        joueur1.setNombrePieces(joueur1.getNombrePieces() + nombre);
                                        joueur1.setNombreDePoints(joueur1.getNombreDePoints() + nombre);
                                        BaseMongo.getBase().ajoutPointsJoueur(joueur1.getPseudo(), joueur1.getNombreDePoints());
                                        BaseMongo.getBase().modifieNombrePiecesJoueur(joueur1.getPseudo(), joueur1.getNombrePieces());
                                        break;
                                    case "jaune":
                                        int nombres = 0;
                                        for (Carte carte : joueur1.getCite().getCartes()) {
                                            if (carte.getCouleur().equals("jaune")) {
                                                nombres += (e.getNombre());
                                            }
                                        }

                                        joueur1.setNombrePieces(joueur1.getNombrePieces() + nombres);
                                        joueur1.setNombreDePoints(joueur1.getNombreDePoints() + nombres);
                                        BaseMongo.getBase().ajoutPointsJoueur(joueur1.getPseudo(), joueur1.getNombreDePoints());
                                        BaseMongo.getBase().modifieNombrePiecesJoueur(joueur1.getPseudo(), joueur1.getNombrePieces());
                                        break;

                                    case "etage":
                                        int nbs = 0;
                                        int nombrePoint = 0;

                                        if (joueur1.getCartesEtages().values().size() == 1) {
                                            nbs += joueur1.getCartesEtages().values().size() * 3;
                                            nombrePoint += joueur1.getCartesEtages().values().size();
                                        }
                                        if (joueur1.getCartesEtages().values().size() == 2) {
                                            nbs += joueur1.getCartesEtages().values().size() * 3;
                                            nombrePoint += joueur1.getCartesEtages().values().size();
                                        }
                                        if (joueur1.getCartesEtages().values().size() == 3) {
                                            nbs += joueur1.getCartesEtages().values().size() * 3;
                                            nombrePoint += joueur1.getCartesEtages().values().size();
                                        }
                                        joueur1.setNombrePieces(joueur1.getNombrePieces() + nbs);
                                        joueur1.setNombreDePoints(joueur1.getNombreDePoints() + nombrePoint);
                                        BaseMongo.getBase().ajoutPointsJoueur(joueur1.getPseudo(), joueur1.getNombreDePoints());
                                        BaseMongo.getBase().modifieNombrePiecesJoueur(joueur1.getPseudo(), joueur1.getNombrePieces());
                                        break;
                                }
                            }
                        }
                    }
                }
            }
        return c;
    }



}
