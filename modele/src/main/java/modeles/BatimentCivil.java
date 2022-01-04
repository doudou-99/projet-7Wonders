package modeles;

public class BatimentCivil{
    private String nom;
    private int nombre;
    public BatimentCivil(){}

    public BatimentCivil(String nom,int nombre){
        this.nom=nom;
        this.nombre=nombre;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "BatimentCivil{" +
                "nom='"+nom+"'"+
                ", nombre=" + nombre +
                '}';
    }
}
