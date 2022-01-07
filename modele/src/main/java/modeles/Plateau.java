package modeles;


import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Plateau implements Serializable {
    private static final long serialVersionUID=1L;
    @BsonId()
    @BsonProperty("_id")
    private String id;
    @BsonProperty("nom")
    private String nomMerveille;

    private String capacite;
    private String face;
    private Collection<Etage> etages;
    private String image;

    public Plateau(){}

    public Plateau(@BsonProperty("_id") String id,@BsonProperty("nom") String nomMerveille, String capacite, String face,String image) {
        this.id = id;
        this.nomMerveille = nomMerveille;
        this.capacite = capacite;
        this.face = face;

        this.etages=new ArrayList<>();
        this.image=image;
    }

    public String getId() {
        return id;
    }

    public String getCapacite() {
        return capacite;
    }

    public String getFace() {
        return face;
    }

    public String getNomMerveille() {
        return nomMerveille;
    }

    public Collection<Etage> getEtages() {
        return etages;
    }

    public String getImage() {
        return image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNomMerveille(String nomMerveille) {
        this.nomMerveille = nomMerveille;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public void setFace(String face) {
        this.face = face;
    }



    public void setEtages(Collection<Etage> etages) {
        this.etages = etages;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Plateau{" +
                "id='" + id + '\'' +
                ", nomMerveille='" + nomMerveille + '\'' +
                ", capacite='" + capacite + '\'' +
                ", face='" + face + '\'' +
                ", etages=" + etages +
                ", image='" + image + '\'' +
                '}';
    }
}
