package co.casterlabs.trovoapi.serializers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import co.casterlabs.trovoapi.chat.TrovoUserRoles;

public class TrovoUserRolesSerializer implements JsonDeserializer<TrovoUserRoles> {

    @Override
    public TrovoUserRoles deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return TrovoUserRoles.valueOf(json.getAsString().toUpperCase());
    }

}
