package modeles;

import java.io.Serializable;

public class Cout implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ressource;
    private int nombreUnite;

    public Cout(){}

    public Cout(String ressource, int nombreUnite) {
        this.ressource = ressource;
        this.nombreUnite = nombreUnite;
    }

    public String getRessource() {
        return ressource;
    }

    public void setRessource(String ressource) {
        this.ressource = ressource;
    }

    public int getNombreUnite() {
        return nombreUnite;
    }

    public void setNombreUnite(int nombreUnite) {
        this.nombreUnite = nombreUnite;
    }

    @Override
    public String toString() {
        return "Cout{" +
                "ressource='" + ressource + '\'' +
                ", nombreUnite=" + nombreUnite +
                '}';
    }
}
