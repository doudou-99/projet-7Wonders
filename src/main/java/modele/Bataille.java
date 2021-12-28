package modele;

import java.util.Collection;

public class Bataille {
    private Collection<String> nomDuVaincu;
    private String nomDuVainqueur;
    private int nombreDePoints;
    private Age age;

    public Bataille(){}

    public Bataille(Collection<String> nomDuVaincu, String nomDuVainqueur, int nombreDePoints,Age age) {
        this.nomDuVaincu = nomDuVaincu;
        this.nomDuVainqueur = nomDuVainqueur;
        this.nombreDePoints = nombreDePoints;
        this.age=age;
    }

    public Collection<String> getNomDuVaincu() {
        return nomDuVaincu;
    }

    public void setNomDuVaincu(Collection<String> nomDuVaincu) {
        this.nomDuVaincu = nomDuVaincu;
    }

    public String getNomDuVainqueur() {
        return nomDuVainqueur;
    }

    public void setNomDuVainqueur(String nomDuVainqueur) {
        this.nomDuVainqueur = nomDuVainqueur;
    }

    public int getNombreDePoints() {
        return nombreDePoints;
    }

    public void setNombreDePoints(int nombreDePoints) {
        this.nombreDePoints = nombreDePoints;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public Age getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Bataille{" +
                "nomDuVaincu=" + nomDuVaincu +
                ", nomDuVainqueur=" + nomDuVainqueur +
                ", nombreDePoints=" + nombreDePoints +
                ",age="+age+
                '}';
    }
}
