package co.casterlabs.trovoapi.chat;

import java.util.List;

import com.google.gson.JsonObject;

public class TrovoRawChatMessage {
    public int type;
    public String content;
    public String nick_name;
    public String sender_id;
    public String message_id;
    public String avatar;
    public TrovoSubLevel sub_lv;
    public List<TrovoUserMedal> medals;
    public List<TrovoUserRoles> roles;
    public JsonObject content_data;
    public boolean is_catchup;

}
