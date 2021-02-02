package co.casterlabs.trovoapi.chat.messages;

import java.util.List;

import com.google.gson.JsonObject;

import co.casterlabs.trovoapi.chat.TrovoMessageType;
import co.casterlabs.trovoapi.chat.TrovoRawChatMessage;
import co.casterlabs.trovoapi.chat.TrovoSpell;
import co.casterlabs.trovoapi.chat.TrovoSubLevel;
import co.casterlabs.trovoapi.chat.TrovoUserMedal;
import co.casterlabs.trovoapi.chat.TrovoUserRoles;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrovoSpellMessage implements TrovoMessage {
    private TrovoRawChatMessage raw;
    private JsonObject content;

    public TrovoSpell getSpell() {
        return TrovoSpell.valueOf(this.content.get("gift").getAsString().toUpperCase().replace(' ', '_'));
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
        return TrovoMessageType.SPELL;
    }

}
