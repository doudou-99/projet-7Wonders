package modele;

public class BatimentCivil{
    private int nombre;
    public BatimentCivil(){}

    public BatimentCivil(int nombre){
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
                "nombre=" + nombre +
                '}';
    }
}
