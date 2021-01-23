package co.casterlabs.trovoapi.requests.data;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@NonNull
@ToString
public class TrovoChannelInfo {
    @SerializedName("is_live")
    private boolean isLive;

    @SerializedName("category_id")
    private String categoryId;

    @SerializedName("category_name")
    private String categoryName;

    @SerializedName("live_title")
    private String streamTitle;

    @SerializedName("audi_type")
    private AudienceType audienceType;

    @SerializedName("language_code")
    private String languageCode;

    @SerializedName("thumbnail")
    private String thumbnailLink;

    @SerializedName("current_viewers")
    private long currentViewers;

    @SerializedName("followers")
    private long followers;

    @SerializedName("streamer_info")
    private String streamerInfo;

    @SerializedName("profile_pic")
    private String profilePictureLink;

    @SerializedName("channel_url")
    private String channelUrl;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("subscriber_num")
    private long subscribers;

    @SerializedName("stream_key")
    private String streamKey;

    private String username;

    @SerializedName("social_links")
    private List<Social> socials;

    public static enum AudienceType {
        CHANNEL_AUDIENCE_TYPE_FAMILYFRIENDLY,
        CHANNEL_AUDIENCE_TYPE_TEEN,
        CHANNEL_AUDIENCE_TYPE_EIGHTEENPLUS
    }

    @Getter
    @NonNull
    @ToString
    public static class Social {
        private String type;

        @SerializedName("url")
        private String link;

    }

}
