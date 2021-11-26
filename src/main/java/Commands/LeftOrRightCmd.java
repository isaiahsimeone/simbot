package Commands;

import simbot.SimPlayer;
import util.Emoji;

public class LeftOrRightCmd {

    public static boolean execute(SimPlayer simplayer) {
        simplayer.getLastCmdMessage().getMessage().addReaction(Emoji.ARROW_LEFT.get_char_code());
        simplayer.getLastCmdMessage().getMessage().addReaction(Emoji.ARROW_RIGHT.get_char_code());
        return true;
    }
}
