package simbot;

import java.util.ArrayList;

import Commands.*;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import org.javacord.api.*;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import parse.Parser;
import util.Emoji;
import util.Messages;
import util.YTResolver;

public class Simbot {

    // Entry-point
    public static void main(String[] args) {
        String TOKEN = "ODgxNTA5ODQ4ODM4MTgwODg0.YSt4BQ.UvwLqYRgcPfyZCkxWi4SG3E6axk";
        DiscordApi api = new DiscordApiBuilder().setToken(TOKEN).login().join();

        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        YoutubeAudioSourceManager yasm = new YoutubeAudioSourceManager();
        playerManager.registerSourceManager(yasm);
        AudioPlayer player = playerManager.createPlayer();
        AudioSource source = new simbot.AudioSource(api, player);
        TrackScheduler trackScheduler = new TrackScheduler(player);

        SimPlayer simplayer = new SimPlayer(playerManager, player, trackScheduler);

        // Handle commands
        api.addMessageCreateListener(event -> {
            if (!event.getMessageContent().startsWith("-")) {
                return ;
            }

            simplayer.setLastCmdEvent(event);

            if (event.getMessageAuthor().getConnectedVoiceChannel().isEmpty()) {
                event.getMessage().getChannel().sendMessage(Messages.NOT_IN_VOICE_CHAN.get_message());
                return ;
            }

            // Parse the message
            Parser parser = new Parser(event.getMessageContent());
            if (!parser.parse()) {
                event.getMessage().addReaction(Emoji.GREY_QUESTION.get_char_code());
                System.out.println("Parse error");
                return ;
            }

            ArrayList<String> arg_list = parser.get_argument_list();

            ServerVoiceChannel vc = event.getMessageAuthor().getConnectedVoiceChannel().get();

            // Initialise and connect to voice channel if not already
            if (!simplayer.isInitialised()) {
                System.out.println("Init simplayer");
                simplayer.init(vc, source);
            }

            String songID;
            switch (parser.get_parsed_command_type()) {
                case CMD_PLAY:
                    // Entire arglist must be a link (no spaces) or a song name
                    songID = String.join(" ", arg_list);
                    PlayCmd.execute(simplayer, songID);
                    break;
                case CMD_STOP:
                    StopCmd.execute(simplayer);
                    break;
                case CMD_PAUSE:
                    PauseCmd.execute(simplayer);
                    break;
                case CMD_RESUME:
                    ResumeCmd.execute(simplayer);
                    break;
                case CMD_SKIP:
                    SkipCmd.execute(simplayer);
                    break;
                case CMD_QUEUE:
                    songID = String.join(" ", arg_list);
                    System.out.println("QUEUE: "+songID);
                    QueueCmd.execute(simplayer, songID);
                    break;
                case CMD_CERBERUS:
                    simplayer.getLastCmdMessage().getChannel().sendMessage("WIP");
                    // WIP
                    String target_user = arg_list.get(0);
                    CerberusCmd.execute(simplayer, api, target_user);
                    break;
                case CMD_CACHE:
                    System.out.println("cache cmd");
                    songID = String.join(" ", arg_list);
                    // If it's just a song name, then resolve it to a video id
                    if (!YTResolver.isLinkFormatted(songID))
                        songID = YTResolver.getIdentifierFromName(songID);
                    if (songID.contains("failure"))
                        // couldn't find a song
                        break;
                    new CacheCmd(simplayer, songID).execute();
                default:
                    System.out.println("Default case reached");
                    // no cmd defined for input
                    break;
            }


        });







    }



}
