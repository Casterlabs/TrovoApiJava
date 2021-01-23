package co.casterlabs.trovoapi.requests.data;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@NonNull
@ToString
public class TrovoUser {
    private String username;

    private String nickname;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("channel_id")
    private String channelId;

}
