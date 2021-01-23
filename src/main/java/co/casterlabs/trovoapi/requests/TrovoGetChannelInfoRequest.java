package co.casterlabs.trovoapi.requests;

import java.io.IOException;

import com.google.gson.JsonObject;

import co.casterlabs.apiutil.auth.ApiAuthException;
import co.casterlabs.apiutil.web.ApiException;
import co.casterlabs.apiutil.web.AuthenticatedWebRequest;
import co.casterlabs.trovoapi.TrovoApiJava;
import co.casterlabs.trovoapi.TrovoApplicationAuth;
import co.casterlabs.trovoapi.TrovoScope;
import co.casterlabs.trovoapi.TrovoUserAuth;
import co.casterlabs.trovoapi.requests.data.TrovoChannelInfo;
import co.casterlabs.trovoapi.util.HttpUtil;
import lombok.NonNull;

public class TrovoGetChannelInfoRequest extends AuthenticatedWebRequest<TrovoChannelInfo, TrovoApplicationAuth> {
    private String url;
    private String channelId;

    public TrovoGetChannelInfoRequest(@NonNull TrovoApplicationAuth auth, @NonNull String channelId) {
        super(auth);

        this.channelId = channelId;
        this.url = "https://open-api.trovo.live/openplatform/channels/id";
    }

    public TrovoGetChannelInfoRequest(@NonNull TrovoUserAuth auth) {
        super(auth);

        this.url = "https://open-api.trovo.live/openplatform/channel";
    }

    @Override
    protected TrovoChannelInfo execute() throws ApiException, ApiAuthException, IOException {
        JsonObject json;

        if (this.channelId == null) {
            ((TrovoUserAuth) this.auth).checkScope(TrovoScope.CHANNEL_DETAILS_SELF);

            json = HttpUtil.sendHttpGet(this.url, this.auth);
        } else {
            JsonObject body = new JsonObject();

            body.addProperty("channel_id", this.channelId);

            json = HttpUtil.sendHttp(body.toString(), this.url, this.auth);
        }

        return TrovoApiJava.GSON.fromJson(json, TrovoChannelInfo.class);
    }

}
