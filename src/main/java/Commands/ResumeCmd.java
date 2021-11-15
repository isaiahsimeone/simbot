package Commands;

import simbot.SimPlayer;

public class ResumeCmd {

    public static boolean execute(SimPlayer simplayer) {
        // Resume track
        if (simplayer.getAudioPlayer().isPaused())
            simplayer.getAudioPlayer().setPaused(false);
        return true;
    }

}
