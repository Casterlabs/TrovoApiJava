package co.casterlabs.trovoapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.casterlabs.trovoapi.chat.TrovoUserMedal;
import co.casterlabs.trovoapi.chat.TrovoUserRoles;
import co.casterlabs.trovoapi.serializers.TrovoUserMedalSerializer;
import co.casterlabs.trovoapi.serializers.TrovoUserRolesSerializer;

public class TrovoApiJava {
    // @formatter:off
    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(TrovoUserRoles.class, new TrovoUserRolesSerializer())
            .registerTypeAdapter(TrovoUserMedal.class, new TrovoUserMedalSerializer())
            .create();
    // @formatter:on

}
