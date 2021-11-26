package Commands;

import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.user.User;
import simbot.SimPlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class PickPersonCmd {

    public static boolean execute(SimPlayer simplayer, ServerVoiceChannel vc) {
        ArrayList<User> user_list = new ArrayList(vc.getConnectedUsers());

        int rand_user_idx;
        // Never pick a bot (including ourselves)
        do {
            rand_user_idx = dice_roll(user_list.size());
        } while (user_list.get(rand_user_idx).isBot());

        simplayer.getLastCmdMessage().getChannel().sendMessage("I pick " + user_list.get(rand_user_idx).getDiscriminatedName());

        return true;
    }


    private static int dice_roll(int faces) {
        Random random = new Random();
        return random.nextInt(faces);
    }

}
