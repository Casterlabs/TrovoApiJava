package co.casterlabs.trovoapi.requests;

import java.io.IOException;

import co.casterlabs.apiutil.auth.ApiAuthException;
import co.casterlabs.apiutil.web.ApiException;
import co.casterlabs.apiutil.web.AuthenticatedWebRequest;
import co.casterlabs.trovoapi.TrovoScope;
import co.casterlabs.trovoapi.TrovoUserAuth;
import co.casterlabs.trovoapi.util.HttpUtil;
import lombok.NonNull;

public class TrovoDeleteChatMessageRequest extends AuthenticatedWebRequest<Void, TrovoUserAuth> {
    public static final String URL = "https://open-api.trovo.live/openplatform/channels/%s/messages/%s/senderuid/%s";

    private String channelId;
    private String messageId;
    private String senderId;

    public TrovoDeleteChatMessageRequest(@NonNull TrovoUserAuth auth, @NonNull String channelId, @NonNull String messageId, @NonNull String senderId) {
        super(auth);

        this.channelId = channelId;
        this.messageId = messageId;
        this.senderId = senderId;
    }

    @Override
    protected Void execute() throws ApiException, ApiAuthException, IOException {
        this.auth.checkScope(TrovoScope.MANAGE_MESSAGES);

        HttpUtil.rawHttpMethod(String.format(URL, this.channelId, this.messageId, this.senderId), "DELETE", this.auth);

        return null;
    }

}
