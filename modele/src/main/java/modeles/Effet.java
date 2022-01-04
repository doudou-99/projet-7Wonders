package modeles;

import java.util.Collection;

public class Effet {
    private String capacite;
    private int nombre;
    private Collection<String> choix;
    private String capaciteSup;

    public Effet(){}

    public Effet(String capacite, int nombre,Collection<String> choix,String capaciteSup) {
        this.capacite = capacite;
        this.nombre = nombre;
        this.choix=choix;
        this.capaciteSup=capaciteSup;
    }

    public String getCapacite() {
        return capacite;
    }

    public int getNombre() {
        return nombre;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public Collection<String> getChoix() {
        return choix;
    }

    public void setChoix(Collection<String> choix) {
        this.choix = choix;
    }

    public String getCapaciteSup() {
        return capaciteSup;
    }

    public void setCapaciteSup(String capaciteSup) {
        this.capaciteSup = capaciteSup;
    }

    public boolean avoirMemeRessource(String effet){
        return effet.equals(this.capacite);
    }


    @Override
    public String toString() {
        return "Effet{" +
                "capacite='" + capacite + '\'' +
                ", nombre=" + nombre +
                ", choix=" + choix +
                ", capaciteSup='"+capaciteSup + "'" +
                '}';
    }
}
