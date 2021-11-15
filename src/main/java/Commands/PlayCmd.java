package Commands;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import simbot.*;
import util.YTResolver;

public class PlayCmd {

    // return true if success, false otherwise
    public static boolean execute(SimPlayer simplayer, String songID) {
        songID = YTResolver.resolveIfRequired(songID);

        System.out.println("PLAY: "+songID);

        AudioPlayerManager apm = simplayer.getAudioPlayerManager();
        AudioPlayer ap = simplayer.getAudioPlayer();

        apm.loadItem(songID, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                ap.playTrack(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                for (AudioTrack track : playlist.getTracks()) {
                    ap.playTrack(track);
                }
            }
            @Override
            public void noMatches() {
                // Notify the user that we've got nothing
                System.out.println("NO MATCHES");
            }
            @Override
            public void loadFailed(FriendlyException throwable) {
                System.out.println("LOAD FAILURE");
                // Notify the user that everything exploded
            }
        });

        return true;
    }

}
