package modeles.enums;

public enum MatierePremiere {
    PIERRE("pierre"),ARGILE("argile"),MINERAI("minerai"),BOIS("bois");

    private String matiere;
    MatierePremiere(String matiere) {
        this.matiere=matiere;
    }

    public String getMatiere() {
        return matiere;
    }
}
