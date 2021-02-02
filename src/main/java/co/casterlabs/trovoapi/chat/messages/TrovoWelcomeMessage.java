package co.casterlabs.trovoapi.chat.messages;

import java.util.List;

import co.casterlabs.trovoapi.chat.TrovoMessageType;
import co.casterlabs.trovoapi.chat.TrovoRawChatMessage;
import co.casterlabs.trovoapi.chat.TrovoSubLevel;
import co.casterlabs.trovoapi.chat.TrovoUserMedal;
import co.casterlabs.trovoapi.chat.TrovoUserRoles;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrovoWelcomeMessage implements TrovoMessage {
    private TrovoRawChatMessage raw;

    public String getViewerNickname() {
        return this.raw.nick_name;
    }

    public String getViewerAvatar() {
        return this.raw.avatar;
    }

    public TrovoSubLevel getViewerSubLevel() {
        return this.raw.sub_lv;
    }

    public List<TrovoUserMedal> getViewerMedals() {
        return this.raw.medals;
    }

    public List<TrovoUserRoles> getViewerRoles() {
        return this.raw.roles;
    }

    public String getViewerId() {
        return this.raw.sender_id;
    }

    @Override
    public TrovoMessageType getType() {
        return TrovoMessageType.WELCOME;
    }

}
