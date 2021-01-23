package co.casterlabs.trovoapi.requests;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import co.casterlabs.apiutil.auth.ApiAuthException;
import co.casterlabs.apiutil.web.ApiException;
import co.casterlabs.apiutil.web.AuthenticatedWebRequest;
import co.casterlabs.trovoapi.TrovoApiJava;
import co.casterlabs.trovoapi.TrovoApplicationAuth;
import co.casterlabs.trovoapi.requests.data.TrovoUser;
import co.casterlabs.trovoapi.util.HttpUtil;
import lombok.NonNull;

public class TrovoGetUsersRequest extends AuthenticatedWebRequest<List<TrovoUser>, TrovoApplicationAuth> {
    public static final String URL = "https://open-api.trovo.live/openplatform/getusers";

    private static final Type LIST_TYPE = new TypeToken<List<TrovoUser>>() {
    }.getType();

    private JsonArray users = new JsonArray();

    public TrovoGetUsersRequest(@NonNull TrovoApplicationAuth auth, @NonNull String... usernames) {
        super(auth);

        for (String username : usernames) {
            this.users.add(username);
        }
    }

    @Override
    protected List<TrovoUser> execute() throws ApiException, ApiAuthException, IOException {
        if (this.users.size() == 0) {
            throw new ApiException("No usernames to query");
        } else {
            JsonObject body = new JsonObject();

            body.add("user", users);

            JsonObject json = HttpUtil.sendHttp(body.toString(), URL, this.auth);

            return TrovoApiJava.GSON.fromJson(json.get("users"), LIST_TYPE);
        }
    }

}
