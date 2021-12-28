package modele.enums;

public enum TypeJeton {
    VICTOIRE("victoire"),DEFAITE("defaite");

    private String typeJeton;

    TypeJeton(String typeJeton) {
        this.typeJeton = typeJeton;
    }

    public String getTypeJeton() {
        return typeJeton;
    }
}
