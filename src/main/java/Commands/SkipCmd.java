package Commands;

import simbot.SimPlayer;

public class SkipCmd {

    public static boolean execute(SimPlayer simplayer) {

        simplayer.getTrackScheduler().nextTrack();
        return true;
    }

}
