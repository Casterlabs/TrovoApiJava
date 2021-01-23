package co.casterlabs.trovoapi.chat.messages;

import java.util.List;

import co.casterlabs.trovoapi.chat.TrovoMessageType;
import co.casterlabs.trovoapi.chat.TrovoRawChatMessage;
import co.casterlabs.trovoapi.chat.TrovoSubLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrovoFollowMessage implements TrovoMessage {
    private TrovoRawChatMessage raw;

    public String getFollowerNickname() {
        return this.raw.nick_name;
    }

    public String getFollowerAvatar() {
        return this.raw.avatar;
    }

    public TrovoSubLevel getFollowerSubLevel() {
        return this.raw.sub_lv;
    }

    public List<String> getFollowerMedals() {
        return this.raw.medals;
    }

    public List<String> getFollowerRoles() {
        return this.raw.roles;
    }

    @Override
    public TrovoMessageType getType() {
        return TrovoMessageType.FOLLOW;
    }

}
