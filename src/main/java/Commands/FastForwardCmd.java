package Commands;


import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import simbot.SimPlayer;

public class FastForwardCmd {

    public static boolean execute(SimPlayer simplayer) {

        if (simplayer.getAudioPlayer().getPlayingTrack() == null)
            return false;

        AudioTrack playing_track = simplayer.getAudioPlayer().getPlayingTrack();

        long current_position = playing_track.getPosition();
        System.out.println("current position: " + current_position);
        long set_pos = 0;

        // Ensure that advancing the track will not overrun the buffer
        if (current_position + 5000 >= playing_track.getDuration())
            set_pos = playing_track.getDuration() - 1; // skip to the next song
        else
            set_pos = current_position + 5000; // advance 5 seconds

        System.out.println("Setting position: " + set_pos);

        simplayer.getAudioPlayer().getPlayingTrack().setPosition(set_pos);

        return true;
    }
}
