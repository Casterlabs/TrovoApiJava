package co.casterlabs.trovoapi.chat.messages;

import java.util.List;

import co.casterlabs.trovoapi.chat.TrovoMessageType;
import co.casterlabs.trovoapi.chat.TrovoRawChatMessage;
import co.casterlabs.trovoapi.chat.TrovoSubLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrovoGiftSubRandomlyMessage implements TrovoMessage {
    private TrovoRawChatMessage raw;

    public int getGiftAmount() {
        return Integer.parseInt(this.raw.content);
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
        return TrovoMessageType.GIFT_SUB_RANDOM;
    }

}
