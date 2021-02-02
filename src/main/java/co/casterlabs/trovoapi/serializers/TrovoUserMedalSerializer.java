package co.casterlabs.trovoapi.serializers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import co.casterlabs.trovoapi.chat.TrovoUserMedal;

public class TrovoUserMedalSerializer implements JsonDeserializer<TrovoUserMedal> {

    @Override
    public TrovoUserMedal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new TrovoUserMedal(json.getAsString());
    }

}
