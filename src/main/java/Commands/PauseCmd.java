package Commands;

import simbot.SimPlayer;

public class PauseCmd {

    public static boolean execute(SimPlayer simplayer) {
        boolean is_paused = simplayer.getAudioPlayer().isPaused();
        simplayer.getAudioPlayer().setPaused(!is_paused);
        return true;
    }

}
