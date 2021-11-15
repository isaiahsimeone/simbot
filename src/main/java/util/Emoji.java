package util;

public enum Emoji {
    GREY_QUESTION("❔");

    public final String charcode;

    Emoji(String charcode) {
        this.charcode = charcode;
    }

    public String get_char_code() {
        return charcode;
    }
}
