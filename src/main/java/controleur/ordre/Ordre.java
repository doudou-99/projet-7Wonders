package controleur.ordre;

public class Ordre {
    public enum OrdreType{ACCUEIL,NOUVEAU_JOUEUR,NOUVEAU_PLATEAU,NOMBRE_JOUEURS,
        JOUEUR,CONNEXION,CHOIX_PLATEAU,NOUVELLE_PARTIE,REPRENDRE_PARTIE,SAUVER_PARTIE,ARRETER_PARTIE
    ,AIDE}

    private OrdreType type;

    public Ordre(OrdreType type) {
        this.type = type;
    }

    public OrdreType getType() {
        return type;
    }
}
