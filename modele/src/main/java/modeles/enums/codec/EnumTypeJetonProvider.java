package modeles.enums.codec;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class EnumTypeJetonProvider implements CodecProvider {
    @Override
    public <T> Codec<T> get(Class<T> aClass, CodecRegistry codecRegistry) {
        return null;
    }
}
