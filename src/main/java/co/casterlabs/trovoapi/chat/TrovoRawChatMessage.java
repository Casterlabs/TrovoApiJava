package co.casterlabs.trovoapi.chat;

import java.util.List;

public class TrovoRawChatMessage {
    public int type;
    public String content;
    public String nick_name;
    public String avatar;
    public TrovoSubLevel sub_lv;
    public List<String> medals;
    public List<String> roles;

}
