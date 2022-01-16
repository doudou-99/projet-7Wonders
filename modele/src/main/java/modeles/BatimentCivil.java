package modeles;

import java.io.Serializable;

public class BatimentCivil implements Serializable {
    private final static long serialVersionUID=3L;
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
