package modele;

public class Tablette {
    private static int id;
    private int nb;

    public Tablette() {
        this.nb = id++;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }
}
