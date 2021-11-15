package parse;
import parse.TokenType;

import java.util.ArrayList;
import java.util.Objects;

public class Aliases {
    private final static ArrayList<String> aliases_play = new ArrayList<>();
    private final static ArrayList<String> aliases_stop = new ArrayList<>();
    private final static ArrayList<String> aliases_pause = new ArrayList<>();
    private final static ArrayList<String> aliases_resume = new ArrayList<>();
    private final static ArrayList<String> aliases_queue = new ArrayList<>();
    private final static ArrayList<String> aliases_skip = new ArrayList<>();
    private final static ArrayList<String> aliases_cache = new ArrayList<>();
    private final static ArrayList<String> aliases_cerberus = new ArrayList<>();

    static {
        // Play aliases
        aliases_play.add("play");
        aliases_play.add("pla");
        aliases_play.add("ply");
        aliases_play.add("pl");
        aliases_play.add("p");

        // Stop aliases
        aliases_stop.add("stop");
        aliases_stop.add("halt");
        aliases_stop.add("s");
        aliases_stop.add("leave");
        aliases_stop.add("disconnect");
        aliases_stop.add("exit");
        aliases_stop.add("dc");
        aliases_stop.add("x");
        aliases_stop.add("shutup");
        aliases_stop.add("fuckoff");

        // Pause aliases
        aliases_pause.add("pause");
        aliases_pause.add("hold");

        // Resume aliases
        aliases_resume.add("resume");
        aliases_resume.add("res");

        // Queue aliases
        aliases_queue.add("queue");
        aliases_queue.add("enqueue");
        aliases_queue.add("que");
        aliases_queue.add("q");

        // skip aliases
        aliases_skip.add("skip");
        aliases_skip.add("next");
        aliases_skip.add("nx");
        aliases_skip.add("thissongshit");
        aliases_skip.add("thissongbad");
        aliases_skip.add("nextsong");

        // Cache aliases
        aliases_cache.add("cache");
        aliases_cache.add("download");

        // Cerberus aliases
        aliases_cerberus.add("cerberus");
        aliases_cerberus.add("cerb");

    }

    public static ArrayList<String> get_aliases_for(TokenType kw) {
        if (Objects.equals(kw.get_name(), "play"))
            return aliases_play;
        if (Objects.equals(kw.get_name(), "stop"))
            return aliases_stop;
        if (Objects.equals(kw.get_name(), "pause"))
            return aliases_pause;
        if (Objects.equals(kw.get_name(), "resume"))
            return aliases_resume;
        if (Objects.equals(kw.get_name(), "queue"))
            return aliases_queue;
        if (Objects.equals(kw.get_name(), "skip"))
            return aliases_skip;
        if (Objects.equals(kw.get_name(), "cache"))
            return aliases_cache;
        if (Objects.equals(kw.get_name(), "cerberus"))
            return aliases_cerberus;
        // Not reached
        return null;
    }
}
