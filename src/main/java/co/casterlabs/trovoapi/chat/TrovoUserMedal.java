package co.casterlabs.trovoapi.chat;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TrovoUserMedal {
    private static final String BLANK_IMAGE = "data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==";
    private static final Map<String, String> IMAGES = new HashMap<>();

    private String name;
    private String image;

    // Auto generated code from trovo_medals.json
    static {
        IMAGES.put("streamer_trovo500", "https://img.trovo.live/imgupload/application/20210125_3p78qrxp1fwtrovo_500.png");
        IMAGES.put("xmaschat", "https://img.trovo.live/imgupload/application/20201208_4kpw01ivp1f3x.png");
        IMAGES.put("famhype_deco_ordinary", "https://static.trovo.live/imgupload/application/20200902_22atwe83vh2i3x.png");
        IMAGES.put("recomm_top_tag", "https://static.trovo.live/imgupload/application/20200710_gxd2syiz09a3x.png");
        IMAGES.put("Thanksgiving", "https://static.trovo.live/imgupload/application/20201117_nrupt548ryapp.webp");
        IMAGES.put("warden", "https://static.trovo.live/imgupload/application/20201118_b6g5pkoe2mp2x.png");
        IMAGES.put("XmasHatDrop", "https://img.trovo.live/imgupload/application/20201209_xyuzuimb84.gif");
        IMAGES.put("admins", "https://static.trovo.live/imgupload/application/20200416_7uhhtbuz5cuAdmins.png");
        IMAGES.put("deco_hat", "https://static.trovo.live/imgupload/application/20200618_1rvca870x743x.png");
        IMAGES.put("tag_wish_buff", "https://img.trovo.live/imgupload/application/20201208_kfn7pn2uwr3x.png");
        IMAGES.put("halloweeen_animation", "https://static.trovo.live/imgupload/application/20201016_bfngxjc875web.gif");
        IMAGES.put("Halloween_deco_spin", "https://static.trovo.live/imgupload/application/20201027_vn9lv3xsxr3x.png");
        IMAGES.put("sub_L3", "https://static.trovo.live/imgupload/application/20200429_c52sc2bnrd52x.png");
        IMAGES.put("xmaschat_2", "https://img.trovo.live/imgupload/application/20201208_pu0fvfudl293x.png");
        IMAGES.put("famhype_event_banner", "https://static.trovo.live/imgupload/application/20200825_tj9424s5h0t.jpg");
        IMAGES.put("X'Mas Drop", "https://img.trovo.live/imgupload/shop/20201203_3l2wayxq5i83x.png");
        IMAGES.put("xmaschat_1", "https://img.trovo.live/imgupload/application/20201208_4kpw01ivp1f3x.png");
        IMAGES.put("deco_icon", "https://static.trovo.live/imgupload/application/20200618_ogko8p7fjg3x.png");
        IMAGES.put("sub_L4", "https://static.trovo.live/imgupload/application/20200429_lgxk7tt7nly2x.png");
        IMAGES.put("creator", "https://static.trovo.live/imgupload/application/20200423_yp9vmkduxdBroadcaster.png");
        IMAGES.put("Thanksgiving_Top_hat", "https://static.trovo.live/imgupload/application/20201113_mkuslgoi22b3x.png");
        IMAGES.put("tag_hour_top", "https://img.trovo.live/imgupload/application/20201204_1y927h4qpxyh2x.png");
        IMAGES.put("Xmasicon_2", "https://img.trovo.live/imgupload/application/20201217_kmp8k7nyi53x.png");
        IMAGES.put("mana_rocket_icon", "https://static.trovo.live/imgupload/application/20200904_f7yb2iwgof53x.png");
        IMAGES.put("sub_L1", "https://static.trovo.live/imgupload/application/20200429_xn39uqvlyjx2x.png");
        IMAGES.put("tag_treasure", "https://img.trovo.live/imgupload/application/20201204_t170g1spc2f1.png");
        IMAGES.put("Thanksgiving_hat", "https://static.trovo.live/imgupload/application/20201113_ew99lgp5dlm.png");
        IMAGES.put("famhype_deco_top", "https://static.trovo.live/imgupload/application/20200902_4tisr0ha3os3x.png");
        IMAGES.put("streamer_official", "https://img.trovo.live/imgupload/application/20210125_7wh3aeggwjq2x.png");
        IMAGES.put("tag_weekly_top", "https://img.trovo.live/imgupload/application/20201210_xxsfijknpl3x.png");
        IMAGES.put("X'Mas Permanent", "https://img.trovo.live/imgupload/application/20201217_fgmiy3l9z3.png");
        IMAGES.put("famhype_icon", "https://static.trovo.live/imgupload/application/20200902_m0o621a7sd3x.png");
        IMAGES.put("halloween_icon", "https://static.trovo.live/imgupload/application/20201015_sy5jdu58yfs2x.png");
        IMAGES.put("moderator", "https://static.trovo.live/imgupload/application/20200421_iz479k1142n2x.png");
        IMAGES.put("subscribe_adv", "https://static.trovo.live/imgupload/application/20200429_xi1uidq3lys3x.png");
        IMAGES.put("DefaultTrovo", "https://img.trovo.live/imgupload/application/20201230_hukn0ereb4s3x.png");
        IMAGES.put("event_anim", "https://static.trovo.live/imgupload/application/20200624_1mc9916uq5lc9c0d9eed39d.gif");
        IMAGES.put("Halloween_deco_top3", "https://static.trovo.live/imgupload/application/20201027_o71un8z32eldevilHatTop_big.png");
        IMAGES.put("ThanksgivingEntry", "https://static.trovo.live/imgupload/application/20201111_dlbx9dsvin3x.png");
        IMAGES.put("XmasAvatarIcon", "https://img.trovo.live/imgupload/application/20201217_9se07pb4l1l3x.png");
        IMAGES.put("event_banner", "https://static.trovo.live/imgupload/application/20200618_xqg22xdxwgg3x.png");
        IMAGES.put("Halloween_deco_top3_spin", "https://static.trovo.live/imgupload/application/20201027_npcs9s2w9penchantedHatTop_big.png");
        IMAGES.put("sub_L2", "https://static.trovo.live/imgupload/application/20200429_d8qax5qosul2x.png");
        IMAGES.put("xmaschat_5", "https://img.trovo.live/imgupload/application/20201208_6dyf4uqv2e33x.png");
        IMAGES.put("XmasWishFinish", "https://img.trovo.live/imgupload/application/20201209_8dsfnkkmpal.gif");
        IMAGES.put("sub_L5", "https://static.trovo.live/imgupload/application/20200429_0r3ynjdyottq2x.png");
        IMAGES.put("X'Mas Spin", "https://img.trovo.live/imgupload/application/20201217_fgmiy3l9z3.png");
        IMAGES.put("X'Mas Top", "https://img.trovo.live/imgupload/application/20201217_vsa25z9jkv.png");
        IMAGES.put("Xmasicon_5", "https://img.trovo.live/imgupload/application/20201217_2ffx6cvvzyv.png");
        IMAGES.put("famhype_anim", "https://static.trovo.live/imgupload/application/20200825_59owizb6ffm.webp");
        IMAGES.put("Halloween_deco_ordinary1", "https://static.trovo.live/imgupload/application/20201027_tdfmabu5yy3x.png");
        IMAGES.put("tag_recomm_top", "https://static.trovo.live/imgupload/application/20200708_jucql186p993x.png");
        IMAGES.put("Xmasicon", "https://img.trovo.live/imgupload/application/20201208_nsethnfrmq3x.png");
        IMAGES.put("Xmasicon_1", "https://img.trovo.live/imgupload/application/20201208_nsethnfrmq3x.png");
    }

    public TrovoUserMedal(String name) {
        this.name = name;
        this.image = IMAGES.getOrDefault(this.name, BLANK_IMAGE);
    }

}
