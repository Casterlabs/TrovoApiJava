package co.casterlabs.trovoapi.chat.messages;

import java.util.List;

import co.casterlabs.trovoapi.chat.TrovoMessageType;
import co.casterlabs.trovoapi.chat.TrovoRawChatMessage;
import co.casterlabs.trovoapi.chat.TrovoSubLevel;
import co.casterlabs.trovoapi.chat.TrovoUserMedal;
import co.casterlabs.trovoapi.chat.TrovoUserRoles;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrovoChatMessage implements TrovoMessage {
    private TrovoRawChatMessage raw;

    public String getMessage() {
        return this.raw.content;
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
        return TrovoMessageType.CHAT;
    }

    @Override
    public boolean isCatchup() {
        return this.raw.is_catchup;
    }

}
