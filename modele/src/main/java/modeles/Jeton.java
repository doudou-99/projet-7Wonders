package modeles;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.io.Serializable;

public class Jeton implements Serializable{
    private final static long serialVersionUID=3L;
    private final static int nombre = 42;
    @BsonProperty("_id")
    private String id;
    private String type;
    private int points;
    private String age;
    private String image;


    public Jeton(){}

    public Jeton(@BsonProperty("_id") String id,String type, int points, String age,String image) {
        this.id=id;
        this.type = type;
        this.points = points;
        this.age = age;
        this.image=image;
    }


    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Jeton{" +
                "id="+id+
                ", type=" + type +
                ", points=" + points +
                ", age=" + age +
                ", image='"+image+"'"+
                '}';
    }
}
