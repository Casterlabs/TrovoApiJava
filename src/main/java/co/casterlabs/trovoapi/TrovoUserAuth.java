package co.casterlabs.trovoapi;

import java.io.IOException;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import co.casterlabs.apiutil.auth.ApiAuthException;
import co.casterlabs.apiutil.web.ApiException;
import co.casterlabs.trovoapi.util.HttpUtil;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import okhttp3.Request.Builder;

public class TrovoUserAuth extends TrovoApplicationAuth {
    private static final String VALIDATE_URL = "https://open-api.trovo.live/openplatform/validate";
    private static final String REVOKE_URL = "https://open-api.trovo.live/openplatform/revoke";

    private String accessToken;
    private List<String> scopes;

    public TrovoUserAuth(@NonNull String clientId, @NonNull String accessToken) throws ApiException, ApiAuthException, IOException {
        super(clientId);

        this.accessToken = accessToken;

        this.scopes = this.validate().scopes;
    }

    public AuthData validate() throws ApiException, ApiAuthException, IOException {
        JsonObject json = HttpUtil.sendHttpGet(VALIDATE_URL, this);

        return TrovoApiJava.GSON.fromJson(json, AuthData.class);
    }

    public void revoke() throws ApiException, ApiAuthException, IOException {
        JsonObject payload = new JsonObject();

        payload.addProperty("access_token", this.accessToken);

        HttpUtil.sendHttp(payload.toString(), REVOKE_URL, this);
    }

    public void checkScope(@NonNull TrovoScope scope) throws ApiAuthException {
        String name = scope.name().toLowerCase();

        if (!this.scopes.contains(scope.name().toLowerCase())) {
            throw new ApiAuthException("Missing required scope: " + name);
        }
    }

    @Override
    public void authenticateRequest(@NonNull Builder request) {
        request.header("Client-ID", this.clientId);
        request.header("Authorization", "OAuth " + this.accessToken);
    }

    @Override
    public void refresh() throws ApiAuthException {
        // NOP
    }

    @Getter
    @NonNull
    @ToString
    public static class AuthData {
        @SerializedName("uid")
        private String userId;

        @SerializedName("client_ID")
        private String clientID;

        @SerializedName("nick_name")
        private String nickname;

        @SerializedName("expire_ts")
        private String expireTimestamp;

        private List<String> scopes;

    }

}
