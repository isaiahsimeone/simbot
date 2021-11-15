package util;

public enum Messages {
    NOT_IN_VOICE_CHAN("Join a voice channel first");

    public final String message;

    Messages(String message) {
        this.message = message;
    }

    public String get_message() {
        return message;
    }
}
