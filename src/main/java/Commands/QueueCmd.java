package Commands;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import simbot.SimPlayer;
import util.YTResolver;

public class QueueCmd {

    public static boolean execute(SimPlayer simplayer, String songID) {

        // Pre-resolve
        songID = YTResolver.resolveIfRequired(songID);

        simplayer.getAudioPlayerManager().loadItem(songID, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                System.out.println("queued track: " + track.getIdentifier());
                simplayer.getTrackScheduler().queue(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                System.out.println("Playlist loaded - queue");
            }

            @Override
            public void noMatches() {
                System.out.println("Unable to queue - no matches");
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                System.out.println("Unable to queue - load fail");
            }
        });

        return true;
    }

}
