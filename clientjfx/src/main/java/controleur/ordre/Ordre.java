package controleur.ordre;

public class Ordre {
    public enum OrdreType{ACCUEIL,NOUVEAU_JOUEUR,NOUVEAU_PLATEAU,PAGE_NOMBRE,NOMBRE_JOUEURS,JOUEUR,CONNEXION,CHOIX_PLATEAU,NOUVELLE_PARTIE,REPRENDRE_PARTIE,SAUVER_PARTIE,ARRETER_PARTIE
    ,AIDE,REJOINDRE_PARTIE, ERREUR_TICKET_INVALIDE,ERREUR_PARTIE_PLEINE,ERREUR_TICKET_PERIME,RETOUR, JOUER_PARTIE;
    }

    private OrdreType type;

    public Ordre(OrdreType type) {
        this.type = type;
    }

    public OrdreType getType() {
        return type;
    }
}
