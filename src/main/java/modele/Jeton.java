package modele;

import modele.enums.TypeJeton;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

public class Jeton {
    public String _id;
    public String type;
    private int points;
    private String age;

    public Jeton(){}

    public Jeton(String type, int points, String age) {
        this.type = type;
        this.points = points;
        this.age = age;
    }


    public String getType() {
        return type;
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

    @Override
    public String toString() {
        return "Jeton{" +
                "_id="+_id+
                ", type=" + type +
                ", points=" + points +
                ", age=" + age +
                '}';
    }
}
