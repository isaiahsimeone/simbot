package util;

public enum Emoji {
    GREY_QUESTION("❔"),
    RED_X("❌"),
    ARROW_LEFT("⬅️"),
    ARROW_RIGHT("➡️"),
    THUMBS_UP("\uD83D\uDC4D");

    public final String charcode;

    Emoji(String charcode) {
        this.charcode = charcode;
    }

    public String get_char_code() {
        return charcode;
    }
}
