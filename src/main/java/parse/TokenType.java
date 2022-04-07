package parse;
public enum TokenType {
    START_SYMBOL("dash"),
    KW_PLAY("play"),
    KW_RESUME("resume"),
    KW_PAUSE("pause"),
    KW_STOP("stop"),
    KW_QUEUE("queue"),
    KW_SKIP("skip"),
    KW_CERBERUS("cerberus"),
    KW_CACHE("cache"),
    KW_LIST_QUEUE("list-queue"),
    KW_CHANGELOG("changelog"),
    KW_LEFTORRIGHT("left-or-right"),
    KW_PICKPERSON("pick-person"),
    KW_FASTFORWARD("fast-forward"),
    KW_REWIND("rewind"),
    KW_GETPLAYING("current-song"),
    EOL("end-of-line"),
    NUMERIC("number"),
    ARGUMENT("argument"),
    USER_MENTION("mention");

    public final String name;

    TokenType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String get_name() {
        return name;
    }

}
