package co.casterlabs.trovoapi.requests;

import java.io.IOException;

import com.google.gson.JsonObject;

import co.casterlabs.apiutil.auth.ApiAuthException;
import co.casterlabs.apiutil.web.ApiException;
import co.casterlabs.apiutil.web.AuthenticatedWebRequest;
import co.casterlabs.trovoapi.TrovoScope;
import co.casterlabs.trovoapi.TrovoUserAuth;
import co.casterlabs.trovoapi.util.HttpUtil;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class TrovoSendChatMessageRequest extends AuthenticatedWebRequest<Void, TrovoUserAuth> {
    public static final String URL = "https://open-api.trovo.live/openplatform/chat/send";

    private String message;
    private @Setter String channelId;

    public TrovoSendChatMessageRequest(@NonNull TrovoUserAuth auth, @NonNull String message) {
        super(auth);

        this.message = message;
    }

    @Override
    protected Void execute() throws ApiException, ApiAuthException, IOException {
        this.auth.checkScope(TrovoScope.CHAT_SEND_SELF);
        this.auth.checkScope(TrovoScope.SEND_TO_MY_CHANNEL);

        JsonObject body = new JsonObject();

        body.addProperty("content", this.message);

        if (this.channelId != null) {
            body.addProperty("channel_id", this.channelId);
        }

        HttpUtil.sendHttp(body.toString(), URL, this.auth);

        return null;
    }

}
