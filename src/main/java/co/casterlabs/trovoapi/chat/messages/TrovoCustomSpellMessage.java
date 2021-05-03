package co.casterlabs.trovoapi.chat.messages;

import java.util.List;

import com.google.gson.JsonObject;

import co.casterlabs.trovoapi.chat.TrovoMessageType;
import co.casterlabs.trovoapi.chat.TrovoRawChatMessage;
import co.casterlabs.trovoapi.chat.TrovoSubLevel;
import co.casterlabs.trovoapi.chat.TrovoUserMedal;
import co.casterlabs.trovoapi.chat.TrovoUserRoles;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrovoCustomSpellMessage implements TrovoMessage {
    private static final String IMAGE_FORMAT = "https://custom-file.trovo.live/file/%s/icon%d.png?imageView2/2/format/webp";

    private TrovoRawChatMessage raw;
    private JsonObject content;

    public long getSpellId() {
        return this.content.get("sid").getAsLong();
    }

    public String getImageLink(String channelId) {
        return String.format(IMAGE_FORMAT, channelId, this.getSpellId());
    }

    public String getGift() {
        return this.content.get("gift").getAsString();
    }

    public int getAmount() {
        return this.content.get("num").getAsInt();
    }

    public String getSenderNickname() {
        return this.raw.nick_name;
    }

    public String getSenderAvatar() {
        return this.raw.avatar;
    }

    public TrovoSubLevel getSenderSubLevel() {
        return this.raw.sub_lv;
    }

    public List<TrovoUserMedal> getSenderMedals() {
        return this.raw.medals;
    }

    public List<TrovoUserRoles> getSenderRoles() {
        return this.raw.roles;
    }

    public String getMessageId() {
        return this.raw.message_id;
    }

    public String getSenderId() {
        return this.raw.sender_id;
    }

    @Override
    public TrovoMessageType getType() {
        return TrovoMessageType.CUSTOM_SPELL;
    }

    @Override
    public boolean isCatchup() {
        return this.raw.is_catchup;
    }

}
