package co.casterlabs.trovoapi.requests;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import co.casterlabs.apiutil.auth.ApiAuthException;
import co.casterlabs.apiutil.web.ApiException;
import co.casterlabs.apiutil.web.AuthenticatedWebRequest;
import co.casterlabs.trovoapi.TrovoApiJava;
import co.casterlabs.trovoapi.TrovoApplicationAuth;
import co.casterlabs.trovoapi.requests.data.TrovoGameCategory;
import co.casterlabs.trovoapi.util.HttpUtil;
import lombok.NonNull;

public class TrovoGetGameCategoriesRequest extends AuthenticatedWebRequest<List<TrovoGameCategory>, TrovoApplicationAuth> {
    public static final String URL = "https://open-api.trovo.live/openplatform/categorys/top";

    private static final Type LIST_TYPE = new TypeToken<List<TrovoGameCategory>>() {
    }.getType();

    public TrovoGetGameCategoriesRequest(@NonNull TrovoApplicationAuth auth) {
        super(auth);
    }

    @Override
    protected List<TrovoGameCategory> execute() throws ApiException, ApiAuthException, IOException {
        JsonObject json = HttpUtil.sendHttpGet(URL, this.auth);

        return TrovoApiJava.GSON.fromJson(json.get("category_info"), LIST_TYPE);
    }

}
