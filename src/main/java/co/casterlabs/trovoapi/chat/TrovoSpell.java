package co.casterlabs.trovoapi.chat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrovoSpell {
    JOY("https://img.trovo.live/imgupload/shop/20210208_lihswp6n7tr.png", "Joy", 1000, TrovoSpellCurrency.MANA),
    CHOCOLATE("https://img.trovo.live/imgupload/shop/20210204_5kbiy6cf4ln.webp", "Chocolate", 100, TrovoSpellCurrency.ELIXIR),
    LOVE_LETTER("https://img.trovo.live/imgupload/shop/20210205_grn7g2e7ix.webp", "Love Letter", 500, TrovoSpellCurrency.ELIXIR),
    CUPID_LEON("https://img.trovo.live/imgupload/shop/20210201_f0vs2h1j9e.webp", "Cupid Leon", 2000, TrovoSpellCurrency.ELIXIR),
    RISE_2021("https://img.trovo.live/imgupload/shop/20201230_a0ggwjtmxhg.webp", "RISE 2021", 5000, TrovoSpellCurrency.ELIXIR),
    ST_PATRICK("https://img.trovo.live/imgupload/shop/20210312_3guy27m840u.png", "St. Patrick", 1000, TrovoSpellCurrency.MANA),

    BULLSEYE("https://img.trovo.live/imgupload/shop/20210315_a9m7m8c5n1o.png", "Bullseye", 1000, TrovoSpellCurrency.MANA),
    SUPER_GOOD("https://img.trovo.live/imgupload/shop/20210302_pcidahysz1esupergood.webp", "Super Good", 5000, TrovoSpellCurrency.ELIXIR),

    STAY_SAFE("https://static.trovo.live/imgupload/shop/20200421_tyxzwfzebxbhelmet.png", "Stay Safe", 100, TrovoSpellCurrency.MANA),
    ON_FIRE("https://static.trovo.live/imgupload/shop/20200610_4ig5dz712a20200610_i74sm6yafscannon2.png", "On Fire", 500, TrovoSpellCurrency.MANA),
    ROSE("https://static.trovo.live/imgupload/shop/20200509_qnxz9b28xqt.webp", "Rose", 5, TrovoSpellCurrency.ELIXIR),
    HYPE("https://static.trovo.live/imgupload/shop/20200509_gmi66m2kh9f.webp", "HYPE", 10, TrovoSpellCurrency.ELIXIR),
    BRAVO("https://static.trovo.live/imgupload/shop/20200509_6kqivntp45r.webp", "Bravo", 50, TrovoSpellCurrency.ELIXIR),
    WINNER("https://static.trovo.live/imgupload/shop/20200501_jk9npzs4735winner.webp", "Winner", 100, TrovoSpellCurrency.ELIXIR),
    EZ("https://static.trovo.live/imgupload/shop/20200902_acv79khxt9.webp", "EZ", 300, TrovoSpellCurrency.ELIXIR),
    SHINY_UNI("https://static.trovo.live/imgupload/shop/20200509_9fikzpgc2ne.webp", "Shiny Uni", 500, TrovoSpellCurrency.ELIXIR),
    CASH_BANG("https://static.trovo.live/imgupload/shop/20200509_fxbh9hgmtt7.webp", "Cash Bang", 1000, TrovoSpellCurrency.ELIXIR),
    GGWP("https://static.trovo.live/imgupload/shop/20200709_jeqeyzmzxk.webp", "GGWP", 1500, TrovoSpellCurrency.ELIXIR),
    TOP1("https://static.trovo.live/imgupload/shop/20200902_8g1v6js5h5ptop1.webp", "TOP1", 2000, TrovoSpellCurrency.ELIXIR);

    private static final String ANIMATED_ARGS = "?imageView2/2/format/webp";
    private static final String STATIC_ARGS = "?imageView2/2/format/png";

    private @Getter(AccessLevel.NONE) String imageId;
    private String name;
    private int cost;
    private TrovoSpellCurrency currency;

    public double convertToUSD() {
        // https://trovo.live/support?topicid=C02F691C6E4A05DD%2F8F61C86D1EA7869B
        if (this.currency == TrovoSpellCurrency.ELIXIR) {
            return this.cost / 20.0;
        } else {
            return 0;
        }
    }

    public String getAnimatedImage() {
        return this.imageId + ANIMATED_ARGS;
    }

    public String getStaticImage() {
        return this.imageId + STATIC_ARGS;
    }

    public static enum TrovoSpellCurrency {
        MANA,
        ELIXIR;

    }

}
