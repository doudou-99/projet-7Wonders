package modeles.enums.codec;

import modeles.enums.Couleur;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class EnumCouleurCodec implements Codec<Couleur> {
    @Override
    public Couleur decode(BsonReader bsonReader, DecoderContext decoderContext) {
        Couleur couleur = null;
        switch (bsonReader.readString()){
            case "bleu":
                couleur= Couleur.BLEU;
                break;
            case "jaune":
                couleur=Couleur.JAUNE;
                break;
            case "gris":
                couleur= Couleur.GRIS;
                break;
            case "violet":
                couleur= Couleur.VIOLET;
                break;
            case "vert":
                couleur= Couleur.VERT;
                break;
            case "rouge":
                couleur= Couleur.ROUGE;
                break;
            case "marron":
                couleur= Couleur.MARRON;
                break;
        }
        return couleur;
    }

    @Override
    public void encode(BsonWriter bsonWriter, Couleur couleur, EncoderContext encoderContext) {
        if (couleur != null) {
            bsonWriter.writeString(couleur.getCouleur());
        }
    }

    @Override
    public Class<Couleur> getEncoderClass() {
        return Couleur.class;
    }
}
