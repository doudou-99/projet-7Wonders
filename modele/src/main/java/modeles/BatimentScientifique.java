package modeles;

public class BatimentScientifique {
    private String nomCarte;
    private String typeSymbole;
    private int nombre;

    public BatimentScientifique(String nomCarte, String typeSymbole, int nombre) {
        this.nomCarte=nomCarte;
        this.typeSymbole = typeSymbole;
        this.nombre = nombre;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public String getTypeSymbole() {
        return typeSymbole;
    }

    public void setTypeSymbole(String typeSymbole) {
        this.typeSymbole = typeSymbole;
    }

    public String getNomCarte() {
        return nomCarte;
    }

    public void setNomCarte(String nomCarte) {
        this.nomCarte = nomCarte;
    }
}
