package modeles.enums;

public enum TypeCarte {
    BATIMENTS_CIVILS("Bâtiments civils"),
    BATIMENTS_MILITAIRES("Bâtiments militaires"),
    MATIERESPREMIERES("Matières premières"),
    PRODUITS_MANUFACTURES("Produits manufacturés"),
    BATIMENTS_COMMERCIAUX("Bâtiments commerciaux"),
    BATIMENTS_SCIENTIFIQUES("Bâtiments scientifiques"),
    GUILDE("Guilde");

    private String s;
    TypeCarte(String s) {
        this.s=s;
    }

    public String getS() {
        return s;
    }
}
