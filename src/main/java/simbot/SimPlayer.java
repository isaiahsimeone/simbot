package simbot;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import org.javacord.api.audio.AudioConnection;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.event.message.MessageCreateEvent;

public class SimPlayer {
    boolean isInitialised = false;
    private AudioConnection audioConnection = null;
    private ServerVoiceChannel vc;
    private AudioPlayerManager apm;
    private AudioPlayer ap;
    private TrackScheduler ts;
    private MessageCreateEvent lastEvent;

    public SimPlayer(AudioPlayerManager apm, AudioPlayer ap, TrackScheduler ts) {
        this.apm = apm;
        this.ap = ap;
        this.ts = ts;
    }

    public AudioPlayerManager getAudioPlayerManager() {
        return apm;
    }

    public AudioPlayer getAudioPlayer() {
        return ap;
    }

    public boolean isInitialised() {
        return isInitialised;
    }

    public void init(ServerVoiceChannel vc, AudioSource source) {
        this.vc = vc;
        vc.connect().thenAccept(audioConnection -> {
            audioConnection.setAudioSource(source);
            this.audioConnection = audioConnection;
        }).exceptionally(e -> {
            // Failed to connect to voice channel (no permissions?)
            e.printStackTrace();
            return null;
        });
        isInitialised = true;
    }
    
    public void setLastCmdEvent(MessageCreateEvent lastEvent) {
        this.lastEvent = lastEvent;
    }
    
    public MessageCreateEvent getLastCmdMessage() {
        return lastEvent;
    }

    public ServerVoiceChannel getServerVoiceChannel() {
        return vc;
    }

    public void destroy() {
        isInitialised = false;
        ap.stopTrack();
    }

    public AudioConnection getAudioConnection() {
        return audioConnection;
    }

    public TrackScheduler getTrackScheduler() {
        return ts;
    }

}
