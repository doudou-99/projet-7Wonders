package modeles;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Age {
    @BsonProperty("_id")
    private String id;
    @BsonProperty("sensDeRotation")
    private String sensDeRotation;
    @BsonProperty("nombreTour")
    private int nombreTour;


    public Age(){}

    public Age(@BsonProperty("_id") String id,@BsonProperty("sensDeRotation") String sensDeRotation,@BsonProperty("nombreTour") int nombreTour) {
        this.id = id;
        this.sensDeRotation = sensDeRotation;
        this.nombreTour = nombreTour;
    }

    public String getId() {
        return id;
    }

    public int getNombreTour() {
        return nombreTour;
    }

    public String getSensDeRotation() {
        return sensDeRotation;
    }

    public void setNombreTour(int nombreTour) {
        this.nombreTour = nombreTour;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSensDeRotation(String sensDeRotation) {
        this.sensDeRotation = sensDeRotation;
    }


    @Override
    public String toString() {
        return "Age{" +
                "id='" + id + '\'' +
                ", sensDeRotation='" + sensDeRotation + '\'' +
                ", nombreTour=" + nombreTour +
                '}';
    }
}
