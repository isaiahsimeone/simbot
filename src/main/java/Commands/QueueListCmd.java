package Commands;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import simbot.SimPlayer;

import java.util.Queue;

public class QueueListCmd {

    public static boolean execute(SimPlayer simplayer) {
        // Resume track
        System.out.println("exec ql");
        Queue<AudioTrack> q = simplayer.getTrackScheduler().getTrackQueue();
        System.out.println("qsz: " + q.size());
        int idx = 0;
        StringBuilder msg = new StringBuilder();

        if (q.size() == 0) {
            msg.append("Nothing in queue");
            simplayer.getLastCmdMessage().getChannel().sendMessage(msg.toString());
            return true;
        }

        msg.append("```");

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
