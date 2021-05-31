package co.casterlabs.trovoapi.chat;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;
import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import co.casterlabs.apiutil.auth.ApiAuthException;
import co.casterlabs.apiutil.web.ApiException;
import co.casterlabs.trovoapi.TrovoApiJava;
import co.casterlabs.trovoapi.TrovoScope;
import co.casterlabs.trovoapi.TrovoUserAuth;
import co.casterlabs.trovoapi.chat.messages.TrovoChatMessage;
import co.casterlabs.trovoapi.chat.messages.TrovoCustomSpellMessage;
import co.casterlabs.trovoapi.chat.messages.TrovoFollowMessage;
import co.casterlabs.trovoapi.chat.messages.TrovoGiftSubMessage;
import co.casterlabs.trovoapi.chat.messages.TrovoGiftSubRandomlyMessage;
import co.casterlabs.trovoapi.chat.messages.TrovoMagicChatMessage;
import co.casterlabs.trovoapi.chat.messages.TrovoMessage;
import co.casterlabs.trovoapi.chat.messages.TrovoPlatformEventMessage;
import co.casterlabs.trovoapi.chat.messages.TrovoRaidWelcomeMessage;
import co.casterlabs.trovoapi.chat.messages.TrovoSpellMessage;
import co.casterlabs.trovoapi.chat.messages.TrovoSubscriptionMessage;
import co.casterlabs.trovoapi.chat.messages.TrovoWelcomeMessage;
import co.casterlabs.trovoapi.util.HttpUtil;
import lombok.NonNull;
import lombok.Setter;
import xyz.e3ndr.fastloggingframework.logging.FastLogger;
import xyz.e3ndr.fastloggingframework.logging.LogLevel;

public class TrovoChat implements Closeable {
    private static final String CHAT_TOKEN_URL = "https://open-api.trovo.live/openplatform/chat/token";
    private static final long KEEPALIVE = TimeUnit.SECONDS.toMillis(20);
    private static URI CHAT_URI;

    private @Setter @Nullable ChatListener listener;

    private Connection conn = new Connection();
    private JsonObject auth;

    static {
        try {
            CHAT_URI = new URI("wss://open-chat.trovo.live/chat");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public TrovoChat(@NonNull TrovoUserAuth auth) throws ApiException, ApiAuthException, IOException {
        auth.checkScope(TrovoScope.CHAT_CONNECT);

        this.auth = HttpUtil.sendHttpGet(CHAT_TOKEN_URL, auth);
    }

    public void connect() {
        if (!this.isOpen()) {
            if (this.conn.getReadyState() == ReadyState.NOT_YET_CONNECTED) {
                this.conn.connect();
            } else {
                this.conn.reconnect();
            }
        }
    }

    public void connectBlocking() throws InterruptedException {
        if (!this.isOpen()) {
            if (this.conn.getReadyState() == ReadyState.NOT_YET_CONNECTED) {
                this.conn.connectBlocking();
            } else {
                this.conn.reconnectBlocking();
            }
        }
    }

    public boolean isOpen() {
        return this.conn.isOpen();
    }

    public void disconnect() {
        if (this.isOpen()) {
            this.conn.close();
        }
    }

    public void disconnectBlocking() throws InterruptedException {
        if (this.isOpen()) {
            this.conn.closeBlocking();
        }
    }

    private class Connection extends WebSocketClient {
        private long nonce = 0;

        public Connection() {
            super(CHAT_URI);
        }

        @Override
        public void onOpen(ServerHandshake handshakedata) {
            this.sendMessage("AUTH", auth);

            Thread t = new Thread(() -> {
                while (this.isOpen()) {
                    try {
                        this.sendMessage("PING", null);
                        Thread.sleep(KEEPALIVE);
                    } catch (Exception ignored) {}
                }
            });

            t.setName("TrovoChat KeepAlive");
            t.start();

            if (listener != null) {
                listener.onOpen();
            }
        }

        public void sendMessage(String type, @Nullable JsonObject data) {
            JsonObject payload = new JsonObject();

            this.nonce++;

            payload.addProperty("type", type);
            payload.addProperty("nonce", String.valueOf(this.nonce));

            if (data != null) {
                payload.add("data", data);
            }

            this.send(payload.toString());
        }

        @Override
        public void onMessage(String raw) {
            JsonObject message = TrovoApiJava.GSON.fromJson(raw, JsonObject.class);

            FastLogger.logStatic(LogLevel.TRACE, raw);

            String type = message.get("type").getAsString();

            if (type.equals("CHAT")) {
                if (listener != null) {
                    JsonObject data = message.getAsJsonObject("data");
                    JsonArray chats = data.getAsJsonArray("chats");

                    if (chats != null) {
                        boolean isCatchup = chats.size() > 1;

                        for (JsonElement e : chats) {
                            TrovoMessage chat = this.parseChat(e.getAsJsonObject(), isCatchup);

                            this.fireListener(chat);
                        }
                    }
                }
            }
        }

        private void fireListener(TrovoMessage chat) {
            switch (chat.getType()) {
                case CHAT:
                    listener.onChatMessage((TrovoChatMessage) chat);
                    break;

                case FOLLOW:
                    listener.onFollow((TrovoFollowMessage) chat);
                    break;

                case GIFT_SUB_RANDOM:
                    listener.onGiftSubRandomly((TrovoGiftSubRandomlyMessage) chat);
                    break;

                case GIFT_SUB_USER:
                    listener.onGiftSub((TrovoGiftSubMessage) chat);
                    break;

                case MAGIC_CHAT_BULLET_SCREEN:
                case MAGIC_CHAT_COLORFUL:
                case MAGIC_CHAT_SPELL:
                case MAGIC_CHAT_SUPER_CAP:
                    listener.onMagicChat((TrovoMagicChatMessage) chat);
                    break;

                case SPELL:
                    listener.onSpell((TrovoSpellMessage) chat);
                    break;

                case PLATFORM_EVENT:
                    listener.onPlatformEvent((TrovoPlatformEventMessage) chat);
                    break;

                case RAID_WELCOME:
                    listener.onRaidWelcome((TrovoRaidWelcomeMessage) chat);
                    break;

                case SUBSCRIPTION:
                    listener.onSubscription((TrovoSubscriptionMessage) chat);
                    break;

                case WELCOME:
                    listener.onWelcome((TrovoWelcomeMessage) chat);
                    break;

                case CUSTOM_SPELL:
                    listener.onCustomSpell((TrovoCustomSpellMessage) chat);
                    break;

                case UNKNOWN:
                    break;
            }
        }

        public TrovoMessage parseChat(JsonObject chat, boolean isCatchup) {
            TrovoRawChatMessage raw = TrovoApiJava.GSON.fromJson(chat, TrovoRawChatMessage.class);
            TrovoMessageType type = TrovoMessageType.lookup(raw.type);

            raw.is_catchup = isCatchup;

            if ((raw.avatar != null) && !raw.avatar.contains("://")) {
                raw.avatar = "https://headicon.trovo.live/user/" + raw.avatar; // Damn you Trovo.
            }

            switch (type) {
                case CHAT:
                    return new TrovoChatMessage(raw);

                case FOLLOW:
                    return new TrovoFollowMessage(raw);

                case GIFT_SUB_RANDOM:
                    return new TrovoGiftSubRandomlyMessage(raw);

                case GIFT_SUB_USER:
                    return new TrovoGiftSubMessage(raw);

                case MAGIC_CHAT_BULLET_SCREEN:
                case MAGIC_CHAT_COLORFUL:
                case MAGIC_CHAT_SPELL:
                case MAGIC_CHAT_SUPER_CAP:
                    return new TrovoMagicChatMessage(raw, type);

                case PLATFORM_EVENT:
                    return new TrovoPlatformEventMessage(raw);

                case RAID_WELCOME:
                    return new TrovoRaidWelcomeMessage(raw);

                case SPELL:
                    return new TrovoSpellMessage(raw, TrovoApiJava.GSON.fromJson(raw.content, JsonObject.class));

                case SUBSCRIPTION:
                    return new TrovoSubscriptionMessage(raw);

                case WELCOME:
                    return new TrovoWelcomeMessage(raw);

                case CUSTOM_SPELL:
                    return new TrovoCustomSpellMessage(raw, TrovoApiJava.GSON.fromJson(raw.content, JsonObject.class));

                case UNKNOWN:
                default:
                    return null;

            }
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            if (listener != null) {
                listener.onClose(remote);
            }
        }

        @Override
        public void onError(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() throws IOException {
        try {
            this.disconnectBlocking();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
