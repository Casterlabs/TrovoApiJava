package co.casterlabs.trovoapi.requests.data;

import org.jetbrains.annotations.Nullable;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@NonNull
@ToString
public class TrovoSelfInfo {
    @SerializedName("userName")
    private String username;

    @SerializedName("nickName")
    private String nickname;

    @SerializedName("userId")
    private String userId;

    private @Nullable String email;

    @SerializedName("channelId")
    private String channelId;

    @SerializedName("profilePic")
    private String profilePictureLink;

    @SerializedName("info")
    private String streamerInfo;

}
