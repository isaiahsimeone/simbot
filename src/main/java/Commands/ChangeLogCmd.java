package Commands;

import simbot.SimPlayer;

public class ChangeLogCmd {

    public static final String changeLog = "```V1.0:\n" +
                                    ".Simbot born\n" +
                                    "V1.1:\n" +
                                    ".Simbot reacts to song requests that it is unable to play.\n" +
                                    ".Simbot will play the next song in the queue\n" +
                                    ".The queue can be shown with -queuelist (or -ql)\n" +
                                    ".The play command will queue the requested song if something is already playing\n" +
                                    "V1.2:\n" +
                                    ".Simbot reacts to play cmds with thumbs up if they're valid\n" +
                                    ".Simbot can pick someone from the voice channel at random with -spinthewheel\n" +
                                    ".Simbot can generate a left or right template with -leftorright (or -lor)\n" +
                                    ".Message will be shown when listing the song queue if it's empty\n" +
                                    "V1.3:\n" +
                                    ".Show current playing song with -current/-whatthis etc\n" +
                                    ".Queue list shows current playing song (if any)\n```";

    public static boolean execute(SimPlayer simplayer) {
        simplayer.getLastCmdMessage().getChannel().sendMessage(changeLog);
        return true;
    }
}
