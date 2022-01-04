package modeles;

public class CarteMerveille {
    private String id;
    private Plateau plateau;


    public CarteMerveille(String id, Plateau plateau) {
        this.id = id;
        this.plateau = plateau;
    }

    public String getId() {
        return id;
    }

    public Plateau getPlateau() {
        return plateau;
    }
}
