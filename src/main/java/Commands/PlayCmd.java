package Commands;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import simbot.*;
import util.Emoji;
import util.YTResolver;

public class PlayCmd {

    public static boolean execute(SimPlayer simplayer, String songID) {
        songID = YTResolver.resolveIfRequired(songID);

        System.out.println("PLAY: "+songID);

        AudioPlayer ap = simplayer.getAudioPlayer();

        simplayer.getAudioPlayerManager().loadItem(songID, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                simplayer.getTrackScheduler().queue(track);
                simplayer.getLastCmdMessage().getMessage().addReaction(Emoji.THUMBS_UP.get_char_code());
               // ap.playTrack(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                for (AudioTrack track : playlist.getTracks()) {
                    simplayer.getTrackScheduler().queue(track);
                }
                simplayer.getLastCmdMessage().getMessage().addReaction(Emoji.THUMBS_UP.get_char_code());
                simplayer.getLastCmdMessage().getMessage().addReaction(Emoji.ONE_TWO_THREE_FOUR.get_char_code());
            }

            @Override
            public void noMatches() {
                // Notify the user that we've got nothing
                System.out.println("NO MATCHES");
                simplayer.getLastCmdMessage().addReactionToMessage(Emoji.RED_X.get_char_code());
                simplayer.getTrackScheduler().nextTrack();
            }

            @Override
            public void loadFailed(FriendlyException throwable) {
                System.out.println("LOAD FAILURE");
                simplayer.getLastCmdMessage().addReactionToMessage(Emoji.RED_X.get_char_code());
                // Notify the user that everything exploded
                simplayer.getTrackScheduler().nextTrack();
            }
        });

        return true;
    }

}
