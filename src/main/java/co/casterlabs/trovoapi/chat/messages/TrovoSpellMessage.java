package co.casterlabs.trovoapi.chat.messages;

import java.util.List;

import com.google.gson.JsonObject;

import co.casterlabs.trovoapi.chat.TrovoMessageType;
import co.casterlabs.trovoapi.chat.TrovoRawChatMessage;
import co.casterlabs.trovoapi.chat.TrovoSpell;
import co.casterlabs.trovoapi.chat.TrovoSubLevel;
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

    public List<String> getSenderMedals() {
        return this.raw.medals;
    }

    public List<String> getSenderRoles() {
        return this.raw.roles;
    }

    @Override
    public TrovoMessageType getType() {
        return TrovoMessageType.SPELL;
    }

}
