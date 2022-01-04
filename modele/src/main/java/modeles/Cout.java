package modeles;

public class Cout {
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
