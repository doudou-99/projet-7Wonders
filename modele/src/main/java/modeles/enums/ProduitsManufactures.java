package modeles.enums;

public enum ProduitsManufactures {
    VERRE("verre"),TISSU("tissu"),PAPYRUS("papyrus");

    private String produit;
    ProduitsManufactures(String produit) {
        this.produit=produit;
    }

    public String getProduit() {
        return produit;
    }
}
