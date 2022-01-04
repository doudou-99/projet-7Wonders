package modeles;

public class Compas {
    private static int id;
    private int nb;

    public Compas() {
        this.nb = id++;
    }

    public int getNb() {
        return nb;
    }
}
