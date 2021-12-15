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
    private final static ArrayList<String> aliases_queuelist = new ArrayList<>();
    private final static ArrayList<String> aliases_changelog = new ArrayList<>();
    private final static ArrayList<String> aliases_leftorright = new ArrayList<>();
    private final static ArrayList<String> aliases_pickperson = new ArrayList<>();
    private final static ArrayList<String> aliases_fastforward = new ArrayList<>();
    private final static ArrayList<String> aliases_getplaying = new ArrayList<>();

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
        aliases_skip.add("n");
        aliases_skip.add("thissongshit");
        aliases_skip.add("thissongbad");
        aliases_skip.add("nextsong");

        // Cache aliases
        aliases_cache.add("cache");
        aliases_cache.add("download");

        // Cerberus aliases
        aliases_cerberus.add("cerberus");
        aliases_cerberus.add("cerb");

        // Queuelist aliases
        aliases_queuelist.add("queuelist");
        aliases_queuelist.add("ql");
        aliases_queuelist.add("queuel");
        aliases_queuelist.add("qlist");

        // Changelog aliases
        aliases_changelog.add("changelog");
        aliases_changelog.add("cl");

        // Left or right aliases
        aliases_leftorright.add("leftorright");
        aliases_leftorright.add("lor");
        aliases_leftorright.add("rightorleft");
        aliases_leftorright.add("rol");
        aliases_leftorright.add("l/r");
        aliases_leftorright.add("l||r");
        aliases_leftorright.add("lr");
        aliases_leftorright.add("lorr");

        // Pick person aliases
        aliases_pickperson.add("pickperson");
        aliases_pickperson.add("pickfromvoicechannel");
        aliases_pickperson.add("randompickuser");
        aliases_pickperson.add("picksomeone");
        aliases_pickperson.add("spinthewheel");
        aliases_pickperson.add("selectrandomuser");
        aliases_pickperson.add("random");

        // Fast forward aliases
        aliases_fastforward.add("fastforward");
        aliases_fastforward.add("ff");
        aliases_fastforward.add("advance");
        aliases_fastforward.add("timeskip");

        // Get current playing aliases
        aliases_getplaying.add("nowplaying");
        aliases_getplaying.add("whatdis");
        aliases_getplaying.add("whatsongisthis");
        aliases_getplaying.add("whatthis");
        aliases_getplaying.add("whatsong");
        aliases_getplaying.add("currentsong");

    }

    public static ArrayList<String> get_aliases_for(TokenType kw) {
        switch (kw) {
            case KW_PLAY:
                return aliases_play;
            case KW_STOP:
                return aliases_stop;
            case KW_PAUSE:
                return aliases_pause;
            case KW_RESUME:
                return aliases_resume;
            case KW_QUEUE:
                return aliases_queue;
            case KW_SKIP:
                return aliases_skip;
            case KW_CACHE:
                return aliases_cache;
            case KW_CERBERUS:
                return aliases_cerberus;
            case KW_LIST_QUEUE:
                return aliases_queuelist;
            case KW_CHANGELOG:
                return aliases_changelog;
            case KW_LEFTORRIGHT:
                return aliases_leftorright;
            case KW_PICKPERSON:
                return aliases_pickperson;
            case KW_FASTFORWARD:
                return aliases_fastforward;
            case KW_GETPLAYING:
                return aliases_getplaying;
        }

        return null;
    }
}
