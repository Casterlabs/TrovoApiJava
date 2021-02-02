package co.casterlabs.trovoapi.chat.messages;

import java.util.List;

import co.casterlabs.trovoapi.chat.TrovoMessageType;
import co.casterlabs.trovoapi.chat.TrovoRawChatMessage;
import co.casterlabs.trovoapi.chat.TrovoSubLevel;
import co.casterlabs.trovoapi.chat.TrovoUserMedal;
import co.casterlabs.trovoapi.chat.TrovoUserRoles;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrovoSubscriptionMessage implements TrovoMessage {
    private TrovoRawChatMessage raw;

    public String getSubscriberNickname() {
        return this.raw.nick_name;
    }

    public String getSubscriberAvatar() {
        return this.raw.avatar;
    }

    public TrovoSubLevel getSubscriberSubLevel() {
        return this.raw.sub_lv;
    }

    public List<TrovoUserMedal> getSubscriberMedals() {
        return this.raw.medals;
    }

    public List<TrovoUserRoles> getSubscriberRoles() {
        return this.raw.roles;
    }

    public String getSubscriberId() {
        return this.raw.sender_id;
    }

    @Override
    public TrovoMessageType getType() {
        return TrovoMessageType.SUBSCRIPTION;
    }

}
