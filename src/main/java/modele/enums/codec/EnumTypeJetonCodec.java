package modele.enums.codec;

import modele.enums.TypeJeton;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

public class EnumTypeJetonCodec implements Codec<TypeJeton> {



    @Override
    public TypeJeton decode(BsonReader bsonReader, DecoderContext decoderContext) {
        TypeJeton typeJeton = null;
        switch (bsonReader.readString()){
            case "victoire":
                typeJeton= TypeJeton.VICTOIRE;
                break;
            case "defaite":
                typeJeton=TypeJeton.DEFAITE;
                break;
        }
        return typeJeton;
    }

    @Override
    public void encode(BsonWriter bsonWriter, TypeJeton typeJeton, EncoderContext encoderContext) {
        if (typeJeton != null) {
            bsonWriter.writeString(typeJeton.getTypeJeton());

        }
    }

    @Override
    public Class<TypeJeton> getEncoderClass() {
        return TypeJeton.class;
    }
}
