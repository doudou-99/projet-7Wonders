package modeles;

public class Roue {
    private static int id;
    private int nb;

    public Roue() {
        this.nb = id++;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }
}
