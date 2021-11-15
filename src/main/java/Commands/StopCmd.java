package Commands;

import simbot.SimPlayer;

public class StopCmd {

    public static boolean execute(SimPlayer simplayer) {
        if (simplayer.isInitialised()) {
            simplayer.getAudioConnection().close();
            simplayer.destroy();
        }
        return true;
    }
}
