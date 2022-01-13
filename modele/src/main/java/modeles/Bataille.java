package modeles;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import java.util.Collection;
import java.util.List;

public class Bataille {
    @BsonProperty("_id")
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    @BsonProperty("nomDuVaincu")
    private List<String> nomDuVaincu;
    @BsonProperty("nomDuVainqueur")
    private String nomDuVainqueur;
    @BsonProperty("age")
    private Age age;

    public Bataille(){}

    public Bataille(String id,List<String> nomDuVaincu, String nomDuVainqueur,Age age) {
        this.nomDuVaincu = nomDuVaincu;
        this.nomDuVainqueur = nomDuVainqueur;
        this.age=age;
    }

    public List<String> getNomDuVaincu() {
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
                "id='"+id+"'"+
                ", nomDuVaincu=" + nomDuVaincu +
                ", nomDuVainqueur=" + nomDuVainqueur +
                ",age="+age+
                '}';
    }
}
