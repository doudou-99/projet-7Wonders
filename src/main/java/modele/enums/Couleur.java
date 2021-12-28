package modele.enums;

public enum Couleur {
    MARRON("marron"),
    VIOLET("violet"),
    GRIS("grise"),
    ROUGE("rouge"),
    JAUNE("jaune"),
    VERT("vert"),
    BLEU("bleu");

    private String couleur;
    Couleur(String couleur) {
        this.couleur=couleur;
    }

    public String getCouleur() {
        return couleur;
    }

    @Override
    public String toString() {
        return "Couleur{" +
                "couleur='" + couleur + '\'' +
                '}';
    }
}
