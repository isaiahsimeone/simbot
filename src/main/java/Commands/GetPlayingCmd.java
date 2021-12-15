package Commands;

import simbot.SimPlayer;

public class GetPlayingCmd {

    public static boolean execute(SimPlayer simplayer) {
        simplayer.getLastCmdMessage().getChannel().sendMessage("Playing: "
                + simplayer.getAudioPlayer().getPlayingTrack().getInfo().title);
        return true;
    }

}
