package modele;


import modele.exceptions.RessourceVoisinInsuffisantException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionCommerce {
    private Map<String,Joueur> joueurCommerce;
    private static int coutPieces = 2;
    private Map<String, Integer> nombreJoueur;
    private Tour tour;

    public GestionCommerce(Tour tour, Joueur joueur, List<Joueur> joueurList){
        this.joueurCommerce=new HashMap<>();
        this.joueurCommerce.put(joueur.getPseudo(),joueur);
        this.nombreJoueur.put(joueur.getPseudo(),0);
        for (int i=1;i<joueurList.size();i++) {
            this.joueurCommerce.put(joueurList.get(i).getPseudo(),joueurList.get(i));
            this.nombreJoueur.put(joueurList.get(i).getPseudo(),i);
        }
        this.tour=tour;
    }

    /*public Carte acheterRessource(String joueur,String ressource,) {
        if(joueurCommerce.containsKey(joueur) && nombreJoueur.containsKey(joueur)) {
            switch (nombreJoueur.get(joueur)){
                case 0:
                    if ()
        }
    }



    public void faireAffaire(String joueur, String choixCite) throws RessourceVoisinInsuffisantException, RessourceInexistanteException {
        if (tour.getNombreTourEnCours() <= tour.getAge().getNombreTour()) {
            if (joueurCommerce.containsKey(joueur) && nombreJoueur.containsKey(joueur)) {
                switch (nombreJoueur.get(joueur)){
                    case 0:
                        for(Joueur j: joueurCommerce.values()){
                            if (j.verifEffetCarteCite(choixCite) && choixCite.equals("cite gauche")) {
                                Effet effet = j.effetCarteCite(choixCite);
                                if (effet.getCapacite().equals("piece")){
                                    switch(effet.getCapaciteSup()){
                                        case "matières premières":
                                            if(nombreJoueur.get(j.getPseudo())==nombreJoueur.size()-1){
                                                if (.getType().equals("Matières premières")){
                                                    for (Effet benef:c.getEffet()) {
                                                        if(j.getGestionCapacite().existeRessource(benef.getCapacite())){
                                                            joueurCommerce.get(joueur).getGestionCapacite().ajoutMatierePremiere(benef.getCapacite(), benef.getNombre());
                                                        }else{
                                                            throw new RessourceVoisinInsuffisantException();
                                                        }
                                                    }
                                                }
                                            }

                                        case "marron":

                                        case "gris":

                                        case "etage":
                                    }
                                }
                                    }
                                    if (e.getChoix().contains(choixCite) && choixCite.equals("cite")){

                                    }
                                    if (e.getChoix().contains(choixCite) && choixCite.equals("cite droite")){

                                    }
                                }
                            }
                        }

                }
            }
        }
    }*/
}
