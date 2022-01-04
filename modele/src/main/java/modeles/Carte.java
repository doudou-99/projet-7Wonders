package modeles;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Carte {
    @BsonId()
    @BsonProperty("_id")
    private String id;
    private String nom;
    @BsonProperty("capacite")
    private Collection<Effet> effet;
    private Collection<Cout> cout;
    private Collection<Integer> chiffre;
    private String couleur;
    private String type;
    private String image;
    private String age;

    public Carte(){}

    public Carte(@BsonProperty("_id") String id, String nom,@BsonProperty("capacite") Collection<Effet> effet, Collection<Cout> cout, String couleur, String type, String image, String age) {
        this.id = id;
        this.nom = nom;
        this.effet = effet;
        this.cout = cout;
        this.chiffre=new ArrayList<>();
        this.couleur = couleur;
        this.type = type;
        this.image = image;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public Collection<Cout> getCout() {
        return cout;
    }

    public String getNom() {
        return nom;
    }

    public Collection<Integer> getChiffre() {
        return chiffre;
    }

    public Collection<Effet> getEffet() {
        return effet;
    }

    public String getAge() {
        return age;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEffet(Collection<Effet> effet) {
        this.effet = effet;
    }

    public void setCout(Collection<Cout> cout) {
        this.cout = cout;
    }

    public void setChiffre(List<Integer> chiffre) {
        this.chiffre = chiffre;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAge(String age) {
        this.age = age;
    }



    @Override
    public String toString() {

        return "Carte{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", effet='" + effet + '\'' +
                ", cout='" + cout + '\'' +
                ", chiffre=" + chiffre +
                ", couleur='" + couleur + '\'' +
                ", type='" + type + '\'' +
                ", image='" + image + '\'' +
                ", age=" + age +
                '}';
    }
}
