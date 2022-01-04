package modeles;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Bataille {
    private List<String> nomDuVaincu;
    private String nomDuVainqueur;
    private Age age;

    public Bataille(){}

    public Bataille(List<String> nomDuVaincu, String nomDuVainqueur,Age age) {
        this.nomDuVaincu = nomDuVaincu;
        this.nomDuVainqueur = nomDuVainqueur;
        this.age=age;
    }

    public Collection<String> getNomDuVaincu() {
        return nomDuVaincu;
    }

    public void setNomDuVaincu(List<String> nomDuVaincu) {
        this.nomDuVaincu = nomDuVaincu;
    }

    public String getNomDuVainqueur() {
        return nomDuVainqueur;
    }

    public void setNomDuVainqueur(String nomDuVainqueur) {
        this.nomDuVainqueur = nomDuVainqueur;
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
                ",age="+age+
                '}';
    }
}
