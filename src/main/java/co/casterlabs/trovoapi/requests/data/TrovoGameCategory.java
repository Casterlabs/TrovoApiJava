package co.casterlabs.trovoapi.requests.data;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@NonNull
@ToString
public class TrovoGameCategory {
    private String id;

    private String name;

    @SerializedName("short_name")
    private String shortName;

    @SerializedName("icon_url")
    private String iconUrl;

    @SerializedName("desc")
    private String description;

}
