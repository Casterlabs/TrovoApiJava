package co.casterlabs.trovoapi.requests;

import java.io.IOException;

import com.google.gson.JsonObject;

import co.casterlabs.apiutil.auth.ApiAuthException;
import co.casterlabs.apiutil.web.ApiException;
import co.casterlabs.apiutil.web.AuthenticatedWebRequest;
import co.casterlabs.trovoapi.TrovoApiJava;
import co.casterlabs.trovoapi.TrovoScope;
import co.casterlabs.trovoapi.TrovoUserAuth;
import co.casterlabs.trovoapi.requests.data.TrovoSelfInfo;
import co.casterlabs.trovoapi.util.HttpUtil;
import lombok.NonNull;

public class TrovoGetSelfInfoRequest extends AuthenticatedWebRequest<TrovoSelfInfo, TrovoUserAuth> {
    public static final String URL = "https://open-api.trovo.live/openplatform/getuserinfo";

    public TrovoGetSelfInfoRequest(@NonNull TrovoUserAuth auth) {
        super(auth);
    }

    @Override
    protected TrovoSelfInfo execute() throws ApiException, ApiAuthException, IOException {
        this.auth.checkScope(TrovoScope.USER_DETAILS_SELF);

        JsonObject json = HttpUtil.sendHttpGet(URL, this.auth);

        return TrovoApiJava.GSON.fromJson(json, TrovoSelfInfo.class);
    }

}
