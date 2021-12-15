package Commands;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import simbot.SimPlayer;

import java.util.Queue;

public class QueueListCmd {

    public static boolean execute(SimPlayer simplayer) {
        // Resume track
        Queue<AudioTrack> q = simplayer.getTrackScheduler().getTrackQueue();
        int idx = 0;
        StringBuilder msg = new StringBuilder();

        if (q.size() == 0 && simplayer.getAudioPlayer().getPlayingTrack() != null) {
            msg.append("Nothing in queue");
            simplayer.getLastCmdMessage().getChannel().sendMessage(msg.toString());
            return true;
        }

        msg.append("```");

        // List playing song
        msg.append("Now Playing: ").append(simplayer.getAudioPlayer().getPlayingTrack().getInfo().title).append("\n");

        for (AudioTrack track : q) {
            if (idx++ == 0)
                msg.append("Next: ");
            else
                msg.append(idx).append(": ");
            msg.append(track.getInfo().title).append("\n");
        }
        msg.append("```");

        simplayer.getLastCmdMessage().getChannel().sendMessage(msg.toString());

        System.out.println("Sent msg");
        return true;
    }

}
